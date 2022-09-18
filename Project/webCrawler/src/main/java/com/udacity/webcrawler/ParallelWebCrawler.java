package com.udacity.webcrawler;

import com.udacity.webcrawler.json.CrawlResult;
import com.udacity.webcrawler.parser.PageParser;
import com.udacity.webcrawler.parser.PageParserFactory;

import javax.inject.Inject;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Pattern;

/**
 * A concrete implementation of {@link WebCrawler} that runs multiple threads on a
 * {@link ForkJoinPool} to fetch and process multiple web pages in parallel.
 */
final class ParallelWebCrawler implements WebCrawler {
    private final Clock clock;
    private final PageParserFactory parserFactory;
    private final Duration timeout;
    private final int popularWordCount;
    private final ForkJoinPool pool;
    private final List<Pattern> ignoredUrls;
    private final int maxDepth;

    @Inject
    ParallelWebCrawler(
            Clock clock,
            PageParserFactory parserFactory,
            @Timeout Duration timeout,
            @PopularWordCount int popularWordCount,
            @TargetParallelism int threadCount,
            @IgnoredUrls List<Pattern> ignoredUrls,
            @MaxDepth int maxDepth) {
        this.clock = clock;
        this.parserFactory = parserFactory;
        this.timeout = timeout;
        this.popularWordCount = popularWordCount;
        this.pool = new ForkJoinPool(Math.min(threadCount, getMaxParallelism()));
        this.ignoredUrls = ignoredUrls;
        this.maxDepth = maxDepth;
    }

    @Override
    public CrawlResult crawl(List<String> startingUrls) {
        Instant deadline = clock.instant().plus(timeout);
        ConcurrentMap<String, Integer> counts = new ConcurrentHashMap<>();
        // ConcurrentSkipListSet is a thread-safe implementation of a sorted set.
        // refer to https://www.geeksforgeeks.org/concurrentskiplistset-in-java-with-examples/
        ConcurrentSkipListSet<String> visitedUrls = new ConcurrentSkipListSet<>();
        for (String url : startingUrls) {
            pool.invoke(new crawlInternal(url, deadline, maxDepth, counts, visitedUrls));
        }

        if (counts.isEmpty()) {
            return new CrawlResult.Builder()
                    .setWordCounts(counts)
                    .setUrlsVisited(visitedUrls.size())
                    .build();
        }

        return new CrawlResult.Builder()
                .setWordCounts(WordCounts.sort(counts, popularWordCount))
                .setUrlsVisited(visitedUrls.size())
                .build();
    }

    public class crawlInternal extends RecursiveTask<Boolean> {
        private String url;
        private Instant deadline;
        private int maxDepth;
        private ConcurrentMap<String, Integer> counts;
        private ConcurrentSkipListSet<String> visitedUrls;

        public crawlInternal(
                String url,
                Instant deadline,
                int maxDepth,
                ConcurrentMap<String, Integer> counts,
                ConcurrentSkipListSet<String> visitedUrls) {
            this.url = url;
            this.deadline = deadline;
            this.maxDepth = maxDepth;
            this.counts = counts;
            this.visitedUrls = visitedUrls;
        }

        @Override
        protected Boolean compute() {
            if (maxDepth == 0 || clock.instant().isAfter(deadline)) {
                return false;
            }
            for (Pattern pattern : ignoredUrls) {
                if (pattern.matcher(url).matches()) {
                    return false;
                }
            }
            if (visitedUrls.contains(url)) {
                return false;
            }
            visitedUrls.add(url);
            PageParser.Result result = parserFactory.get(url).parse();

            for (ConcurrentMap.Entry<String, Integer> e : result.getWordCounts().entrySet()) {
                counts.compute(e.getKey(), (k, v) -> (v == null) ? e.getValue() : e.getValue() + v);
            }
            List<crawlInternal> subtasks = new ArrayList<>();
            for (String link : result.getLinks()) {
                subtasks.add(new crawlInternal(link, deadline, maxDepth - 1, counts, visitedUrls));
            }
            invokeAll(subtasks);
            return true;
        }
    }

    @Override
    public int getMaxParallelism() {
        return Runtime.getRuntime().availableProcessors();
    }
}
