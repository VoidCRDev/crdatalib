package sh.miles.crdatalib.parsing.deserialize.loot;

import com.fasterxml.jackson.databind.module.SimpleModule;
import sh.miles.crdatalib.data.loot.LootChoice;
import sh.miles.crdatalib.data.loot.LootItem;
import sh.miles.crdatalib.parsing.deserialize.ParsingTest;

import java.util.List;

public class LootChoiceDeserializerTest extends ParsingTest<LootChoice> {

    @Override
    public void register(final SimpleModule module) {
        module.addDeserializer(LootItem.class, new LootItemDeserializer());
        module.addDeserializer(LootChoice.class, new LootChoiceDeserializer());
    }

    @Override
    public LootChoice[] getExpected() {
        return new LootChoice[]{
                new LootChoice(
                        0.0f, List.of(new LootItem("base:hazard[default]", 0, 10))
                ),
                new LootChoice(
                        2.0f, List.of(new LootItem("base:medkit", 0, 1))
                )
        };
    }

    @Override
    public String[] getResourceLocation() {
        return new String[]{
                "loot/example_loot_choice_one.json",
                "loot/example_loot_choice_two.json"
        };
    }


}
