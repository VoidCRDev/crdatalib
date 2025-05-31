package sh.miles.crdatalib.parsing.schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import sh.miles.crdatalib.parsing.CRJson;

import java.io.IOException;

/**
 * An abstract asset schema for all cosmic reach objects
 *
 * @since 1.0.0
 */
public abstract class AbstractCosmicReachAssetSchema<T> implements AssetSchema<T> {

    public static final String DEFAULT_SCHEMA_VERSION = "0.4.7";

    @Override
    public T parse(final SchemaInput input) {
        intermediary(input);
        try {
            return CRJson.JSON.treeToValue((TreeNode) input.intermediary, getParsedType());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean validate(final SchemaInput input) {
        intermediary(input);
        return getParsedType() == input.type.outputType && validate((JsonNode) input.intermediary);
    }

    @Override
    public String getSchemaVersion() {
        return DEFAULT_SCHEMA_VERSION;
    }

    private void intermediary(SchemaInput input) {
        if (input.intermediary == null) {
            try {
                input.intermediary = CRJson.JSON.readTree(input.raw);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Validates a JsonNode
     *
     * @param node the node to validate
     * @return true if valid, otherwise false
     * @since 1.0.0
     */
    protected abstract boolean validate(JsonNode node);

    /**
     * Gets the parsed type
     *
     * @return the parsed type
     * @since 1.0.0
     */
    protected abstract Class<T> getParsedType();
}
