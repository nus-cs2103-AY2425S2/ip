package elchino.storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import elchino.exceptions.*;
import elchino.tasks.*;

/**
 * Class to handle storage of tasks, allowing tasks to be loaded from and saved to a file.
 */
public class Storage {
    private final Path filePath;
    private static final String ERROR_CREATE_FILE = "Error: al crear el archivo de tareas.";
    private static final String ERROR_READ_FILE = "Error: al leer el archivo.";
    private static final String ERROR_WRITE_FILE = "Error: al escribir en el archivo.";
    private static final String ERROR_MALFORMED_LINE = "Error: Línea malformada en el archivo: ";
    private static final String ERROR_UNKNOWN_TASK_TYPE = "Error: Tipo de tarea desconocido en el archivo: ";
    private static final String ERROR_PROCESS_TASK = "Error: No se pudo procesar la tarea en el archivo: ";
    
    /**
     * Constructor for Storage.
     * @param filePath The path to the file to store tasks.
     * @throws ElchinoException if an error occurs during initialization
     */
    public Storage(String filePath) throws ElchinoException {
        this.filePath = Path.of(filePath);

        try {
            if (!Files.exists(this.filePath.getParent())) {
                Files.createDirectories(this.filePath.getParent());
            }
            if (!Files.exists(this.filePath)) {
                Files.createFile(this.filePath);
            }
        } catch (IOException e) {
            throw new ElchinoException(ERROR_CREATE_FILE);
        }
    }

    /**
     * Loads tasks from the file.
     * @return An ArrayList of tasks loaded from the file.
     * @throws ElchinoException if an error occurs during loading
     */
    public ArrayList<Task> loadTasks() throws ElchinoException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(parseTask(line));
            }
        } catch (IOException e) {
            throw new ElchinoException(ERROR_READ_FILE);
        }
        return tasks;
    }

    /**
     * Saves tasks to the file.
     * @param tasks An ArrayList of tasks to be saved to the file.
     * @throws ElchinoException if an error occurs during saving.
     */
    public void saveTasks(ArrayList<Task> tasks) throws ElchinoException {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.storeTask());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new ElchinoException(ERROR_WRITE_FILE);
        }
    }


    /**
     * Parses a line from the file into a Task object.
     * @param line The line to be parsed as a task.
     * @return The Task object parsed from the line.
     */
    private Task parseTask(String line) throws ElchinoException {
        String[] details = line.split(" \\| ");
        if (details.length < 3) {
            throw new ElchinoException(ERROR_MALFORMED_LINE + line);
        }

        String type = details[0];
        boolean isDone = details[1].equals("1");
        String description = details[2];

        try {
            switch (type) {
                case "T":
                    Task todo = new Todo(description);
                    if (isDone) todo.setDone();
                    return todo;
                case "D":
                    if (details.length < 4) {
                        throw new ElchinoException(ERROR_MALFORMED_LINE + line);
                    }
                    Task deadline = new Deadline(description, details[3]);
                    if (isDone) deadline.setDone();
                    return deadline;
                case "E":
                    if (details.length < 5) {
                        throw new ElchinoException(ERROR_MALFORMED_LINE + line);
                    }
                    Task event = new Event(description, details[3], details[4]);
                    if (isDone) event.setDone();
                    return event;
                default:
                    throw new ElchinoException(ERROR_UNKNOWN_TASK_TYPE + type);
            }
        } catch (Exception e) {
            throw new ElchinoException(ERROR_PROCESS_TASK + line);
        }
    }
}
