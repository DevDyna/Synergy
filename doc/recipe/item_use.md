# Item Use

### Note:
- `"render_only"` recipes are mainly hardcoded so you can remove it from recipe viewers but don't remove the in-world event!

### Required fields

- `"base"` Block clicked with `"catalyst"`
- `"catalyst"` Item used to click `"base"`
- `"result"` Block result placed
- `"can_be_disabled"` Define when the recipe can be completly disabled via config
- `"render_only"` Define when the recipe doesn't execute the in-world recipe

### Optional fields

- `"item_result"` Item result of `"catalyst"`

```js
{
  "type": "synergy:item_use",
  "base": <blockstate-id>,
  "can_be_disabled": <boolean>,
  "catalyst": <item-ingredient>,
  "item_result": <item-stack>,
  "render_only": <boolean>,
  "result": <blockstate-id>
}
```
