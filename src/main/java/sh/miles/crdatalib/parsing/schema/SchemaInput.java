package sh.miles.crdatalib.parsing.schema;

import org.jspecify.annotations.Nullable;
import sh.miles.crdatalib.CRDataLibUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * Input to a {@link AssetSchema} with extra caching features, relies on {@link AssetType}
 *
 * @since 1.0.0
 */
public final class SchemaInput {

    public final AssetType<?> type;
    public final byte[] raw;
    @Nullable
    Object intermediary = null;

    SchemaInput(final AssetType<?> type, byte[] raw) {
        this.type = type;
        this.raw = raw;
    }

    /**
     * Creates a schema input from an asset type and bytes
     *
     * @param assetType the asset type
     * @param bytes     the asset bytes
     * @return the schema input
     * @since 1.0.0
     */
    public static SchemaInput of(AssetType<?> assetType, final byte[] bytes) {
        return new SchemaInput(assetType, bytes);
    }

    /**
     * Creates a schema input from an asset type and stream
     *
     * @param assetType the type of asset
     * @param stream    the stream
     * @return the schema input
     * @since 1.0.0
     */
    public static SchemaInput of(AssetType<?> assetType, final InputStream stream) {
        try {
            return new SchemaInput(assetType, stream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a list of schema inputs from a folder
     *
     * @param assetType      the asset type
     * @param folder         the folder to fold into schema inputs
     * @param depthTolerance how deep the walker can recurse before an exception is thrown. e.g. at depth tolerance of 2
     *                       if 2 sub folders of the head are entered and exception is thrown
     * @return the list of schema inputs
     * @throws IllegalArgumentException thrown if the depth goes above the depthTolerance input
     * @since 1.1.0
     */
    public static List<SchemaInput> fold(AssetType<?> assetType, final Path folder, int depthTolerance) throws IllegalStateException {
        final List<SchemaInput> inputs = new ArrayList<>();
        try (final Stream<Path> subs = Files.walk(folder)) {
            subs.forEach((sub) -> {
                if (Files.isDirectory(sub)) {
                    return;
                }

                final byte[] bytes = CRDataLibUtils.readBytesOrThrow(sub);
                final SchemaInput input = SchemaInput.of(assetType, bytes);
                inputs.add(input);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inputs;
    }
}
