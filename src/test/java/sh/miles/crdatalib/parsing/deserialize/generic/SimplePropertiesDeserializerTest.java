package sh.miles.crdatalib.parsing.deserialize.generic;

import com.fasterxml.jackson.databind.module.SimpleModule;
import sh.miles.crdatalib.data.generic.SimpleProperties;
import sh.miles.crdatalib.parsing.deserialize.ParsingTest;

import java.util.List;
import java.util.Map;

public class SimplePropertiesDeserializerTest extends ParsingTest<SimpleProperties> {

    @Override
    public void register(final SimpleModule module) {
        module.addDeserializer(SimpleProperties.class, new SimplePropertiesDeserializer());
    }

    @Override
    public SimpleProperties[] getExpected() {
        return new SimpleProperties[]{new SimpleProperties(Map.of(
                "texture", "textures/items/example_item.png",
                "modelType", "base:item3D",
                "stackLimit", 999,
                "toolSpeed", 100,
                "durability", 1209,
                "effectiveBreakingTags", List.of(
                        "tool_axe_effective",
                        "tool_pickaxe_effective"
                )
        ))};
    }

    @Override
    public String[] getResourceLocation() {
        return new String[]{"generic/example_properties.json"};
    }
}
