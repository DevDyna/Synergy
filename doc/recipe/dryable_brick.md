# Dryable Bricks

### Note:

- this recipe type is semi-hardcoded
- it cannot accept any block to be used but require to extend [Synergy/PlaceableBrickBlock.java](https://github.com/DevDyna/Synergy/blob/21.1/src/main/java/com/devdyna/synergy/init/builder/survival/placeable_bricks/PlaceableBrickBlock.java) !
- using a datapack/kubejs you can ONLY remove recipes of this type
- item output is defined inside the loot table of the brick placeable

### Required fields

- `"block"` [Synergy/PlaceableBrickBlock.java](https://github.com/DevDyna/Synergy/blob/21.1/src/main/java/com/devdyna/synergy/init/builder/survival/placeable_bricks/PlaceableBrickBlock.java) block at the base stage
- `"input"`
- `"output"`

```js
{
  "type": "synergy:dryable_brick",
  "block": <blockstate-id>,
  "input": <item-ingredient>,
  "output": <item-stack>
}
```
