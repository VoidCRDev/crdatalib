package sh.miles.crdatalib.parsing.schema.base;

import com.fasterxml.jackson.databind.JsonNode;
import sh.miles.crdatalib.data.blocks.DataBlock;
import sh.miles.crdatalib.parsing.schema.AbstractCosmicReachAssetSchema;

public class BlockAssetSchema extends AbstractCosmicReachAssetSchema<DataBlock> {
    @Override
    protected boolean validate(final JsonNode node) {
        return node.has("stringId") && node.has("blockStates");
    }

    @Override
    protected Class<DataBlock> getParsedType() {
        return DataBlock.class;
    }
}
