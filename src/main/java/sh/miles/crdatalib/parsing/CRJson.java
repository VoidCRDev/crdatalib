package sh.miles.crdatalib.parsing;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import sh.miles.crdatalib.data.blocks.DataBlock;
import sh.miles.crdatalib.data.generic.SimpleProperties;
import sh.miles.crdatalib.data.items.DataItem;
import sh.miles.crdatalib.data.loot.LootChoice;
import sh.miles.crdatalib.data.loot.LootItem;
import sh.miles.crdatalib.data.loot.LootTable;
import sh.miles.crdatalib.parsing.deserialize.blocks.DataBlockDeserializer;
import sh.miles.crdatalib.parsing.deserialize.generic.SimplePropertiesDeserializer;
import sh.miles.crdatalib.parsing.deserialize.items.DataItemDeserializer;
import sh.miles.crdatalib.parsing.deserialize.loot.LootChoiceDeserializer;
import sh.miles.crdatalib.parsing.deserialize.loot.LootItemDeserializer;
import sh.miles.crdatalib.parsing.deserialize.loot.LootTableDeserializer;

/**
 * A Class for managing the CRJson components
 *
 * @since 1.0.0
 */
public final class CRJson {

    public static final JsonMapper JSON = new JsonMapper()
            .rebuild()
            .enable(JsonReadFeature.ALLOW_TRAILING_COMMA)
            .addModule(new SimpleModule()
                    .addDeserializer(SimpleProperties.class, new SimplePropertiesDeserializer())
                    .addDeserializer(DataItem.class, new DataItemDeserializer())
                    .addDeserializer(DataBlock.class, new DataBlockDeserializer())
                    .addDeserializer(LootItem.class, new LootItemDeserializer())
                    .addDeserializer(LootChoice.class, new LootChoiceDeserializer())
                    .addDeserializer(LootTable.class, new LootTableDeserializer())
            )
            .build();

    private CRJson() {
    }

}
