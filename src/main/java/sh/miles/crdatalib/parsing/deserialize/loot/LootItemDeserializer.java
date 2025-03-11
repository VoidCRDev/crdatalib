package sh.miles.crdatalib.parsing.deserialize.loot;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import sh.miles.crdatalib.data.loot.LootItem;

import java.io.IOException;

/**
 * Deserializer for {@link LootItem}
 *
 * @since 1.0.0
 */
public final class LootItemDeserializer extends JsonDeserializer<LootItem> {
    @Override
    public LootItem deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JacksonException {
        final JsonNode node = parser.readValueAsTree();
        final String id = node.get("id").asText();
        final int max = node.get("max").asInt();
        final int min;
        if (node.has("min")) {
            min = node.get("min").asInt();
        } else {
            min = 0;
        }

        return new LootItem(id, min, max);
    }
}
