package clank.utility;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import clank.command.Command;
import clank.task.Task;
import clank.task.TaskList;

/**
 * Handles user interactions for the Clank chatbot.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a new Ui instance and initializes the scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.println("Greetings! I'm Clank, Ratchet's best friend!\n"
                + "I heard you need some help here.\n"
                + "How may I assist you?");
    }

    /**
     * Returns the welcome message displayed when Clank is launched.
     *
     * @return A string containing the introductory message from Clank.
     */
    public String getWelcomeMessage() {
        return "Greetings! I'm Clank, Ratchet's best friend!\n"
                + "I heard you need some help here.\n"
                + "How may I assist you?";
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message when loading tasks fails.
     */
    public void showLoadingError() {
        System.out.println("Oh no! I've failed to load tasks from the file. "
                + "Let me try to fix it with my wrench!");
    }

    /**
     * Displays a custom error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a list of tasks that match the search keyword.
     * If no tasks match, a message is displayed indicating no results were found.
     *
     * @param matchingTasks The list of tasks that contain the search keyword.
     */
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("Sorry, but I can't seem to find any task with this keyword!");
            return;
        }

        System.out.println("Here are the tasks I found in my database!\n"
                + "Take a look!");

        Stream.iterate(1, i -> i + 1)
                .limit(matchingTasks.size())
                .map(i -> i + ". " + matchingTasks.get(i - 1))
                .forEach(System.out::println);
    }

    /**
     * Displays a list of upcoming tasks to the user.
     * If the method is called during application launch, it provides a different introductory message.
     *
     * @param upcomingTasks The list of upcoming tasks to be displayed.
     * @param isLaunch {@code true} if this method is called at application startup,
     *                 {@code false} if it is called manually (e.g., from a reminder command).
     */
    public void showUpcomingTasks(ArrayList<Task> upcomingTasks, boolean isLaunch) {
        if (upcomingTasks.isEmpty()) {
            if (!isLaunch) {
                System.out.println("You have no upcoming tasks!");
            }
            return;
        }

        System.out.println(isLaunch ? "By the way, you have some upcoming tasks!"
                                    : "Here are your upcoming tasks!");

        Stream.iterate(1, i -> i + 1)
                .limit(upcomingTasks.size())
                .map(i -> i + ". " + upcomingTasks.get(i - 1))
                .forEach(System.out::println);
    }

    /**
     * Reads a command from user input.
     *
     * @return The user input as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes the Scanner to free resources.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Executes the given command, capturing its output and returning it as a string.
     *
     * @param command The command to be executed.
     * @param taskList The task list that the command may modify.
     * @param storage The storage system used for saving or loading tasks.
     * @return A string containing the command's output.
     *         If an error occurs, returns an error message.
     */
    public String executeCommand(Command command, TaskList taskList, Storage storage) {
        assert taskList != null : "TaskList should not be null.";
        assert storage != null : "Storage should not be null.";

        StringBuilder response = new StringBuilder();
        try {
            PrintStream originalOut = System.out;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream captureStream = new PrintStream(outputStream);
            System.setOut(captureStream);

            command.execute(taskList, this, storage);

            System.setOut(originalOut);
            response.append(outputStream);
        } catch (Exception e) {
            response.append("An error occurred: ").append(e.getMessage());
        }
        return response.toString().trim();
    }
}
