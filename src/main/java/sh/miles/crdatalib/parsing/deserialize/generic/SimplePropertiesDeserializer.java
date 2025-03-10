package sh.miles.crdatalib.parsing.deserialize.generic;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import sh.miles.crdatalib.data.generic.SimpleProperties;

import java.io.IOException;
import java.util.Map;

/**
 * Deserializer for {@link SimpleProperties}
 *
 * @since 1.0.0
 */
public class SimplePropertiesDeserializer extends JsonDeserializer<SimpleProperties> {

    private static final Class<?> REFERENCE = (Class<?>) new TypeReference<>() {
    }.getType();

    @Override
    public SimpleProperties deserialize(final JsonParser parser, final DeserializationContext context) throws IOException, JacksonException {
        return new SimpleProperties((Map<String, Object>) context.readValue(parser, Map.class));
    }
}
