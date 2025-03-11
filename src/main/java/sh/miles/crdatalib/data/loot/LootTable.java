package sh.miles.crdatalib.data.loot;

import java.util.List;

/**
 * Represents a loot table
 *
 * @since 1.0.0
 */
public record LootTable(String id, List<LootChoice> choices) {
}
