package sh.miles.crdatalib.parsing.schema.base;

import com.fasterxml.jackson.databind.JsonNode;
import sh.miles.crdatalib.data.loot.LootTable;
import sh.miles.crdatalib.parsing.schema.AbstractCosmicReachAssetSchema;

public class LootTableAssetSchema extends AbstractCosmicReachAssetSchema<LootTable> {
    @Override
    protected boolean validate(final JsonNode node) {
        return node.has("id") && node.has("options");
    }

    @Override
    protected Class<LootTable> getParsedType() {
        return LootTable.class;
    }
}
