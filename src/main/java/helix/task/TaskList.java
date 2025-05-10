package helix.task;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import helix.enums.OutputSymbol;
import helix.storage.Storage;
import helix.ui.ConsoleUi;

/**
 * Represents a helix.task manager that maintains a list of tasks, allowing for operations such as adding,
 * removing, and marking tasks as done or undone. The TaskList automatically synchronizes changes
 * with a helix.storage backend.
 */
public class TaskList {
    private final List<Task> tasks;
    private final Storage storage;

    /**
     * Constructs a TaskList and initializes it with tasks loaded from the specified helix.storage.
     *
     * @param storage the Storage object responsible for saving and loading tasks
     * @throws IOException if an error occurs while loading tasks from helix.storage
     */
    public TaskList(Storage storage) throws IOException {
        this.storage = storage;
        this.tasks = storage.load();
    }

    /**
     * Adds a new helix.task to the TaskList and updates the helix.storage.
     *
     * @param task the {@link Task} to be added
     * @param consoleUi the {@link ConsoleUi} component used to display messages to the user
     */
    public void addTask(Task task, ConsoleUi consoleUi) {
        this.tasks.add(task);
        updateStorage(consoleUi);
    }

    /**
     * Removes a helix.task from the TaskList by its index and updates the helix.storage.
     *
     * @param taskIndex the index of the helix.task to be removed (0-based index)
     * @param consoleUi the {@link ConsoleUi} component used to display messages to the user
     * @return the {@link Task} that was removed
     * @throws IndexOutOfBoundsException if the {@code taskIndex} is out of range
     */
    public Task removeTask(int taskIndex, ConsoleUi consoleUi) {
        Task task = this.tasks.remove(taskIndex);
        updateStorage(consoleUi);
        return task;
    }

    /**
     * Updates the details of an existing task and synchronizes changes with storage.
     *
     * @param taskIndex the index of the task to update (0-based index).
     * @param newDetails the new details of the task (format varies based on task type).
     * @param consoleUi the {@link ConsoleUi} component used to display messages to the user.
     */
    public void updateTask(int taskIndex, String newDetails, ConsoleUi consoleUi) {
        Task task = this.tasks.get(taskIndex);
        task.updateTaskDetails(newDetails);
        updateStorage(consoleUi);
    }

    /**
     * Marks a helix.task as done based on its index and updates the helix.storage.
     *
     * @param taskIndex the index of the helix.task to mark as done (0-based index)
     * @param consoleUi the {@link ConsoleUi} component used to display messages to the user
     * @throws IndexOutOfBoundsException if the {@code taskIndex} is out of range
     */
    public void markTaskAsDone(int taskIndex, ConsoleUi consoleUi) {
        Task task = this.tasks.get(taskIndex);
        task.markAsDone();
        updateStorage(consoleUi);
    }

    /**
     * Marks a helix.task as not done based on its index and updates the helix.storage.
     *
     * @param taskIndex the index of the helix.task to mark as not done (0-based index)
     * @param consoleUi the {@link ConsoleUi} component used to display messages to the user
     * @throws IndexOutOfBoundsException if the {@code taskIndex} is out of range
     */
    public void markTaskAsUndone(int taskIndex, ConsoleUi consoleUi) {
        Task task = this.tasks.get(taskIndex);
        task.markAsUndone();
        updateStorage(consoleUi);
    }

    /**
     * Retrieves an unmodifiable view of the current list of tasks.
     *
     * @return an unmodifiable List of tasks
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(this.tasks);
    }

    /**
     * Retrieves the total number of tasks in the TaskList.
     *
     * @return the number of tasks in the TaskList
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Retrieves a specific helix.task from the TaskList by its index.
     *
     * @param taskIndex the index of the helix.task to retrieve (0-based index)
     * @return the helix.task at the specified index
     * @throws IndexOutOfBoundsException if the taskIndex is out of range
     */
    public Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex);
    }

    /**
     * Updates the current state of the tasks to the helix.storage. If saving fails, logs a warning message.
     *
     * @param consoleUi the {@link ConsoleUi} component used to display messages to the user
     */
    private void updateStorage(ConsoleUi consoleUi) {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            String warningSymbol = OutputSymbol.WARNING.getSymbol();
            consoleUi.showSavingStorageError(e.getMessage());
        }
    }
}
