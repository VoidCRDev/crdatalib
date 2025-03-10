package sh.miles.crdatalib.parsing.deserialize.items;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import sh.miles.crdatalib.data.generic.SimpleProperties;
import sh.miles.crdatalib.data.items.DataItem;

import java.io.IOException;

/**
 * Deserializer for {@link DataItem}
 *
 * @since 1.0.0
 */
public final class DataItemDeserializer extends JsonDeserializer<DataItem> {

    @Override
    public DataItem deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JacksonException {
        final JsonNode parent = parser.readValueAsTree();
        final var properties = parent.get("itemProperties");
        assert properties != null;
        return new DataItem(parent.get("id").asText(), context.readTreeAsValue(properties, SimpleProperties.class));
    }
}
