package sh.miles.crdatalib.parsing.schema.base;

import com.fasterxml.jackson.databind.JsonNode;
import sh.miles.crdatalib.data.items.DataItem;
import sh.miles.crdatalib.parsing.schema.AbstractCosmicReachAssetSchema;

public class ItemAssetSchema extends AbstractCosmicReachAssetSchema<DataItem> {

    @Override
    protected boolean validate(final JsonNode node) {
        return node.has("id") && node.has("itemProperties");
    }

    @Override
    protected Class<DataItem> getParsedType() {
        return DataItem.class;
    }
}
