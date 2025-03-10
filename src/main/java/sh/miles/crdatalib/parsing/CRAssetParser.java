package sh.miles.crdatalib.parsing;

import sh.miles.crdatalib.CRDataLibUtils;
import sh.miles.crdatalib.data.CRData;
import sh.miles.crdatalib.parsing.schema.AssetSchemaFactory;
import sh.miles.crdatalib.parsing.schema.AssetType;
import sh.miles.crdatalib.parsing.schema.SchemaInput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * Parser for CR Assets
 *
 * @since 1.0.0
 */
public final class CRAssetParser {

    private final Map<AssetType<?>, String> navigate = new HashMap<>();
    private AssetSchemaFactory schemaFactory;
    private Path root;
    private int threads = 0;

    private CRAssetParser() {
    }

    /**
     * Sets the root of the assets folder
     *
     * @param root the root folder
     * @return this asset parser
     * @since 1.0.0
     */
    public CRAssetParser assetRoot(Path root) {
        this.root = root;
        return this;
    }

    /**
     * Adds another subfolder of the {@link #assetRoot(Path)} to navigate
     *
     * @param type      the asset type of this sub folder
     * @param subfolder the subfolder path
     * @return this asset parser
     * @since 1.0.0
     */
    public CRAssetParser navigate(AssetType<?> type, String subfolder) {
        if (type == null || subfolder == null)
            throw new IllegalArgumentException("The provided AssetType and subfolder must not be null");
        this.navigate.put(type, subfolder);
        return this;
    }

    /**
     * Sets the schema factory for this asset parser
     * <p>
     * This builder argument is automatically provided if null or not set
     *
     * @param schemaFactory the schema faster
     * @return this asset parser
     * @since 1.0.0
     */
    public CRAssetParser schemaFactory(AssetSchemaFactory schemaFactory) {
        this.schemaFactory = schemaFactory;
        return this;
    }

    /**
     * The amount of threads to allocate to this parser. Low thread counts are recommended for small parses
     * <p>
     * By default no threads are provided '0' meaning parse will run on the main thread
     *
     * @param threads the amount of threads to allocate
     * @return this asset parser
     * @since 1.0.0
     */
    public CRAssetParser threads(int threads) {
        this.threads = threads;
        return this;
    }

    /**
     * Parses all content now on the main thread
     *
     * @return the CRData
     * @since 1.0.0
     */
    public CRData parseNow() {
        validateBuilder();
        return parseSingle();
    }

    /**
     * Parses all content with the respective settings
     * <p>
     * If no more than 0 threads are provided to {@link #threads} this method is analogous to {@link #parseNow()}. Given
     * no threads are provided it is safe to use {@link CompletableFuture#getNow(Object)} with a null parameter for your
     * value
     *
     * @return the future CRData
     * @since 1.0.0
     */
    public CompletableFuture<CRData> parse() {
        validateBuilder();
        if (this.threads == 0) {
            return CompletableFuture.completedFuture(parseSingle());
        } else {
            return parseThreaded();
        }
    }

    private CRData parseSingle() {
        final CRData.Builder builder = CRData.builder();
        for (final Map.Entry<AssetType<?>, String> entry : this.navigate.entrySet()) {
            try {
                final var assetType = entry.getKey();
                final var schema = this.schemaFactory.from(assetType);
                final var subfolder = this.root.resolve(entry.getValue());
                try (Stream<Path> files = Files.list(subfolder)) {
                    files.forEach((path) -> {
                        final byte[] bytes = CRDataLibUtils.readBytesOrThrow(path);
                        final SchemaInput input = SchemaInput.of(assetType, bytes);
                        if (schema.validate(input)) {
                            builder.add(assetType, schema.parse(input));
                        }
                    });
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return builder.build();
    }

    private CompletableFuture<CRData> parseThreaded() {
        final CRData.Builder builder = CRData.builder(true);
        try (final ExecutorService executors = Executors.newFixedThreadPool(this.threads, Thread.ofVirtual().factory())) {
            List<CompletableFuture<Void>> waiting = new ArrayList<>();
            for (final Map.Entry<AssetType<?>, String> entry : this.navigate.entrySet()) {
                final var assetType = entry.getKey();
                final var schema = this.schemaFactory.from(assetType);
                final var subfolder = this.root.resolve(entry.getValue());
                try (Stream<Path> files = Files.list(subfolder)) {
                    files.forEach((path) -> {
                        waiting.add(CompletableFuture.runAsync(() -> {
                            final byte[] bytes = CRDataLibUtils.readBytesOrThrow(path);
                            final SchemaInput input = SchemaInput.of(assetType, bytes);
                            if (schema.validate(input)) {
                                builder.add(assetType, schema.parse(input));
                            }
                        }, executors));
                    });
                }
            }

            return CompletableFuture.allOf(waiting.toArray(CompletableFuture[]::new)).thenApply((v) -> builder.build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateBuilder() {
        if (this.root == null) throw new IllegalStateException("A root path for the assets must be provided");
        if (this.navigate.isEmpty())
            throw new IllegalStateException("At least 1 subfolder most be provided to navigate");
        if (this.threads < 0) throw new IllegalStateException("Atleast one thread must be given");
        if (this.schemaFactory == null) this.schemaFactory = AssetSchemaFactory.create();
    }

    public static CRAssetParser builder() {
        return new CRAssetParser();
    }
}
