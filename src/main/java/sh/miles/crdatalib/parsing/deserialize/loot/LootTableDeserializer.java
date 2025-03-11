package sh.miles.crdatalib.parsing.deserialize.loot;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import sh.miles.crdatalib.data.loot.LootChoice;
import sh.miles.crdatalib.data.loot.LootTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LootTableDeserializer extends JsonDeserializer<LootTable> {
    @Override
    public LootTable deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JacksonException {
        final JsonNode node = parser.readValueAsTree();
        final String id = node.get("id").asText();
        final List<LootChoice> choices = new ArrayList<>();
        for (final JsonNode option : node.get("options")) {
            choices.add(context.readTreeAsValue(option, LootChoice.class));
        }
        return new LootTable(id, choices);
    }
}
