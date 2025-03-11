package sh.miles.crdatalib.data;

import sh.miles.crdatalib.data.blocks.DataBlock;
import sh.miles.crdatalib.data.items.DataItem;
import sh.miles.crdatalib.data.loot.LootTable;
import sh.miles.crdatalib.parsing.schema.AssetType;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Represents all cosmic reach data
 *
 * @param items  all parsed cosmic reach items
 * @param blocks all parsed cosmic reach blocks
 * @since 1.0.0
 */
public record CRData(List<DataItem> items, List<DataBlock> blocks, List<LootTable> lootTables) {

    /**
     * Creates a new builder for this CRData
     * <p>
     * By default this builder is not safe for concurrency
     *
     * @return the builder
     * @since 1.0.0
     */
    public static Builder builder() {
        return new Builder(false);
    }

    /**
     * Creates a new builder for this CRData
     *
     * @param concurrent whether or not to use concurrency safe backend
     * @return the builder
     * @since 1.0.0
     */
    public static Builder builder(boolean concurrent) {
        return new Builder(concurrent);
    }

    /**
     * A Builder for {@link CRData}
     *
     * @since 1.0.0
     */
    public static class Builder {
        private final Map<AssetType<?>, Queue<?>> objects;
        private final boolean concurrent;

        private Builder(boolean concurrent) {
            this.concurrent = concurrent;
            if (concurrent) {
                this.objects = new ConcurrentHashMap<>();
            } else {
                this.objects = new HashMap<>();
            }
        }

        /**
         * Adds an asset type of the value
         *
         * @param type  the asset type
         * @param value the value
         * @since 1.0.0
         */
        public void add(AssetType<?> type, Object value) {
            final Queue<Object> values = (Queue<Object>) objects.computeIfAbsent(type, (k) -> concurrent ? new ConcurrentLinkedDeque<>() : new ArrayDeque<>());
            values.add(value);
        }

        /**
         * Builds a {@link CRData}
         *
         * @return the cr data
         * @since 1.0.0
         */
        public CRData build() throws IllegalStateException {
            if (this.objects.isEmpty()) {
                throw new IllegalStateException("no objects to be built in empty CRData objects are not valid");
            }

            return new CRData(
                    (List<DataItem>) objects.getOrDefault(AssetType.ITEM, new ArrayDeque<>()).stream().toList(),
                    (List<DataBlock>) objects.getOrDefault(AssetType.BLOCK, new ArrayDeque<>()).stream().toList(),
                    (List<LootTable>) objects.getOrDefault(AssetType.LOOT_TABLE, new ArrayDeque<>()).stream().toList()
            );
        }
    }
}
