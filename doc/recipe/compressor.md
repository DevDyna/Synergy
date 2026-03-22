# Compressor

### Required fields

- `"energy"` Energy consumed every tick when the recipe is present
- `"ticks"` Duration of the recipe
- `"consume_catalyst"` Define if consume the `"plate"` at the end of the recipe
- `"input"`
- `"output"`

### Optional fields

- `"plate"`

```js
{
  "type": "synergy:machine/compressor",
  "consume_catalyst": <boolean>,
  "energy": <integer>,
  "input": <item-sized-ingredient>,
  "output": <item-stack>,
  "plate": <item-sized-ingredient>,
  "ticks": <integer>
}
```
