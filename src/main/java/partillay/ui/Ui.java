package partillay.ui;

import java.util.ArrayList;
import java.util.Scanner;

import partillay.task.Task;
import partillay.task.TaskList;

/**
 * Represents the user interface (UI) that handles input and output interactions with the user.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "________________________________________________________";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Reads the next line of input from the user.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user when the program starts.
     */
    public String getWelcomeMessage() {
        String result = "Hello! I'm Partillay, your ABSOLUTELY greatest bestie.\n";
        result += "Anything I CAN HELP?";
        return getLinedMessage(result);
    }

    /**
     * Displays a goodbye message to the user when the program ends.
     */
    public String getGoodbyeMessage() {
        return getLinedMessage("Bye. See you later! Slay!");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to be displayed
     */
    public String getErrorMessage(String message) {
        return getLinedMessage(message);
    }

    /**
     * Prints a horizontal line to the console for separation between outputs.
     */
    public String getLine() {
        return HORIZONTAL_LINE;
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks the TaskList containing the tasks to be displayed
     */
    public String getTaskListTasks(TaskList tasks) {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        ArrayList<Task> tasksToShow = tasks.getTasks();
        for (int i = 0; i < tasksToShow.size(); i++) {
            result.append((i + 1)).append(". ").append(tasksToShow.get(i)).append("\n");
        }
        return getLinedMessage(result.toString());
    }

    /**
     * Displays a message to the user.
     *
     * @param message the message to be displayed
     */
    public String getLinedMessage(String message) {
        return getLine() + "\n" + message + "\n" + getLine() + "\n";
    }
}
