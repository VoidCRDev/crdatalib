package sh.miles.crdatalib;

import sh.miles.crdatalib.data.CRData;
import sh.miles.crdatalib.data.blocks.DataBlock;
import sh.miles.crdatalib.data.items.DataItem;
import sh.miles.crdatalib.parsing.CRAssetParser;
import sh.miles.crdatalib.parsing.schema.AssetSchemaFactory;
import sh.miles.crdatalib.parsing.schema.AssetType;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

/**
 * A Data library for Cosmic Reach
 * <p>
 * This class serves as convince access to all data provided by CRDataLib
 *
 * @since 1.0.0
 */
public final class CRDataLib {

    private CRDataLib() {
    }

    /**
     * Creates anew cr asset parser
     *
     * @return a brand new cr asset parser
     * @since 1.0.0
     */
    public static CRAssetParser newParser() {
        return CRAssetParser.builder();
    }

    /**
     * Creates a new schema factor that can be used in the {@link CRAssetParser} for slightly better caching over
     * multiple runs
     *
     * @return the new schema factory
     * @since 1.0.0
     */
    public static AssetSchemaFactory newSchemaFactory() {
        return AssetSchemaFactory.create();
    }

}
