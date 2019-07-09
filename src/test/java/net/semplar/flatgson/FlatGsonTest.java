package net.semplar.flatgson;

import com.google.gson.Gson;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

public class FlatGsonTest {
    private final Gson gson = new Gson(); {
        GsonFlatSupport.injectInto(gson);
    }

    private final Person person = new Person(); {
        person.name = "Douglas";
        person.bag = new Item();
        person.bag.itemName = "Brush";
        person.bag.part = new Part();
        person.bag.part.partName = "Battery";
    }

    private final PersonFlattened personFlat = new PersonFlattened(); {
        personFlat.name = "Douglas";
        personFlat.itemName = "Brush";
        personFlat.partName = "Battery";
    }

    private final String expectedJson = "{\"name\":\"Douglas\",\"itemName\":\"Brush\",\"partName\":\"Battery\"}";

    @Test
    public void testPersonToJson() {
        Assert.assertEquals(expectedJson, gson.toJson(person));
    }

    @Test
    public void testFlatToJson() {
        Assert.assertEquals(expectedJson, gson.toJson(personFlat));
    }

    @Test
    public void testJsonToFlat() {
        Assert.assertEquals(personFlat, gson.fromJson(expectedJson, PersonFlattened.class));
    }

    @Test
    public void testJsonToPerson() {
        Assert.assertEquals(person, gson.fromJson(expectedJson, Person.class));
    }

    @Data
    private static class Person {
        protected String name;
        @Flat protected Item bag;
    }

    @Data
    private static class Item {
        protected String itemName;
        @Flat protected Part part;
    }

    @Data
    private static class Part {
        protected String partName;
    }

    /**
     * Should be analoguous to {@link Person} since it uses {@link Flat} tag.
     */
    @Data
    private static class PersonFlattened {
        protected String name;
        protected String itemName;
        protected String partName;
    }
}
