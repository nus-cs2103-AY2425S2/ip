package jimmy;

import jimmy.tasks.TaskList;

/**
 * The {@code Jimmy} class serves as the core logic handler for the chatbot application.
 * It initializes the storage and task list, and provides access to tasks and storage for the GUI.
 */
public class Jimmy {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a {@code Jimmy} instance with the specified file path for data storage.
     * Loads tasks from storage and handles any loading errors gracefully.
     *
     * @param filePath the path to the file where tasks are stored.
     */
    public Jimmy(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JimmyException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Retrieves the current task list managed by Jimmy.
     *
     * @return the {@code TaskList} containing all current tasks.
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Retrieves the storage instance used by Jimmy to save and load tasks.
     *
     * @return the {@code Storage} instance.
     */
    public Storage getStorage() {
        return storage;
    }
}
