package sh.miles.crdatalib.parsing.schema;

import sh.miles.crdatalib.data.blocks.DataBlock;
import sh.miles.crdatalib.data.items.DataItem;

import java.util.Objects;

/**
 * Represents a type of asset with name and output type
 *
 * @param <O> the output type
 * @since 1.0.0
 */
public final class AssetType<O> {

    public static final AssetType<DataItem> ITEM = new AssetType<>(DataItem.class);
    public static final AssetType<DataBlock> BLOCK = new AssetType<>(DataBlock.class);

    public final Class<O> outputType;

    private AssetType(Class<O> outputType) {
        this.outputType = outputType;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final AssetType<?> assetType)) return false;
        return Objects.equals(outputType, assetType.outputType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(outputType);
    }
}
