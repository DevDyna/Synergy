# Void Box Conversion

### Required fields

- `"input"`
- `"output"`

```js
{
  "type": "synergy:void_box_conversion",
  "input": <item-ingredient>,
  "output": <item-stack>
}
```

Example

```json
{
  "type": "synergy:void_box_conversion",
  "input": {
    "item": "minecraft:dirt"
  },
  "output": {
    "count": 2,
    "id": "minecraft:stone"
  }
}
```
