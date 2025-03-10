package sh.miles.crdatalib;

import org.jspecify.annotations.NullMarked;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Generic utilities for CRDataLib
 *
 * @since 1.0.0
 */
@NullMarked
public final class CRDataLibUtils {

    /**
     * Grabs a namespace or key from a cosmic reach key "namespace:key"
     *
     * @param key       the full key
     * @param namespace true to capture the namespace, otherwise false
     * @return the expected key part excluding ':'
     * @since 1.0.0
     */
    public static String captureKeyPart(String key, boolean namespace) {
        if (key == null) throw new IllegalArgumentException("Provided key must not be null");
        final StringBuilder builder = new StringBuilder();
        boolean allowAppend = namespace;
        char current;
        for (int i = 0; i < key.length(); i++) {
            current = key.charAt(i);
            if (current == ':') {
                allowAppend = !allowAppend;
            } else if (allowAppend) {
                builder.append(current);
            }
        }

        return builder.toString();
    }

    /**
     * Reads all bytes from the given path or throws a runtime exception
     *
     * @param path the path to read from
     * @return the bytes
     * @since 1.0.0
     */
    public static byte[] readBytesOrThrow(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
