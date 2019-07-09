# gson-flat
Object flattening support for Gson. Works through dirty reflection.

Consider following model:
```java
private static class Person {
    protected String name;
    @Flat protected Item bag;
}

private static class Item {
    protected String itemName;
    @Flat protected Part part;
}

private static class Part {
    protected String partName;
}
```

With normal Gson, serialized JSON looks like:
```json
{
    "name": "Douglas",
    "bag": {
        "itemName": "Brush",
        "part": {
            "partName": "Battery"
        }
    }
}
```

So, this library is created to workaround such inconvenience. To flatten JSON, mark needed fields with @Flat annotation, and customize your Gson instance:
```java
Gson gson = new Gson(); // or whatever way you create it
GsonFlatSupport.injectInto(gson);
// now behavior of gson is little changed
```

The serialized JSON will look like:
```json
{
    "name": "Douglas",
    "itemName": "Brush",
    "partName": "Battery"
}
```
