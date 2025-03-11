package sh.miles.crdatalib.parsing.deserialize.items;

import com.fasterxml.jackson.databind.module.SimpleModule;
import sh.miles.crdatalib.data.generic.SimpleProperties;
import sh.miles.crdatalib.data.items.DataItem;
import sh.miles.crdatalib.parsing.deserialize.ParsingTest;
import sh.miles.crdatalib.parsing.deserialize.generic.SimplePropertiesDeserializer;

import java.util.List;
import java.util.Map;

public class DataItemDeserializerTest extends ParsingTest<DataItem> {

    @Override
    public void register(final SimpleModule module) {
        module.addDeserializer(SimpleProperties.class, new SimplePropertiesDeserializer());
        module.addDeserializer(DataItem.class, new DataItemDeserializer());
    }

    @Override
    public DataItem[] getExpected() {
        return new DataItem[]{new DataItem(
                "base:example_item",
                new SimpleProperties(Map.of(
                        "texture", "textures/items/example_item.png",
                        "modelType", "base:item3D",
                        "stackLimit", 999,
                        "toolSpeed", 100,
                        "durability", 1209,
                        "effectiveBreakingTags", List.of(
                                "tool_axe_effective",
                                "tool_pickaxe_effective"
                        ))
                ))};
    }

    @Override
    public String[] getResourceLocation() {
        return new String[]{"items/example_item.json"};
    }
}
