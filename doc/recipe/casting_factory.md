# Casting Factory

### Required fields

- `"energy"` Energy consumed every tick when the recipe is present
- `"ticks"` Duration of the recipe
- `"consume_input"` Define if consume the `"input_item"` at the end of the recipe
- `"input_fluid"`
- `"output"`


### Optional fields

- `"input_item"`

```js
{
  "type": "synergy:machine/casting_factory",
  "consume_item": <boolean>,
  "energy": <integer>,
  "input_fluid": <fluid-sized-ingredient>,
  "input_item": <item-sized-ingredient>,
  "output": <item-stack>,
  "ticks": <integer>
}
```
