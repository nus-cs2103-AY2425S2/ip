package carolyn;

import java.util.Scanner;

/**
 * Handles user interactions by providing formatted messages for various actions.
 */
public class Ui {
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the goodbye message to the user.
     *
     * @return A string containing the goodbye message.
     */
    public String sayGoodBye() {
        String bye = " Bye. Hope to see you again soon!\n";
        return(bye);
    }

    /**
     * Returns a message indicating a task has been marked as done.
     *
     * @param t The {@link Task} that has been marked as done.
     * @return A string containing the task completion message.
     */
    public String printForMark(Task t) {
        String s = " Nice! I've marked this task as done:\n" + "   " + t.toString();
        return s;
    }

    /**
     * Returns a message indicating a task has been unmarked (marked as not done).
     *
     * @param t The {@link Task} that has been unmarked.
     * @return A string containing the task unmarking message.
     */
    public String printForUnmark(Task t) {
        String s = " OK, I've marked this task as not done yet:\n" + "   " + t.toString();
        return s;
    }

    /**
     * Returns a message indicating a task has been deleted from the task list.
     * <p>
     * Prints a formatted message showing that the specified task has been removed,
     * along with the updated number of tasks in the list.
     * </p>
     *
     * @param t     The {@link Task} that has been deleted.
     * @param tasks  The {@link TaskList} from which the task was deleted.
     * @return A string containing the task deletion message and the updated task count.
     */
    public String printForDelete(Task t, TaskList tasks) {
        String s = " Noted. I've removed this task:\n" + "   " + t.toString() + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.\n";
        return s;
    }

    /**
     * Returns a message indicating a new task has been added to the task list.
     * <p>
     * Prints a formatted message showing that the specified task has been added,
     * along with the updated number of tasks in the list.
     * </p>
     *
     * @param t     The {@link Task} that has been added.
     * @param tasks  The {@link TaskList} to which the task was added.
     * @return A string containing the task addition message and the updated task count.
     */
    public String printForAddTask(Task t, TaskList tasks) {
        String s = " Got it. I've added this task:\n"
                + "   " + t.toString() + "\n" + " Now you have " + tasks.size() + " tasks in the list.\n";
        return s;
    }

    public String printForAddTag(Task t, String tagString) {
        String s = " Got it. I've added this tag \"" + tagString
                + "\" to the task:\n" + t.toString();
        return s;
    }

    /**
     * Returns an exception message to the user.
     *
     * @param e The {@link Exception} containing the error message to display.
     * @return A string containing the exception message.
     */
    public String printException(Exception e) {
        return(e.getMessage());
    }

    /**
     * Returns the entire task list as a string to the user.
     *
     * @param tasks The {@link TaskList} containing tasks to display.
     * @return A string representing the entire task list.
     */
    public String printTaskList(TaskList tasks) {
        return(tasks.toString());
    }

}