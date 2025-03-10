package sh.miles.crdatalib.parsing.schema;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Defines a schema for a particular type of asset
 *
 * @param <T> the type of object returned by this schema
 * @since 1.0.0
 */
public interface AssetSchema<T> {

    /**
     * Parses an asset from bytes
     *
     * @param input the schema input to parse
     * @return the object parsed
     * @since 1.0.0
     */
    T parse(SchemaInput input);

    /**
     * Validates the provided bytes
     *
     * @param input the input to validate
     * @return true if the asset is valid
     * @since 1.0.0
     */
    boolean validate(SchemaInput input);

    /**
     * Gets the schema version for this asset schema
     * <p>
     * Schema version generally aligns with the lowest CosmicReach version a schema works for
     *
     * @return the schema version
     * @since 1.0.0
     */
    String getSchemaVersion();
}
