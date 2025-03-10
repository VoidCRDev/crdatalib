package sh.miles.crdatalib.data.generic;

import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents an properties which is a map string string wrapper
 *
 * @since 1.0.0
 */
public final class SimpleProperties {

    private final Map<String, PropertyValue> properties;

    public SimpleProperties(final Map<String, Object> properties) {
        this.properties = new HashMap<>();
        for (final Map.Entry<String, Object> entry : properties.entrySet()) {
            this.properties.put(entry.getKey(), new PropertyValue(entry.getValue()));
        }
    }

    /**
     * Gets a property value from this key
     *
     * @param key the key
     * @return the value or null if no value corresponds to the key
     * @throws IllegalArgumentException thrown if the given key is null
     * @throws IllegalStateException    thrown if the key value is not a string
     * @since 1.0.0
     */
    @Nullable
    public PropertyValue getProperty(String key) throws IllegalArgumentException, IllegalStateException {
        if (key == null) throw new IllegalArgumentException("given key must not be null");
        final var value = this.properties.get(key);
        if (value == null) return null;

        throw new IllegalStateException("The property at the key " + key + " is not a string");
    }

    /**
     * Gets a property value from this key, or throws if no value is found
     *
     * @param key the key
     * @return the value at that key
     * @throws IllegalArgumentException thrown if the given key is null or if no property is found
     * @throws IllegalStateException    thrown if the key value is not a string
     * @since 1.0.0
     */
    public PropertyValue getPropertyOrThrow(String key) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("given key must not be null");
        final var property = getProperty(key);
        if (property == null) throw new IllegalArgumentException("these properties have no such key " + key);
        return property;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final SimpleProperties that)) return false;
        return Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(properties);
    }

    @Override
    public String toString() {
        return "Props{" + properties + '}';
    }
}
