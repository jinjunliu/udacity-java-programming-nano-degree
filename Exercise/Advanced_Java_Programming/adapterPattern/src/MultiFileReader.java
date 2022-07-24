import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MultiFileReader implements Closeable {

    private final List<BufferedReader> readers;

    public MultiFileReader(List<Path> paths) {
        readers = new ArrayList<>(paths.size());
        try {
            for (Path path : paths) {
                readers.add(Files.newBufferedReader(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<BufferedReader> getReaders() {
        return Collections.unmodifiableList(readers);
    }

    @Override
    public void close() {
        for (BufferedReader reader : readers) {
            try {
                reader.close();
            } catch (Exception ignored) {
            }
        }
    }
}
