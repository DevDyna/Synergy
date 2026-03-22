# Recipe Schemes

This page will provide a complete guide on describe all required and optional fields related to [Synergy](https://legacy.curseforge.com/minecraft/mc-mods/synergy)

## Knowledge Base:

Any recipetype require to contain the field `"type"` else it doesn't work!

### Common syntax

This section will let you know what some key-words define what

- `<recipe-id>` -> `"minecraft:crafting_shapeless"`

- `<blockstate-id>` -> `"{"Name": "minecraft:stonecutter","Properties": {"facing": "north"}}"`

- `<item-id>` -> `"minecraft:oak_log"`
- `<item-tag>` -> `"#minecraft:logs"`

- `<fluid-id>` -> `"minecraft:water"`
- `<fluid-tag>` -> `"#minecraft:lava"`

- `<integer>` -> `1`
- `<float>` -> `1.0`
- `<boolean>` -> `true`

- Multiple Elements

```js
"entry": <item-stack..2>
```

Describe a list of differents `<item-stack>` which can be at most 2 elements!

## Item Stack syntax `<item-stack>`

```js
{
    "count": <integer>,
    "id": <item-id>
}
```

## Ingredient syntax `<item-ingredient>`

Static Item type

```js
{
    "item": <item-id>
}
```

Tag Item type

```js
{
    "tag": <item-tag>
}
```

## Sized Ingredient syntax `<item-sized-ingredient>`

Static Item type

```js
{
    "count": <integer>,
    "item": <item-id>
}
```

Tag Item type

```js
{
    "count": <integer>,
    "tag": <item-tag>
}
```

## ChanceOutputItem syntax `<chance-output-item>`

Static Item type

```js
{
    "chance": <float>,
    "item": <item-stack>
}
```

## Fluid Stack syntax `<fluid-stack>`

```js
{
    "amount": <integer>,
    "id": <fluid-id>
  }
```

## FluidIngredient syntax `<fluid-ingredient>`

Static Fluid type

```js
{
    "fluid": <fluid-id>
}
```

Tag Fluid type

```js
{
    "tag": <fluid-tag>
}
```

_deprecated_

## Sized Fluid Ingredient syntax `<fluid-sized-ingredient>`

Static Fluid type

```js
{
    "amount": <integer>,
    "fluid": <fluid-id>
}
```

Tag Fluid type

```js
{
    "amount": <integer>,
    "tag": <fluid-tag>
}
```

## Provider Node Pattern syntax `<node-pattern>`

```js
{
    "core": <blockstate-id>,
    "left": <blockstate-id>,
    "right": <blockstate-id>,
    "below": <blockstate-id>
}
```

- `"core"` is required
- `"left"` , `"right"` and `"below"` are optional

# All Recipe Schemes

- [Alloy Smelter](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/alloy_smelter.md)
- [Casting Factory](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/casting_factory.md)
- [Casting Table](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/casting_table.md)
- [Compressor](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/compressor.md)
- [Crushing Tub](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/crushing_tub.md)
- [Dryable Bricks](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/dryable_brick.md)
- [Drying Rack](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/drying_rack.md)
- [Electric Furnace](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/electric_furnace.md)
- [Electric Melter](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/electric_melter.md)
- [Evaporation Basin](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/evaporation_basin.md)
- [Extractor](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/extractor.md)
- [Foundry Fuels](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/foundry_fuels.md)
- [Foundry](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/foundry.md)
- [Item Use](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/item_use.md)
- [Macerator](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/macerator.md)
- [Provider Fluid](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/provider_fluid.md)
- [Provider Item](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/provider_item.md)
- [Quern](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/quern.md)
- [Reactor Reaction](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/reactor_reaction.md)
- [Resource Info](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/resource_info.md)
- [Rock Crusher](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/rock_crusher.md)
- [Urn Ritual](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/urn_ritual.md)
- [Void Box Conversion](https://github.com/DevDyna/Synergy/blob/21.1/doc/recipe/void_box_conversion.md)
