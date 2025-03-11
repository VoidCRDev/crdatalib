package sh.miles.crdatalib.parsing.schema;

import sh.miles.crdatalib.parsing.schema.base.BlockAssetSchema;
import sh.miles.crdatalib.parsing.schema.base.ItemAssetSchema;
import sh.miles.crdatalib.parsing.schema.base.LootTableAssetSchema;

import java.util.HashMap;
import java.util.Map;

/**
 * A Factory for the creation of asset schema
 *
 * @since 1.0.0
 */
public final class AssetSchemaFactory {

    private final Map<AssetType<?>, AssetSchema<?>> cache = new HashMap<>(5);

    private AssetSchemaFactory() {
    }

    /**
     * Creates a schema from the type of asset
     * <p>
     * The provided asset type must be a pre-existing asset type defined in {@link AssetType}
     *
     * @param type the type of asset
     * @param <T>  the type of asset output
     * @return the asset schema
     * @throws IllegalArgumentException thrown if no schema exists or provided AssetType is null
     * @since 1.0.0
     */
    public <T> AssetSchema<T> from(AssetType<T> type) throws IllegalArgumentException {
        if (type == null) throw new IllegalArgumentException("non null type must be provided");
        AssetSchema<T> schema = (AssetSchema<T>) cache.get(type);
        if (schema != null) {
            return schema;
        }

        AssetSchema<?> tmp = null;
        if (type.equals(AssetType.ITEM)) {
            tmp = new ItemAssetSchema();
            this.cache.put(type, schema);
            return (AssetSchema<T>) tmp;
        } else if (type.equals(AssetType.BLOCK)) {
            tmp = new BlockAssetSchema();
            this.cache.put(type, schema);
            return (AssetSchema<T>) tmp;
        } else if (type.equals(AssetType.LOOT_TABLE)) {
            tmp = new LootTableAssetSchema();
            this.cache.put(type, schema);
            return (AssetSchema<T>) tmp;
        }

        throw new IllegalArgumentException("No such known schema for type " + type.outputType.getName());
    }

    public static AssetSchemaFactory create() {
        return new AssetSchemaFactory();
    }
}
