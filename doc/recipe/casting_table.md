# Casting Table

### Required fields

- `"ticks"` Duration of the recipe
- `"consume_input"` Define if consume the `"input"` at the end of the recipe
- `"fluid"`
- `"output"`

### Optional fields

- `"input"`

```js
{
  "type": "synergy:casting_table",
  "consume_input": <boolean>,
  "fluid": <fluid-sized-ingredient>,
  "input": <item-sized-ingredient>,
  "output": <item-stack>,
  "ticks": <integer>
}
```
