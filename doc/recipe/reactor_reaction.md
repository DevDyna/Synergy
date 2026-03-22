# Reactor Reaction

### Note:
- `"fe"` can be modified using moderators
- `"heat"` can be modified using moderators and coolers

### Required fields

- `"ticks"` Duration of the recipe
- `"ingredient"`
- `"result"`
- `"fe"` ForgeEnergy factor produced every tick
- `"heat"` Heat factor produced every tick


```js
{
  "type": "synergy:fuel_cell",
  "fe": <integer>,
  "heat": <float>,
  "ingredient": <item-ingredient>,
  "result": <item-stack>,
  "ticks": <integer>
}
```