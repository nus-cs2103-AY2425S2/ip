package gptzerofive.ui;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a new Ui instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public String showWelcome() {
        String helloString = """
                Hello! I'm GPT0.5.
                What can I do for you today?
                """;
        return formattedPrint(helloString);
    }

    /**
     * Displays the goodbye message.
     */
    public String showGoodbye() {
        String goodbyeString = """
                Goodbye! Have a nice day!""";
        return formattedPrint(goodbyeString);
    }

    /**
     * Reads the user input command.
     *
     * @return The user input command.
     */
    public String readCommand() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Displays a line separator.
     */
    public String showLine() {
        return ("\t---------------------------------------------------");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public String showError(String message) {
        assert message != null : "Error message should not be null";
        return formattedPrint(message);
    }

    /**
     * Displays the task list.
     *
     * @param taskList The task list to display.
     */
    public String showTaskList(String taskList) {
        assert taskList != null : "Task list should not be null";
        return formattedPrint("Here are the tasks in your list:\n" + taskList);
    }

    /**
     * Formats and prints a message.
     *
     * @param message The message to format and print.
     */
    public String formattedPrint(String message) {
        assert message != null : "Message should not be null";
        Scanner scanner = new Scanner(message);
        String resultString = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            resultString += "\t" + line + "\n";
        }
        scanner.close();

        return resultString;
    }

    /**
     * Formats and prints a message.
     *
     * @param taskList The message to format and print. Take note that the parameter
     *                 is named tasklist, but is actually a string.
     */
    public String printFilteredTaskList(String taskList) {
        assert taskList != null : "Task list should not be null";
        return "Here are the matching tasks in your list:\n" + taskList;
    }
}
