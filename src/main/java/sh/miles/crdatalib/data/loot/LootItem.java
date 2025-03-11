package sh.miles.crdatalib.data.loot;

/**
 * Represents a item in a loot table
 *
 * @since 1.0.0
 */
public record LootItem(String itemId, int min, int max) {
}
