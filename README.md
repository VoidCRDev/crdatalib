# crdatalib

crdatalib is focused on providing easy access experience for parsing.
Json cosmic reach data files for general use.

## Goal

The goal of this API is to provide utilities to easily parse cosmic reach assets quickly.
This API can be used to write new Cosmic Reach asset files or also just read and act on the
current assets like making API generation.

## Usage

Using crdatalib is very easy and everything that is needed can be accessed through the CRDataLib class.

A Short example for parsing blocks and items is below

```java
final CRAssetParser parser = CRDataLib.newParser();
final CompletableFuture<CRData> future = parser.assetRoot(Path.of("assets", "base"))
  .threads(4) // threads aren't required but allocating a couple can speed up large parses
  .navigate(AssetType.BLOCK, "blocks")
  .navigate(AssetType.ITEM, "items")
  .parse(); // parseNow() can be used to parse on a single thread
future.whenComplete((data, exception) -> {
  for (final DataItem item : data.items()) {
    System.out.println(item);
  }

  for (final DataBlock block : data.blocks()) {
    System.out.println(block);
  }
});
```

## Progress

This API is not currently finished The progress is below

- [ ] Animations
    - [ ] Read
    - [ ] Write
- [ ] Block Events
    - [ ] Read
    - [ ] Write
- [X] Blocks
    - [X] Read
    - [ ] Write
- [ ] Block State Generators
    - [ ] Read
    - [ ] Write
- [ ] Fonts
    - [ ] Read
    - [ ] Write
- [X] Items
    - [X] Read
    - [ ] Write
- [ ] Languages
    - [ ] Read
    - [ ] Write
- [ ] Loot
    - [X] Read
    - [ ] Write
- [ ] Models
    - [ ] Read
    - [ ] Write
- [ ] Music
    - [ ] Read
    - [ ] Write
- [ ] Recipes
    - [ ] Read
    - [ ] Write
- [ ] Shaders
    - [ ] Read
    - [ ] Write
- [ ] Textures
    - [ ] Read
    - [ ] Write
