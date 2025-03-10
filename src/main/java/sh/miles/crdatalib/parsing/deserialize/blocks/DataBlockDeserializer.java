package sh.miles.crdatalib.parsing.deserialize.blocks;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import sh.miles.crdatalib.data.blocks.DataBlock;
import sh.miles.crdatalib.data.generic.SimpleProperties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Deserializer for {@link DataBlock}
 *
 * @since 1.0.0
 */
public class DataBlockDeserializer extends JsonDeserializer<DataBlock> {
    @Override
    public DataBlock deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JacksonException {
        final JsonNode parent = parser.readValueAsTree();
        final Map<String, SimpleProperties> states = new HashMap<>();
        final var blockStates = parent.get("blockStates");
        if (parent.has("blockStates")) {
            final var iterator = blockStates.fields();
            while (iterator.hasNext()) {
                final var next = iterator.next();
                states.put(next.getKey(), context.readTreeAsValue(next.getValue(), SimpleProperties.class));
            }
        }

        final SimpleProperties defaultProperties;
        if (parent.has("defaultProperties")) {
            defaultProperties = context.readTreeAsValue(parent.get("defaultProperties"), SimpleProperties.class);
        } else {
            defaultProperties = new SimpleProperties(Map.of());
        }

        return new DataBlock(parent.get("stringId").asText(), defaultProperties, states);
    }
}
