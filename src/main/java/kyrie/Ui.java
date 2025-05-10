package kyrie;

/**
 * Represents the user interface.
 */
public class Ui {
    private String name;
    private String response;

    /**
     * Constructor for Ui.
     * 
     * @param name The name of the user interface.
     */
    public Ui(String name) {
        this.name = name;
    }

    /**
     * Gets the response.
     * 
     * @return response The response.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Sets the response.
     * 
     * @param response The response to set.
     */
    private void setResponse(String response) {
        this.response = response;
    }

    public void showError(Exception e) {
        this.setResponse("There seems to be something wrong\n" + e.getMessage());
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        this.setResponse("Shalom aleikhim, I am " + name + "\nTell me what you desire");
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        this.setResponse("Farewell, may you find what you seek");
    }

    /**
     * Shows the tasks in the task list.
     * 
     * @param tasks The task list to show.
     */
    public void showTasks(TaskList tasks) {
        String response = "Here are the tasks in your list:\n";
        for (int i = 1; i <= tasks.getTaskCount(); i++) {
            response += i + ". " + tasks.getTask(i) + "\n";
        }
        this.setResponse(response);
    }

    /**
     * Shows the marked task.
     * 
     * @param task The task to show.
     */
    public void showMarkedTask(Task task) {
        this.setResponse("Excellent, you have finished this task:\n\t" + task);
    }

    /**
     * Shows the unmarked task.
     * 
     * @param task The task to show.
     */
    public void showUnmarkedTask(Task task) {
        this.setResponse("You have undone your choice, that task is no longer marked finished:\n\t" + task);
    }

    /**
     * Shows the added task.
     * 
     * @param task The task to show.
     */
    public void showAddedTask(Task task) {
        this.setResponse("added a new task: " + task);
    }

    /**
     * Shows the deleted task.
     * 
     * @param task The task to show.
     * @param tasks The task list to show the number of tasks remaining.
     */
    public void showDeletedTask(Task task, TaskList tasks) {
        this.setResponse("Noted, I have removed this task:\n\t" + task + "\nNow you have " + tasks.getTaskCount() + " tasks remaining");
    }

    /**
     * Shows the found tasks.
     * 
     * @param tasks The tasks to show.
     * @return The found tasks.
     */
    public void showFoundTasks(TaskList tasks) {
        String response = "Here are the tasks matching your keyword:\n";
        for (int i = 1; i <= tasks.getTaskCount(); i++) {
            response += i + ". " + tasks.getTask(i) + "\n";
        }
        this.setResponse(response);
    }

    /**
     * Shows the tagged task.
     * 
     * @param task The task to show.
     */
    public void showTaggedTask(Task task) {
        this.setResponse("You have successfully tagged this task: " + task);
    }
}