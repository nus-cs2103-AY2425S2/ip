package qwerty.ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import qwerty.commands.BotCommand;
import qwerty.exceptions.BotException;
import qwerty.task.Task;

/**
 * Initializes the bot with a given file path, handles user input, executes commands,
 * and displays appropriate messages.
 */
public class Qwerty {

    private Storage storage; // Manages reading and saving tasks to file.
    private TaskList tasks; // List of tasks managed by the bot.
    private Ui ui; // Handles user interactions and displays messages.

    /**
     * Constructs a Qwerty bot with the specified file path for storing tasks.
     * Initializes the UI, Storage, and TaskList, loading tasks from the given file.
     * If loading tasks fails, an empty task list is created.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Qwerty(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            ArrayList<Task> list = storage.readListFromFile(); // Reads saved tasks from file.
            this.tasks = new TaskList(list);
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Prints any exception that occurs during loading.
            this.tasks = new TaskList(new ArrayList<>()); // Initializes with an empty task list.
        }
    }

    /**
     * Starts the Qwerty bot, displaying a welcome message and running the command loop.
     * Continuously accepts user input, parses commands, executes the corresponding actions,
     * and displays feedback. The loop terminates when the user inputs the 'bye' command.
     */
    public void run() {
        ui.showWelcome(); // Displays a welcome message.
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(); // Reads the user's command.
                ui.showLine();
                BotCommand c = Parser.parse(fullCommand); // Parses the command and creates the BotCommand.
                c.execute(tasks, ui, storage); // Executes the command.
                isExit = c.isBye(); // Checks if the 'bye' command was entered to exit the loop.
            } catch (BotException e) {
                System.out.println(e); // Prints error message if any BotException occurs.
            } finally {
                if (!isExit) {
                    ui.showLine(); // Shows a separator line after each command execution.
                }
            }
        }
        ui.closeScanner(); // Closes the scanner to clean up resources.
    }

    /**
     * The main entry point for the Qwerty bot application.
     * Initializes the bot with a specified file path and starts it.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Qwerty("./data/Qwerty.txt").run(); // Starts the Qwerty bot with a file path for storage.
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        // Capture System.out.println output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out; // Save original System.out

        System.setOut(printStream); // Redirect System.out

        try {
            BotCommand c = Parser.parse(input); // Parses the command and creates the BotCommand.
            c.execute(tasks, ui, storage);
            return outputStream.toString().trim();
        } catch (BotException b) {
            return b.toString();
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            System.setOut(originalOut); // Restore System.out
        }
    }
}
