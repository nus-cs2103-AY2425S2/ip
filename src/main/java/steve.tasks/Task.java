package steve.tasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Represents a general task with a description and a completion status.
 * <p>
 * This class serves as a base class for specific types of tasks (e.g., Event, Todo).
 * It provides functionality for marking tasks as done, deleting tasks, and saving tasks to a file.
 */
public class Task {
    private static int taskCount = 0;
    // Using a user-specific location that works in both IDE and JAR environments
    private static final String DATA_DIRECTORY = System.getProperty("user.home") + File.separator + "steve-data";
    private static final String FILE_NAME = "steve.txt";
    private static final String FILE_PATH = DATA_DIRECTORY + File.separator + FILE_NAME;

    private String description;
    private boolean done;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
        taskCount++;
    }

    /**
     * Returns the code representing the task type.
     *
     * @return A string representing the task type ("Task").
     */
    public String code() {
        return "Task";
    }

    /**
     * Returns a string representing the task's completion status.
     * If the task is done, it returns "X"; otherwise, it returns a space.
     *
     * @return A string indicating whether the task is done or not.
     */
    public String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return A string representing the task's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Displays a message indicating the task's completion status.
     *
     * @param word A string representing whether the task was marked as done or not.
     */
    public void message(String word) {
        String result = word.equals("mark")
                ? markTask()
                : unmarkTask();
        System.out.println(result);
    }

    /**
     * returns string message indicating task completion
     */
    public String markTask() {
        return "_________________________\n"
                + "     Nice! I've marked this task as done:\n"
                + "       [X] " + this.description + "\n"
                + "_________________________\n";
    }

    /**
     * returns string message indicating task incomplete
     */
    public String unmarkTask() {
        return "_________________________\n"
                + "     OK, I've marked this task as not done yet:\n"
                + "       [ ] " + this.description + "\n"
                + "_________________________\n";
    }

    /**
     * Marks the task as done or not done based on the given flag.
     *
     * @param isDone A boolean flag indicating whether the task should be marked as done or not.
     */
    public void markDone(boolean isDone) {
        this.done = isDone;
    }

    /**
     * Returns done status of task
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Returns the total number of tasks.
     *
     * @return The current task count.
     */
    public int taskCount() {
        return taskCount;
    }

    /**
     * Decreases the total task count by one.
     */
    public void decreaseTaskCount() {
        taskCount--;
    }

    /**
     * Returns a string representing the task in a list format, including its type, status, and description.
     *
     * @return A string representing the task in list format.
     */
    public String list() {
        return "." + " [" + this.code() + "]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Returns the type of the task.
     *
     * @return A string representing the task type ("Task: ").
     */
    public String type() {
        return "Task: ";
    }

    /**
     * Returns whether the task is valid. This base class always returns false.
     *
     * @return False, as this is a general task and not a specific task type.
     */
    public boolean isValid() {
        return false;
    }

    /**
     * Returns the original description of the task.
     *
     * @return The task's original description.
     */
    public String getOriginalDescription() {
        return this.description;
    }

    /**
     * Ensures the data directory exists
     */
    private static void ensureDataDirectoryExists() {
        File directory = new File(DATA_DIRECTORY);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Created data directory: " + DATA_DIRECTORY);
            } else {
                System.err.println("Failed to create data directory: " + DATA_DIRECTORY);
            }
        }
    }

    /**
     * Saves the task to a file.
     *
     * @param task The task to save.
     */
    public static void saveTasks(Task task) {
        ensureDataDirectoryExists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(task.type() + task.getOriginalDescription());
            writer.newLine(); // Add a new line after each task
            System.out.println("Tasks saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Saves list tasks to a file.
     */
    public static void saveAllTasks(List<Task> tasks) {
        ensureDataDirectoryExists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) { // No 'append' mode
            for (Task task : tasks) {
                writer.write(task.type() + task.getOriginalDescription());
                writer.newLine();
            }
            System.out.println("Tasks updated successfully!");
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }
}
