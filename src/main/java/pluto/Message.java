package pluto;

/**
 * Represents a Message class. This class
 * is in charge of displaying messages from Pluto
 */
public class Message {

    /**
     * Displays the welcome message when bot is started up
     */
    public String showWelcomeMessage() {
        String greeting = "Hi! I am Pluto\n" + "What can I do for you today? \n\n";
        return greeting;
    }

    /**
     * Displays goodbye message when bot is exited
     */
    public String showGoodbyeMessage() {
        String goodbye = "Bye! Hope to see you again :)";
        return goodbye;
    }

    /**
     * Displays message that task is added to the list
     * Displays the new number of tasks in the list
     * @param task the Task to be added
     * @param size an int that shows the new number of tasks in the list
     */
    public String addTaskMessage(Task task, int size) {
        String message = "Got it. I've added this task:\n";
        message += " " + task.toString();
        message += "\nNow you have " + size + " tasks in your list";
        return message;
    }

    /**
     * Lists the current task
     * @param task the current Task
     * @param index the index of the Task in the list
     */
    public String listTaskMessage(Task task, int index) {
        return index + ". " + task.toString() +"\n";
    }

    /**
     * Displays the task's status after being marked completed
     * @param task the Task that is marked completed
     */
    public String markTaskMessage(Task task) {
        return "Nice! I've marked this task as done:\n"
                + task.taskStatusMessage();
    }

    /**
     * Displays the task's status after being marked as undone
     * @param task the Task to be marked as undone
     */
    public String unmarkTaskMessage(Task task) {
        return "Ok, I've marked this task as not done yet:\n"
                + task.taskStatusMessage();
    }

    /**
     * Displays the message and task list size after
     * a task is removed
     * @param task the Task to be removed
     * @param size the size of the task list after
     *             the specified task is removed
     */
    public String removeTaskMessage(Task task, int size) {
        String message = "Noted. I've removed this task:\n";
        message += task.toString();
        message += "\nNow you have " + size + " tasks in your list";
        return message;
    }

    public static String newListMessage() {
        return "No existing saved file found, creating a new empty list";
    }

    public static String showErrorMessage(String message) {
        return message;
    }
}
