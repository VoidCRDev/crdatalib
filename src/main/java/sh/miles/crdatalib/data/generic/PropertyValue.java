package sh.miles.crdatalib.data.generic;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Property entry
 *
 * @since 1.0.0
 */
public final class PropertyValue {

    private final Object value;

    public PropertyValue(final Object value) {
        this.value = value;
    }

    /**
     * Gets this property as a string
     *
     * @return the string value
     * @throws ClassCastException thrown if this value is not a string
     * @since 1.0.0
     */
    public String string() throws ClassCastException {
        return this.cast(String.class);
    }

    /**
     * Gets this property as a string array
     *
     * @return the string array
     * @throws ClassCastException thrown if this value is not a string array
     * @since 1.0.0
     */
    public List<String> stringList() throws ClassCastException {
        return this.cast(List.class);
    }

    /**
     * Gets this property as a nested map
     *
     * @return the nested map
     * @throws ClassCastException thrown if this value is not a nested map
     */
    public Map<String, Object> nestMap() throws ClassCastException {
        return this.cast(Map.class);
    }

    /**
     * Gets this property as an integer
     *
     * @return the integer
     * @throws ClassCastException thrown if this value is not an integer
     */
    public int integer() throws ClassCastException {
        return this.cast(int.class);
    }

    /**
     * Gets if this value is the given type
     *
     * @param type the type to check
     * @param <T>  the type
     * @return true if it is this type, otherwise false
     * @since 1.0.0
     */
    public <T> boolean is(Class<T> type) {
        return this.value.getClass().isAssignableFrom(type);
    }

    /**
     * Casts this property value to a type
     *
     * @param type the type to case to
     * @param <T>  the type
     * @return the property value
     * @throws ClassCastException thrown if the casted type is incompatible
     * @since 1.0.0
     */
    public <T> T cast(Class<T> type) throws ClassCastException {
        return type.cast(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final PropertyValue that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "" + this.value;
    }
}
