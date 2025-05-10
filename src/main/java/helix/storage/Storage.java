package helix.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import helix.enums.TaskStatus;
import helix.enums.TaskType;
import helix.task.Deadline;
import helix.task.Event;
import helix.task.Task;
import helix.task.Todo;

/**
 * Handles the saving and loading of tasks from a file.
 * The tasks are stored in a plain text file and are serialised / de-serialised using a specific format.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     * Ensures the file path always resolves to the `data` directory in the project root.
     *
     * @param filePath the relative path to the helix.storage file
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath).normalize();
    }

    /**
     * Loads tasks from the helix.storage file.
     * If the file or its parent directory does not exist, they are created, and an empty list is returned.
     *
     * @return a {@link List} of {@link Task} objects loaded from the helix.storage file
     * @throws IOException if there is an error reading the file
     */
    public List<Task> load() throws IOException {
        ensureFileExists();
        return readTasksFromFile();
    }

    /**
     * Ensures the storage file exists, creating it if necessary.
     *
     * @throws IOException if an error occurs while creating the file
     */
    private void ensureFileExists() throws IOException {
        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        }
    }

    /**
     * Reads tasks from the storage file.
     *
     * @return a list of tasks loaded from the file
     * @throws IOException if an error occurs while reading the file
     */
    private List<Task> readTasksFromFile() throws IOException {
        List<Task> taskList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                taskList.add(parseTask(line));
            }
        }
        return taskList;
    }

    /**
     * Saves a list of tasks to the helix.storage file.
     * The tasks are serialised into a specific format before being written to the file.
     *
     * @param taskList the {@link List} of {@link Task} objects to save
     * @throws IOException if there is an error writing to the file
     */
    public void save(List<Task> taskList) throws IOException {
        writeTasksToFile(taskList);
    }

    /**
     * Writes the given tasks to the storage file.
     *
     * @param taskList the list of tasks to write
     * @throws IOException if an error occurs while writing to the file
     */
    private void writeTasksToFile(List<Task> taskList) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for (Task task : taskList) {
                writer.write(serialiseTask(task));
                writer.newLine();
            }
        }
    }

    /**
     * Parses a {@link Task} from its string representation.
     * The string must follow the format: {@code TaskType | TaskStatus | Description | [Additional Details]}.
     *
     * @param line the string representing the helix.task in helix.storage
     * @return the parsed {@link Task} object
     * @throws IllegalArgumentException if the string is invalid or cannot be parsed
     */
    private static Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            TaskType taskType = TaskType.valueOf(parts[0]);
            TaskStatus taskStatus = TaskStatus.valueOf(parts[1]);
            String description = parts[2];

            Task task = createTaskByType(taskType, description, parts);
            if (taskStatus == TaskStatus.COMPLETED) {
                task.markAsDone();
            }
            return task;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing task: " + line, e);
        }
    }

    /**
     * Factory method to create tasks based on their type.
     *
     * @param taskType    The type of task.
     * @param description The task description.
     * @param parts       The serialized task parts.
     * @return The corresponding {@link Task} object.
     */
    private static Task createTaskByType(TaskType taskType, String description, String[] parts) {
        return switch (taskType) {
        case TODO -> new Todo(description);
        case DEADLINE -> new Deadline(description, parts[3]);
        case EVENT -> {
            String[] eventDuration = parts[3].split(" - ");
            yield new Event(description, eventDuration[0], eventDuration[1]);
        }
        };
    }

    /**
     * Serialises a {@link Task} into its string representation.
     * The format is: {@code TaskType | TaskStatus | Description | [Additional Details]}.
     *
     * @param task the {@link Task} to serialise
     * @return the string representation of the helix.task
     */
    private static String serialiseTask(Task task) {
        StringBuilder sb = new StringBuilder();

        // Task Type
        TaskType taskType = task.getTaskType();
        sb.append(taskType);
        sb.append(" | ");
        // Completion status
        sb.append(
                task.getTaskStatus() == TaskStatus.COMPLETED
                        ? TaskStatus.COMPLETED
                        : TaskStatus.PENDING
        );
        sb.append(" | ");
        // Task description
        sb.append(task.getDescription());
        // Task Details (if any)
        String taskDetails = task.getTaskDetails();
        if (taskType == TaskType.DEADLINE || taskType == TaskType.EVENT) {
            sb.append(" | ");
            sb.append(taskDetails);
        }
        return sb.toString();
    }
}
