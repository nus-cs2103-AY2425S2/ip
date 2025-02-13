package zoe.adapters.gson;

import java.lang.reflect.Type;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import zoe.task.Task;

/**
 * A custom Gson type adapter for serializing and deserializing {@link Task} objects
 * in a polymorphic way.
 * <p>
 * This adapter adds a "type" property to the JSON representation of a {@code Task}
 * to indicate the actual subclass type. During deserialization, the "type" property is
 * used to determine the concrete class to instantiate.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * Gson gson = new GsonBuilder()
 *     .registerTypeAdapter(Task.class, new TaskTypeAdapter())
 *     .create();
 * </pre>
 * </p>
 */
// @@author xumarcus-reused
// Reused from https://www.baeldung.com/gson-polymorphism
public class TaskTypeAdapter implements JsonSerializer<Task>, JsonDeserializer<Task> {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .create();

    /**
     * Serializes a {@link Task} object into its JSON representation.
     * <p>
     * The method converts the {@code Task} to a JSON tree using Gson, and then adds an
     * extra property "type" that contains the simple name of the concrete class.
     * </p>
     *
     * @param task the {@code Task} instance to be serialized
     * @param type the actual type (which is {@code Task})
     * @param context the context of serialization (not used directly)
     * @return a {@link JsonElement} representing the serialized {@code Task} including its type
     */
    @Override
    public JsonElement serialize(Task task, Type type, JsonSerializationContext context) {
        JsonElement jsonElement = gson.toJsonTree(task);
        jsonElement.getAsJsonObject().addProperty("type", task.getClass().getSimpleName());
        return jsonElement;
    }

    /**
     * Deserializes a JSON element into a {@link Task} object.
     * <p>
     * This method reads the "type" property from the JSON object to determine the concrete
     * subclass of {@code Task} to instantiate. It then delegates the deserialization process
     * to Gson.
     * </p>
     *
     * @param jsonElement the JSON element to be deserialized, expected to contain a "type" property
     * @param type the actual type (which is {@code Task})
     * @param context the context of deserialization (not used directly)
     * @return a deserialized {@code Task} object of the appropriate subclass
     * @throws JsonParseException if the class specified in the "type" property is not found
     */
    @Override
    @SuppressWarnings("unchecked")
    public Task deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String typeName = jsonObject.get("type").getAsString();
        try {
            Class<? extends Task> cls = (Class<? extends Task>) Class.forName(typeName);
            return gson.fromJson(jsonElement, cls);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }
}
// @@author
