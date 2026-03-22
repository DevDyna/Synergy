# Rock Crusher

### Required fields

- `"energy"` Energy consumed every tick when the recipe is present
- `"ticks"` Duration of the recipe
- `"input_fluid"`
- `"input_item"`
- `"result"`

```js
{
  "type": "synergy:machine/rock_crusher",
  "energy": <integer>,
  "input_fluid": <fluid-sized-ingredient>,
  "input_item": <item-sized-ingredient>,
  "result": <chance-output-item..9>,
  "ticks": <integer>
}
```