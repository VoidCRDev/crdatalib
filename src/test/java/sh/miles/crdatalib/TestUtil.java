package sh.miles.crdatalib;

import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.io.InputStream;

public final class TestUtil {

    public static InputStream resource(final String name) {
        return TestUtil.class.getResourceAsStream("/" + name);
    }

    public static <T> T jsonResource(final String name, Class<T> export, JsonMapper mapper) {
        try (final var stream = resource(name)) {
            return mapper.readValue(stream, export);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
