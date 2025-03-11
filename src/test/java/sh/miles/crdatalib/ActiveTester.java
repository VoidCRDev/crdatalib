package sh.miles.crdatalib;

import sh.miles.crdatalib.parsing.schema.AssetType;

import java.nio.file.Path;

public class ActiveTester {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final long start = System.currentTimeMillis();
            CRDataLib.newParser()
                    .assetRoot(Path.of("assets", "base"))
                    .threads(2)
                    .navigate(AssetType.BLOCK, "blocks")
                    .navigate(AssetType.ITEM, "items")
                    .navigate(AssetType.LOOT_TABLE, "loot")
                    .parse().whenComplete((data, exception) -> {
                        System.out.println("Took " + (System.currentTimeMillis() - start) + "ms");
                    });
        }
    }
}
