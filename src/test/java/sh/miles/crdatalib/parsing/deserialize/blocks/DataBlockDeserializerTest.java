package sh.miles.crdatalib.parsing.deserialize.blocks;

import com.fasterxml.jackson.databind.module.SimpleModule;
import sh.miles.crdatalib.data.blocks.DataBlock;
import sh.miles.crdatalib.data.generic.SimpleProperties;
import sh.miles.crdatalib.parsing.deserialize.ParsingTest;
import sh.miles.crdatalib.parsing.deserialize.generic.SimplePropertiesDeserializer;

import java.util.List;
import java.util.Map;

public class DataBlockDeserializerTest extends ParsingTest<DataBlock> {
    @Override
    public void register(final SimpleModule module) {
        module.addDeserializer(SimpleProperties.class, new SimplePropertiesDeserializer());
        module.addDeserializer(DataBlock.class, new DataBlockDeserializer());
    }

    @Override
    public DataBlock getExpected() {
        return new DataBlock(
                "base:example_block",
                new SimpleProperties(Map.of()),
                Map.of("default", new SimpleProperties(Map.of(
                        "modelName", "base:models/blocks/empty.json",
                        "blockEventsId", "base:block_events_example_block",
                        "isOpaque", false,
                        "lightAttenuation", 0,
                        "refractiveIndex", 1,
                        "canRaycastForBreak", false,
                        "canRaycastForPlaceOn", false,
                        "canRaycastForReplace", true,
                        "walkThrough", true,
                        "tags", List.of("replaceable")
                )))
        );
    }

    @Override
    public String getResourceLocation() {
        return "blocks/example_block.json";
    }
}
