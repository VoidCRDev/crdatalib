package sh.miles.crdatalib.data.items;

import sh.miles.crdatalib.data.generic.SimpleProperties;

/**
 * Represents an Item in its json form data
 *
 * @param id         the item id
 * @param properties the item properties
 * @since 1.0.0
 */
public record DataItem(String id, SimpleProperties properties) {
}
