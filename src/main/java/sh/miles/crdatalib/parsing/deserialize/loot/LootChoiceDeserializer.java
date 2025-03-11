package sh.miles.crdatalib.parsing.deserialize.loot;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import sh.miles.crdatalib.data.loot.LootChoice;
import sh.miles.crdatalib.data.loot.LootItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Deserializer for {@link LootChoice}
 *
 * @since 1.0.0
 */
public final class LootChoiceDeserializer extends JsonDeserializer<LootChoice> {
    @Override
    public LootChoice deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JacksonException {
        final JsonNode parent = parser.readValueAsTree();
        if (parent.isArray()) {
            final List<LootItem> items = new ArrayList<>();
            for (final JsonNode item : parent) {
                items.add(context.readTreeAsValue(item, LootItem.class));
            }
            return new LootChoice(0.0f, items);
        }

        final float weight = (float) parent.get("weight").asDouble();
        final List<LootItem> items = new ArrayList<>();
        for (final JsonNode stack : parent.get("stacks")) {
            items.add(context.readTreeAsValue(stack, LootItem.class));
        }

        return new LootChoice(weight, items);
    }
}
