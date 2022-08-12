package com.udacity.webcrawler.json;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.json.*;

/**
 * A static utility class that loads a JSON configuration file.
 */
public final class ConfigurationLoader {

    private final Path path;

    /**
     * Create a {@link ConfigurationLoader} that loads configuration from the given {@link Path}.
     */
    public ConfigurationLoader(Path path) {
        this.path = Objects.requireNonNull(path);
    }

    /**
     * Loads configuration from this {@link ConfigurationLoader}'s path
     *
     * @return the loaded {@link CrawlerConfiguration}.
     */
    public CrawlerConfiguration load() {
        // read the JSON string from path
        try (Reader reader = Files.newBufferedReader(path)) {
            return read(reader);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read configuration from " + path, e);
        }
    }

    /**
     * Loads crawler configuration from the given reader.
     *
     * @param reader a Reader pointing to a JSON string that contains crawler configuration.
     * @return a crawler configuration
     */
    public static CrawlerConfiguration read(Reader reader) {
        // This is here to get rid of the unused variable warning.
        Objects.requireNonNull(reader);
        // read the JSON input and parse it into a CrawlerConfiguration using the Jackson JSON library
        // ref: https://www.baeldung.com/jackson-object-mapper-tutorial
        JsonMapper mapper = new JsonMapper();
        mapper.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        try {
            return mapper.readValue(reader, CrawlerConfiguration.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading JSON configuration", e);
        }
    }
}
