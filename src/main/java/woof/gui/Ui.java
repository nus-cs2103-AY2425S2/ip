package woof.gui;

public interface Ui {
    /**
     * Displays a welcome message when the application starts.
     */
    public void showWelcome();

    /**
     * Displays an error message to the user.
     *
     * @param error The error message to display.
     */
    public void showError(String error);

    /**
     * Displays the list of tasks to the user.
     */
    public void displayTaskList();

    public void displayFind(String find);

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task        The task that was added.
     * @param totalTasks  The total number of tasks after adding the new task.
     */
    public void displayTaskAdded(String task, int totalTasks);

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void displayTaskMarked(String task);

    /**
     * Displays a message confirming that a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void displayTaskUnmarked(String task);

    /**
     * Displays a message confirming that a task has been deleted.
     *
     * @param task        The task that was deleted.
     * @param totalTasks  The total number of tasks after deleting the task.
     */
    public void displayTaskDeleted(String task, int totalTasks);

    /**
     * Displays a message confirming that all tasks have been cleared.
     */
    public void displayTasksCleared();

    /**
     * Displays a goodbye message when the application exits.
     */
    public void displayGoodbye();

    public void displayHelp();
}
