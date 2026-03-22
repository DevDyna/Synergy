# Urn rituals

### Required fields

- `"ingredients"`
- `"result"`

```js
{
  "type": "synergy:urn",
  "ingredients": <item-sized-ingredient..9>,
  "result": <item-stack>
}
```

Example

```json
{
  "type": "synergy:urn",
  "ingredients": [
    {
      "count": 1,
      "item": "minecraft:dirt"
    },
    {
      "count": 1,
      "item": "minecraft:cobblestone"
    }
  ],
  "result": {
    "count": 2,
    "id": "minecraft:stone"
  }
}
```
