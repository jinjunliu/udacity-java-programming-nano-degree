import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public final class ExpirationChecker {

  private final Clock clock;
  private final MetadataFetcher metadataFetcher;

  @Inject
  ExpirationChecker(Clock clock, MetadataFetcher metadataFetcher) {
    this.clock = clock;
    this.metadataFetcher = metadataFetcher;
  }

  public List<Path> getExpiredFiles(List<Path> paths, Duration expiration) {
    return paths.stream()
            .filter((path) -> isExpired(path, expiration))
            .collect(Collectors.toList());
  }

  private boolean isExpired(Path path, Duration expiration) {
    Instant now = clock.instant();
    try {
      Instant modifiedTime = metadataFetcher.getLastModifiedTime(path);
      return now.isAfter(modifiedTime.plus(expiration));
    } catch (IOException e) {
      return false;
    }
  }
}
