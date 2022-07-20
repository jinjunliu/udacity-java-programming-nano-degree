import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

public final class Main {
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.out.println("Usage: Main [file path]");
      return;
    }

    UdacisearchClient client =
            new UdacisearchClient(
                    "CatFacts LLC",
                    17,
                    8000,
                    5,
                    Instant.now(),
                    Duration.ofDays(180),
                    ZoneId.of("America/Los_Angeles"),
                    "555 Meowmers Ln, Riverside, CA 92501");

    Path outputPath = Path.of(args[0]);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.writeValue(Files.newBufferedWriter(outputPath), client);

    System.out.println("Wrote to " + outputPath.toAbsolutePath().toString());

    UdacisearchClient deserialized =
            objectMapper.readValue(Files.newBufferedReader(outputPath), UdacisearchClient.class);
    System.out.println("Deserialized " + deserialized);
  }
}
