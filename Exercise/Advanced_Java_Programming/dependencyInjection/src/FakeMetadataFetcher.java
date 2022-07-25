import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;

public final class FakeMetadataFetcher implements MetadataFetcher {

  private final Map<Path, Instant> modifiedTimes;

  public FakeMetadataFetcher(Map<Path, Instant> modifiedTimes) {
    this.modifiedTimes = Objects.requireNonNull(modifiedTimes);
  }

  @Override
  public Instant getLastModifiedTime(Path path) throws IOException {
    return modifiedTimes.get(path);
  }
}
