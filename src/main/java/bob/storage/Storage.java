package bob.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

/**
 * This class represents the storage of tasks.
 * It contains static methods to fetch and save tasks to a CSV file
 * on the user's home directory.
 * This includes:
 * <ul>
 *    <li> {@link #fetchTasksFromFile()} </li>
 *   <li> {@link #saveTasksToFile(List)} </li>
 * </ul>
 */
public class Storage {
    private static final String FILE_NAME = "tasks.csv";
    private static final String HEADER = "id,type,description,done,by,from,to";
    private static final Path DEFAULT_FILE_PATH = Paths.get(System.getProperty("user.home"), FILE_NAME);

    private static Path filePath = DEFAULT_FILE_PATH;

    static {
        createFileIfNotExist();
    }

    private static void createFileIfNotExist() {
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
                Files.write(filePath, HEADER.getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
            }
        }
    }

    /**
     * Helper method that converts a line from the CSV file to a Task object.
     *
     * @param line String.
     * @return Task object.
     * @throws IllegalArgumentException if the task type is not from {T,D,E}.
     */
    private static Task convertLineToTask(String line) throws IllegalArgumentException {
        String[] fields = line.split(",");
        String type = fields[1];
        String description = fields[2];
        boolean done = Boolean.parseBoolean(fields[3]);

        switch (type) {
        case "T":
            return new Todo(description, done);
        case "D":
            String byString = fields[4];
            LocalDateTime by = byString.isBlank()
                    ? null
                    : LocalDateTime.parse(byString);
            return new Deadline(description, by, done);
        case "E":
            String fromString = fields[5];
            String toString = fields[6];
            LocalDateTime from = fromString.isBlank()
                    ? null
                    : LocalDateTime.parse(fromString);
            LocalDateTime to = toString.isBlank()
                    ? null
                    : LocalDateTime.parse(toString);
            return new Event(description, from, to, done);
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }

    /**
     * Fetches tasks from the file and returns them as a list.
     * Returns empty list if encountered any IO error.
     *
     * @return List of tasks.
     */
    public static List<Task> fetchTasksFromFile() {
        try {
            List<Task> tasks = Files.lines(filePath)
                    .skip(1)
                    .map(Storage::convertLineToTask)
                    .collect(Collectors.toList());
            return tasks;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Saves the given list of tasks to the file.
     * Note that this method re-writes the CSV file entirely,
     * every time it is called.
     *
     * @param tasks List.
     * @throws IOException if error during file IO.
     */
    public static void saveTasksToFile(List<Task> tasks) throws IOException {
        String lines = Stream.concat(
                Stream.of(HEADER),
                tasks.stream().map(task -> (tasks.indexOf(task) + 1) + "," + task.toCsv())
                )
                .collect(Collectors.joining("\n"));
        Files.write(filePath, lines.getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Returns the file path of the storage file.
     * Used for testing purposes.
     *
     * @param filePath file path.
     */
    public static void setFilePathTo(Path filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Resets the file path to the default file path.
     * Used for testing purposes.
     */
    public static void resetFilePath() {
        Storage.filePath = DEFAULT_FILE_PATH;
    }
}
