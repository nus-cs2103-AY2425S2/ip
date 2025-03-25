package buddy.display;

import java.util.Scanner;

import buddy.command.CommandType;
import buddy.exception.BuddyException;
import buddy.task.Task;
import buddy.task.TaskList;

/**
 * Represent Display function for chatbot
 */
public class Display {
    private static final String BORDER = "_________________________________________";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Returns string representation of greeting response.
     */
    public static String greet() {
        return String.format(Display.BORDER + "\n"
                + "Hello! I'm Buddy\n" + "What can I do for you?\n"
                + Display.BORDER);
    }

    /**
     * Returns string representation of bye response.
     */
    public static String bye() {
        return String.format(Display.BORDER + "\n"
                + " Bye. Hope to see you again soon!\n"
                + Display.BORDER);
    }

    /**
     * Returns string response of markTask command.
     *
     * @param task Task that needs marking as done.
     * @return String response of markTask command.
     */
    public static String markTask(Task task) {
        return Display.BORDER + "\n Nice! I've marked this task as done:\n"
                + String.format(" %s\n", task.toString()) + Display.BORDER;
    }

    /**
     * Returns string response of unmarkTask command.
     *
     * @param task Task that needs unmarking as done.
     * @return String response of unmarkTask command.
     */
    public static String unmarkTask(Task task) {
        return Display.BORDER + "\n OK, I've marked this task as not done yet:\n"
                + String.format(" %s\n", task.toString()) + Display.BORDER;
    }

    /**
     * Returns string response of addTask command.
     *
     * @param task     Task that needs marking as done.
     * @param taskList The current task list.
     * @return String response of addTask command.
     */
    public static String addTask(Task task, TaskList taskList) {
        String res = Display.BORDER + String.format("\n  Got it. I've added this task:\n  %s",
                task.toString());
        if (taskList.getLength() > 1) {
            res = res + String.format("\n  Now you have %s tasks in the list.\n", taskList.getLength());
        } else {
            res = res + String.format("\n  Now you have %s task in the list.\n", taskList.getLength());
        }
        res += Display.BORDER;
        return res;
    }

    /**
     * Returns string response of listTasks command.
     *
     * @param taskList the current task list
     * @return String response of listTasks command.
     */
    public static String listTasks(TaskList taskList) {
        if (taskList.getLength() == 0) {
            return "You don't have any tasks in the list at the moment";
        }
        StringBuilder result = new StringBuilder();
        result.append(Display.BORDER + "\n  Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getLength(); i += 1) {
            result.append(String.format("  %s.%s\n", i + 1,
                    taskList.getTask(i).toString()));
        }
        return result + Display.BORDER;
    }

    /**
     * Returns String error representation of exception.
     *
     * @param error Exception.
     * @return String error representation of exception.
     */
    public static String showError(BuddyException error) {
        return Display.BORDER + "\n " + error.toString() + "\n" + Display.BORDER;
    }

    /**
     * Returns string response of deleteTask command.
     *
     * @param task     Task that needs deleting
     * @param taskList The current task list
     * @return String response of deleteTask command.
     */
    public static String deleteTask(Task task, TaskList taskList) {
        return Display.BORDER + String.format("\n  Noted. I've removed this task:\n  %s",
                task.toString())
                + String.format("\n  Now you have %s tasks in the list.\n", taskList.getLength())
                + Display.BORDER;
    }

    /**
     * Returns string response of filter command.
     *
     * @param filteredTaskList the filtered task list
     * @return String response of filter command
     */
    public static String filterTask(TaskList filteredTaskList) {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (int i = 0; i < filteredTaskList.getLength(); i++) {
            result.append(String.format("%d. %s\n", counter++, filteredTaskList.getTask(i).toString()));
        }
        return Display.BORDER + "\nHere are the matching results in your list:\n" + result + Display.BORDER;
    }

    /**
     * Returns string response of update command.
     *
     * @param task the task
     * @return string response of update command
     */
    public static String updateTask(Task task) {
        return Display.BORDER + String.format("\n  Noted. I've edited this task:\n  %s\n",
                task.toString())
                + Display.BORDER;
    }

    /**
     * Returns string response of help command.
     *
     * @return string response of help command
     */
    public static String help() {
        StringBuilder result = new StringBuilder();

        for (CommandType command : CommandType.values()) {
            result.append(command.name()).append(": ").append(command.toString()).append("\n");
        }

        return Display.BORDER + "\n" + result + Display.BORDER;
    }


    /**
     * Returns the next input in the newline.
     */
    public String readInput() {
        return this.scanner.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void closeInput() {
        this.scanner.close();
    }
}
