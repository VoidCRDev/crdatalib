package sh.miles.crdatalib.parsing.schema;

import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;

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
     */
    public static SchemaInput of(AssetType<?> assetType, final InputStream stream) {
        try {
            return new SchemaInput(assetType, stream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
