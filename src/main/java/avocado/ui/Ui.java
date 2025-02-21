package avocado.ui;

import avocado.task.Task;
import avocado.task.TaskList;
import java.util.Scanner;

/**
 * Represents the user interface of Avocado.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the welcome message.
     */
    // public void showWelcome() {
    //     String logo = "     _             \n"
    //             + "    / \\__    ____ \n"
    //             + "   / _ \\ \\  / /  \\ \n"
    //             + "  / ___ \\ \\/ / |  |\n"
    //             + " /_/   \\_\\__/\\___/ \n";

    //     System.out.println("____________________________________________________________");
    //     System.out.println("Hello from\n" + logo);
    //     System.out.println("Hello! I'm Avocado");
    //     System.out.println("What can I do for you?");
    //     System.out.println("____________________________________________________________");
    // }

    public String showWelcome() {
        // String logo = "     _             \n"
        //         + "    / \\__    ____ \n"
        //         + "   / _ \\ \\  / /  \\ \n"
        //         + "  / ___ \\ \\/ / |  |\n"
        //         + " /_/   \\_\\__/\\___/ \n";
        // String logo = "Avocado";
        // return "Hello from\n" + logo
        return "Hello! I'm Avocado\n"
                + "What can I do for you?\n";
    }

    /**
     * Reads the command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    // public void showLine() {
    //     System.out.println("____________________________________________________________");
    // }       

    public String showLine() {
        return "____________________________________________________________";
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    // public void showError(String message) {
    //     System.out.println(" " + message);
    // }

    public String showError(String message) {
        return message;
    }



    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    // public void showMarkedAsDone(Task task) {
    //     System.out.println("____________________________________________________________");
    //     System.out.println(" Nice! I've marked this task as done:");
    //     System.out.println("   " + task);
    //     // System.out.println("____________________________________________________________");
    // }

    public String showMarkedAsDone(Task task) {
        return " Nice! I've marked this task as done:\n"
                + "   " + task;
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    // public void showMarkedAsUndone(Task task) {
    //     System.out.println("____________________________________________________________");
    //     System.out.println(" Nice! I've marked this task as not done:");
    //     System.out.println("   " + task);
    //     // System.out.println("____________________________________________________________");
    // }

    public String showMarkedAsUndone(Task task) {
        return " Nice! I've marked this task as not done:\n"
                + "   " + task;
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    // public void showTaskAdded(Task task, TaskList tasks) {
    //     System.out.println("____________________________________________________________");
    //     System.out.println(" Got it. I've added this task:");
    //     System.out.println("   " + task);
    //     System.out.println(" Now you have " + tasks.getTasks().size() + " tasks in the list.");
    //     // System.out.println("____________________________________________________________");
    // }

    public String showTaskAdded(Task task, TaskList tasks) {
        return " Got it. I've added this task:\n"
                + "   " + task + "\n"
                + " Now you have " + tasks.getTasks().size() + " tasks in the list.";
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    // public void showTaskDeleted(Task task, TaskList tasks) {
    //     System.out.println("____________________________________________________________");
    //     System.out.println(" Noted. I've removed this task:");
    //     System.out.println("   " + task);
    //     System.out.println(" Now you have " + tasks.getTasks().size() + " tasks in the list.");
    //     // System.out.println("____________________________________________________________");
    // }

    public String showTaskDeleted(Task task, TaskList tasks) {
        return " Noted. I've removed this task:\n"
                + "   " + task + "\n"
                + " Now you have " + tasks.getTasks().size() + " tasks in the list.";
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    // public void showGoodbye() {
    //     System.out.println("____________________________________________________________");
    //     System.out.println("Bye. Hope to see you again soon!");
    //     // System.out.println("____________________________________________________________");
    // }

    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }
}
