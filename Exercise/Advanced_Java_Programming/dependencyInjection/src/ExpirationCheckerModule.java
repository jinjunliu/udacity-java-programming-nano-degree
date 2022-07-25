import com.google.inject.AbstractModule;

import java.time.Clock;

public final class ExpirationCheckerModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(MetadataFetcher.class).to(MetadataFetcherImpl.class);
    bind(Clock.class).toInstance(Clock.systemUTC());
  }
}
