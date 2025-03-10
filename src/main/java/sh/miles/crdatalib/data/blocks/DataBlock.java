package sh.miles.crdatalib.data.blocks;

import sh.miles.crdatalib.data.generic.SimpleProperties;

import java.util.Map;

/**
 * Represents a Block in its data format
 *
 * @param id                the id of this block
 * @param defaultProperties the default properties
 * @param states            all explicitly defined states
 * @since 1.0.0
 */
public record DataBlock(String id, SimpleProperties defaultProperties, Map<String, SimpleProperties> states) {
}
