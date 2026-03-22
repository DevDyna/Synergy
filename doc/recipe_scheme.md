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

Use cases:

#TODO

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

Use cases:

#TODO

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

Use cases:

#TODO

## ChanceOutputItem syntax `<chance-output-item>`

Static Item type

```js
{
    "chance": <float>,
    "item": <item-stack>
}
```

Use cases:

- rock crusher recipe output

## Fluid Stack syntax `<fluid-stack>`

```js
{
    "amount": <integer>,
    "id": <fluid-id>
  }
```

Use cases:

#TODO

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

Use cases:

- _deprecated_

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

Use cases:

#TODO

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
