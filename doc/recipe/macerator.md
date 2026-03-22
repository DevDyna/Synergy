# Macerator

### Required fields

- `"energy"` Energy consumed every tick when the recipe is present
- `"ticks"` Duration of the recipe
- `"input"`
- `"output"`


### Optional fields

- `"secondary_item"`

```js
{
  "type": "synergy:machine/macerator",
  "energy": <integer>,
  "input": <item-sized-ingredient>,
  "output": <item-stack>,
  "secondary_item": <chance-output-item>,
  "ticks": <integer>
}
```
