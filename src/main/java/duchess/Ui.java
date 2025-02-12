package duchess;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interactions, including displaying messages and reading user input.
 */
public class Ui {
    private String logo = "   ___                    _                              \n"
            + "  |   \\   _  _     __    | |_      ___     ___     ___   \n"
            + "  | |) | | +| |   / _|   | ' \\    / -_)   (_-<    (_-<   \n"
            + "  |___/   \\_,_|   \\__|_  |_||_|   \\___|   /__/_   /__/_  \n"
            + "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n"
            + "`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\"`-0-0-\'\n";
    private String chatbotName = "Duchess";
    private String chatLine = "________________________________\n";
    private String greetingMsg = "Hello! I'm " + chatbotName + "\n"
                    + "What can I do for you?";
    private String exitMsg = "Bye. Hope to see you again soon!\n";

    private Scanner scanner;

    /**
     * Constructs a Ui instance and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a horizontal line as a separator.
     */
    public void showLine() {
        System.out.println(chatLine);
    }

    /**
     * Displays the chatbot logo and greeting message.
     */
    public void showGreeting() {
        System.out.println(logo);
        showLine();
        System.out.println(greetingMsg);
        showLine();
    }

    /**
     * Displays the exit message before closing the application.
     */
    public void showExitMessage() {
        System.out.println(exitMsg);
        showLine();
    }

    /**
     * Exits the program by showing the exit message and closing the scanner.
     */
    public void exit() {
        showExitMessage();
        this.scanner.close();
    }

    /**
     * Displays an error message.
     *
     * @param s The error message to be displayed.
     */
    public void showError(String s) {
        System.out.println(s);
        showLine();
    }

    /**
     * Reads the user's command from the input.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        String s = scanner.nextLine();
        showLine();
        return s;
    }

    /**
     * Prints the list of tasks stored in the TaskList.
     *
     * @param taskList The TaskList containing tasks to be displayed.
     */
    public void printList(TaskList taskList) {
        for (int i = 0; i < taskList.size(); ++i) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        showLine();
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task The task that was added.
     */
    public void showAddedTask(String task) {
        System.out.println("added: " + task);
        showLine();
    }

    /**
     * Displays a message when a task is marked as completed.
     */
    public void showItemMarked() {
        System.out.println("Item marked!");
        showLine();
    }

    /**
     * Displays a message when a task is unmarked (set as not completed).
     */
    public void showItemUnmarked() {
        System.out.println("Item unmarked!");
        showLine();
    }

    /**
     * Displays a message when a task is deleted from the list.
     */
    public void showItemDeleted() {
        System.out.println("Item deleted!");
        showLine();
    }
    /**
     * Displays a list of tasks that match the search keyword.
     *
     * @param matchingTasks A list of tasks that match the search criteria.
     */
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); ++i) {
            System.out.println((i + 1) + ". " + matchingTasks.get(i));
        }
        showLine();
    }
}
