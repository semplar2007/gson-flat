# gson-flat
Object flattening support for Gson.

## The problem
Sometimes in DTO you need to include all fields of other DTO. The normal way is to extend the DTO and add needed fields there. However if DTO already extends other class, this is not possible since Java doesn't have multiple inheritance; the workaround should be applied.

Consider following model:
```java
class Person {
    String name;
    @Flat Item bag;
}

class Item {
    String itemName;
    @Flat Part part;
}

class Part {
    String partName;
}
```

With normal Gson behavior, serialized JSON looks like:
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

To flatten JSON, mark needed fields with @Flat annotation, and customize your Gson instance:
```java
Gson gson = new Gson(); // or whatever way you create it
GsonFlatSupport.injectInto(gson);
// now behavior of gson is little changed
Person person = new Person();
// ... fill person's data
return gson.toJson(person);
```

The serialized JSON:
```json
{
    "name": "Douglas",
    "itemName": "Brush",
    "partName": "Battery"
}
```

## Implementation details
- Implemented by reflective replacing of Gson's default ReflectiveTypeAdapterFactory by modified version in runtime;
- Both serialization and deserialization works;
- Recursive flattening is possible, as on example above;
- Doesn't use toJsonTree(), works straightly with JsonReader and JsonWriter by having map of property name (String) to mapped field path (Field[]);
- Flattened class can't contain fields with colliding names, otherwise an exception is thrown during Gson.toJson() call.
- Doesn't support flattening of Lists and Maps.

## License
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
