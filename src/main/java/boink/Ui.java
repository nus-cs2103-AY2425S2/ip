package boink;

import boink.exceptions.BoinkException;
import boink.exceptions.InvalidIndexException;
import boink.exceptions.InvalidTaskInputException;
import boink.tasks.Task;

/**
 * This class deals with interactions with the user.
 */

public class Ui {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Ui class
     * @param storage Storage.
     * @param tasks TaskList.
     */

    public Ui(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Displays a welcome message.
     *
     * @return A string greeting the user with a friendly message.
     */

    public String showWelcome() {
        return "Hello! I'm Boink!\n" + "What can I do for you?\n";
    }

    /**
     * Displays an error message with the provided exception details.
     *
     * @param err The exception message or error details to display.
     * @return A string containing the error message prefixed with "Please try again.".
     */

    public String showError(String err) {
        return "Please try again. " + err + "\n";
    }

    /**
     * Displays an exit message when the user exits the application.
     *
     * @return A string informing the user that the application is exiting.
     */

    public String showExit() {
        return "Exiting from Boink...\n";
    }

    /**
     * Lists all the tasks stored in the system.
     *
     * @return A string representation of all the tasks in the task list.
     */

    public String listTasks() {
        return tasks.printTasks();
    }

    /**
     * Marks task as completed based on index provided.
     * @param input User input containing command and index of task to mark.
     * @return Task that was marked.
     * @throws InvalidTaskInputException If index is not a valid integer.
     * @throws InvalidIndexException If index is out of range.
     */

    public String markTask(String input) throws InvalidTaskInputException, InvalidIndexException {
        int index = this.getIndexFromInput(input);
        String output = tasks.markTask(index);
        storage.saveTasksToFile(tasks);
        return output;
    }

    /**
     * Unmarks task as not completed based on index provided.
     * @param input User input containing command and index of task to unmark.
     * @return Task that was unmarked.
     * @throws InvalidTaskInputException If index is not a valid integer.
     * @throws InvalidIndexException If index is out of range.
     */

    public String unmarkTask(String input) throws InvalidTaskInputException, InvalidIndexException {
        int index = this.getIndexFromInput(input);
        String output = tasks.unmarkTask(index);
        storage.saveTasksToFile(tasks);
        return output;
    }

    /**
     * Deletes task based on index provided.
     * @param input User input containing command and index of task to delete.
     * @return Task that was deleted.
     * @throws InvalidTaskInputException If index is not a valid integer.
     * @throws InvalidIndexException If index is out of range.
     */

    public String deleteTask(String input) throws InvalidTaskInputException, InvalidIndexException {
        int index = this.getIndexFromInput(input);
        String output = tasks.deleteTask(index);
        storage.saveTasksToFile(tasks);
        return output;
    }

    /**
     * Filters tasks containing keyword.
     * @param input Keyword to filter.
     * @return List of tasks containing keyword.
     */

    public String findTasks(String input) {
        String[] parts = input.split(" ");
        String word = parts[1].trim();
        String output = tasks.findWord(word);
        return output;
    }

    /**
     * Creates task from input and adds to TaskList.
     * @param input String containing task details.
     * @return Print output of task that was created.
     * @throws BoinkException If user did not state task details or invalid format.
     */

    public String addTask(String input) throws BoinkException {
        Task newTask = Parser.createTaskFromInput(input);
        assert (newTask != null) : "Task should not be null";
        String output = tasks.addTask(newTask);
        storage.saveTasksToFile(tasks);
        return output;
    }

    /**
     * Archives all tasks into archive file.
     * @return Print output indicating if tasks archived successfully.
     */

    public String archiveTasks() {
        storage.archiveTasksToFile(tasks);
        String output = tasks.archiveAllTasks();
        storage.saveTasksToFile(tasks);
        return output;
    }

    /**
     * Checks if String provided can be parsed into integer.
     * @param input String to be parsed.
     * @return Index integer if string is parsable
     * @throws InvalidTaskInputException if index is empty or not an integer.
     */

    private int getIndexFromInput(String input) throws InvalidTaskInputException {
        try {
            String[] parts = input.split(" ");
            if (parts.length < 2) {
                throw new InvalidTaskInputException("Please enter an index.");
            }
            return Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException err) {
            throw new InvalidTaskInputException("Index given is not an integer.");
        }
    }
}
