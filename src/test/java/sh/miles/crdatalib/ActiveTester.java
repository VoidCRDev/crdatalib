package sh.miles.crdatalib;

import sh.miles.crdatalib.data.blocks.DataBlock;
import sh.miles.crdatalib.parsing.schema.AssetType;

import java.nio.file.Path;

public class ActiveTester {

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            final long start = System.currentTimeMillis();
            CRDataLib.newParser()
                    .assetRoot(Path.of("assets", "base"))
                    .threads(0)
                    .navigate(AssetType.BLOCK, "blocks")
                    .navigate(AssetType.ITEM, "items")
                    .navigate(AssetType.LOOT_TABLE, "loot")
                    .parse().whenComplete((data, exception) -> {
                        System.out.println("Took " + (System.currentTimeMillis() - start) + "ms");
                        for (final DataBlock block : data.blocks()) {
                            System.out.println(block.id());
                        }
                    });
        }
    }
}
