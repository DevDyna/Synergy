# Extractor

### Required fields

- `"energy"` Energy consumed every tick when the recipe is present
- `"ticks"` Duration of the recipe
- `"input"`


### Optional fields

- `"secondary_item"`
- `"optional_fluid"`

```js
{
  "type": "synergy:machine/extractor",
  "energy": <integer>,
  "input": <item-sized-ingredient>,
  "optional_fluid": <fluid-stack>,
  "secondary_item": <chance-output-item>,
  "ticks": <integer>
}
```
