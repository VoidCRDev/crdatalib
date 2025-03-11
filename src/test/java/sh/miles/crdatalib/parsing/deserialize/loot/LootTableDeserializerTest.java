package sh.miles.crdatalib.parsing.deserialize.loot;

import com.fasterxml.jackson.databind.module.SimpleModule;
import sh.miles.crdatalib.data.loot.LootChoice;
import sh.miles.crdatalib.data.loot.LootItem;
import sh.miles.crdatalib.data.loot.LootTable;
import sh.miles.crdatalib.parsing.deserialize.ParsingTest;

import java.util.List;

public class LootTableDeserializerTest extends ParsingTest<LootTable> {
    @Override
    public void register(final SimpleModule module) {
        module.addDeserializer(LootItem.class, new LootItemDeserializer())
                .addDeserializer(LootChoice.class, new LootChoiceDeserializer())
                .addDeserializer(LootTable.class, new LootTableDeserializer());
    }

    @Override
    public LootTable[] getExpected() {
        return new LootTable[]{
                new LootTable("base:example", List.of(
                        new LootChoice(
                                0.0f, List.of(new LootItem("base:hazard[default]", 0, 10
                                )
                        )),
                        new LootChoice(
                                2.0f, List.of(
                                new LootItem("base:lunar_soil[default]", 0, 10),
                                new LootItem("base:asphalt[default]", 0, 10)))
                ))
        };
    }

    @Override
    public String[] getResourceLocation() {
        return new String[] {
                "loot/example_loot_table.json"
        };
    }
}
