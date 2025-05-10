package steve.ui;

import java.util.Scanner;

import javafx.application.Platform;
import steve.commands.Command;
import steve.commands.CommandCreate;
import steve.exceptions.InvalidCommandException;
import steve.tasks.TaskManager;

/**
 * Manages the UI flow of the chatbot, handling user input and processing commands.
 */
public class UserInterface {
    private TaskManager taskManager;
    private Scanner scanner;

    /**
     * Constructs a UserInterface with a specified TaskManager.
     *
     * @param taskManager the TaskManager used to manage tasks in the system
     */
    public UserInterface(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the chatbot UI, repeatedly prompts for user input and processes commands.
     * The loop continues until the user inputs "bye".
     */
    public void start() {
        Messages.greeting();
        String userInput = scanner.nextLine().trim();

        while (!userInput.equals("bye")) {
            try {
                Command command = CommandCreate.createCommand(userInput, taskManager);
                command.execute();
            } catch (InvalidCommandException e) {
                Messages.unknown();
            } catch (NumberFormatException e) {
                System.out.println(Messages.formatExceptionMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
            userInput = scanner.nextLine().trim();
        }
        Messages.goodbye();
    }

    public String getResponse(String input) {
        input = input.trim();
        if (input.isEmpty()) {
            return "Input cannot be empty. Please enter a valid command.";
        }

        if (input.equals("bye")) {
            Platform.exit();
        }

        try {
            Command command = CommandCreate.createCommand(input, taskManager);
            return command.execute(); // Assuming execute() returns a String response
        } catch (InvalidCommandException e) {
            return Messages.unknownString();
        } catch (NumberFormatException e) {
            return "Invalid number format!";
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }
}
