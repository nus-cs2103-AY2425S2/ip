package zoe.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import lombok.AllArgsConstructor;
import zoe.ZoeIoException;
import zoe.adapters.gson.TaskTypeAdapter;
import zoe.task.Task;
import zoe.task.TaskList;

/**
 * Handles the persistence of a {@link TaskList} to and from a file.
 * <p>
 * This class uses Gson to serialize and deserialize the task list. It registers a custom
 * type adapter for the {@link Task} class to support polymorphic serialization/deserialization.
 * <br>
 * <strong>Note:</strong> To properly support Java 8 date/time types, ensure that a type adapter for
 * {@link java.time.LocalDate} is registered in {@code TaskTypeAdapter} as follows:
 * <pre>
 *     registerTypeAdapter(LocalDate.class, new zoe.adapters.gson.LocalDateTypeAdapter())
 * </pre>
 * </p>
 */
@AllArgsConstructor
public class Storage {
    private static final Gson gson = new GsonBuilder()
            .registerTypeHierarchyAdapter(Task.class, new TaskTypeAdapter())
            .create();
    private final String path;

    /**
     * Saves the given {@link TaskList} to a file.
     * <p>
     * The method serializes the {@code taskList} into JSON format and writes it to the file at
     * the specified path. If the parent directories do not exist, they are created.
     * </p>
     *
     * @param taskList the task list to save
     * @throws ZoeIoException if there is an error writing to the file
     */
    public void save(TaskList taskList) throws ZoeIoException {
        File file = new File(path);

        //noinspection ResultOfMethodCallIgnored
        file.getParentFile().mkdirs();

        try (Writer writer = new FileWriter(file)) {
            gson.toJson(taskList, writer);
        } catch (JsonIOException | IOException e) {
            throw new ZoeIoException("Fail to write to file: " + e.getMessage());
        }
    }

    /**
     * Loads a {@link TaskList} from the file.
     * <p>
     * The method reads the file from the specified path and deserializes the JSON content
     * into a list of {@link Task} objects. If the file is empty, a {@code ZoeIOException} is thrown.
     * </p>
     *
     * @return a list of tasks loaded from the file
     * @throws ZoeIoException if the file is not found, cannot be read, or if parsing fails
     */
    public List<Task> load() throws ZoeIoException {
        try (Reader reader = new FileReader(path)) {
            List<Task> tasks = gson.fromJson(reader, new TypeToken<List<Task>>() {
            }.getType());
            if (tasks == null) {
                throw new ZoeIoException("File is empty");
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new ZoeIoException("File not found");
        } catch (IOException e) {
            throw new ZoeIoException("Fail to read from file");
        } catch (JsonParseException e) {
            throw new ZoeIoException("Fail to parse file");
        }
    }
}
