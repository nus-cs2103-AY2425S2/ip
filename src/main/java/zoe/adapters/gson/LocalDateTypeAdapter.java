package zoe.adapters.gson;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * A custom Gson type adapter for serializing and deserializing {@link LocalDate} objects.
 * <p>
 * This adapter converts {@code LocalDate} objects to and from their JSON representation
 * using the date pattern {@code yyyy-MM-dd}.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * Gson gson = new GsonBuilder()
 *     .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
 *     .create();
 * </pre>
 * </p>
 */
// @@author xumarcus-reused
// Reused from https://mkyong.com/java/gson-supports-java-8-date-time-types
public class LocalDateTypeAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Serializes a {@link LocalDate} object into its JSON representation.
     *
     * @param localDate the {@code LocalDate} instance to be serialized
     * @param type the actual type (which is {@code LocalDate})
     * @param context the context of serialization that is used to serialize any
     *                                   non-trivial field of {@code localDate}
     * @return a {@link JsonPrimitive} representing the formatted date as a JSON string
     */
    @Override
    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext context) {
        return new JsonPrimitive(localDate.format(formatter));
    }

    /**
     * Deserializes a JSON element into a {@link LocalDate} object.
     *
     * @param jsonElement the JSON element containing the date as a string
     * @param type the actual type (which is {@code LocalDate})
     * @param context the context of deserialization that is used to deserialize any
     *                                     non-trivial field of the JSON element
     * @return a {@code LocalDate} object parsed from the JSON string
     */
    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) {
        return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString(), formatter);
    }
}
// @@author
