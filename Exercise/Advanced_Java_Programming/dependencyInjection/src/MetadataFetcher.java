import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;

public interface MetadataFetcher {

  // Returns the last time the file at the given path was modified.
  Instant getLastModifiedTime(Path path) throws IOException;
}
