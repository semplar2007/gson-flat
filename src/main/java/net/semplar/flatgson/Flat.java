package net.semplar.flatgson;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Makes Gson serializer/deserializer extract fields of marked object from the same JSON object where field is declared,
 * not from JSON subobject.
 * Recursive flat works too, however you can't @Flat {@link java.util.Collections}s, {@link java.util.Map}s
 * and their descendants.
 * Works only with {@link GsonFlatSupport}, otherwise marked field will be mapped as a Json child object.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Flat {
}
