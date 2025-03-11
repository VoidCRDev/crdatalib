package sh.miles.crdatalib.data.loot;

import java.util.List;

/**
 * Represents an option in a loot table
 */
public record LootChoice(float weight, List<LootItem> items) {
}
