package sh.miles.crdatalib.parsing.deserialize.loot;

import com.fasterxml.jackson.databind.module.SimpleModule;
import sh.miles.crdatalib.data.loot.LootItem;
import sh.miles.crdatalib.parsing.deserialize.ParsingTest;

public class LootItemDeserializerTest extends ParsingTest<LootItem> {
    @Override
    public void register(final SimpleModule module) {
        module.addDeserializer(LootItem.class, new LootItemDeserializer());
    }

    @Override
    public LootItem[] getExpected() {
        return new LootItem[]{new LootItem(
                "base:hazard[default]",
                5,
                10
        )};
    }

    @Override
    public String[] getResourceLocation() {
        return new String[]{"loot/example_loot_item.json"};
    }
}
