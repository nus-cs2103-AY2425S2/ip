package nguyen;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Enum representing different task types: TODO, DEADLINE, and EVENT.
 */
enum TaskType {
    TODO, DEADLINE, EVENT
}

/**
 * Main class for the chatbot "Nguyen".
 * Manages user interaction, task management, and data storage.
 */
public class Nguyen {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private boolean isExit;
    /**
     * Default constructor that initializes the chatbot with default configurations.
     */
    public Nguyen() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage("data/ChatBot.txt");
        isExit = false;
    }

    /**
     * Constructor that initializes the chatbot with a specified storage file path.
     * Attempts to load tasks from the file.
     *
     * @param filePath Path to the storage file.
     */
    public Nguyen(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            System.out.println(storage.load());
            tasks = new TaskList(storage.load());
        } catch (NguyenException e) {
            System.out.println("why it wrong??");
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot, accepting user input and executing corresponding commands.
     * Ends the program when the 'bye' command is given.
     */
    public void run() {
        ui.showWelcome();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                if (isExit) {
                    ui.showBye();
                }
            } catch (NguyenException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Returns the chatbot's response to the user's input as a string.
     * Used for programmatic interaction with the chatbot.
     *
     * @param userInput The user's command input.
     * @return The chatbot's response.
     */
    public String getResponse(String userInput) {
        if (isExit) {
            return "Nguyen Chat Bot has been terminated";
        }
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream capturingOut = new PrintStream(baos);
        System.setOut(capturingOut);
        try {
            Command c = Parser.parse(userInput);
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                isExit = true;
                ui.showBye();
            }
        } catch (NguyenException e) {
            ui.showError(e.getMessage());
        } finally {
            System.out.flush();
            System.setOut(originalOut);
        }
        return baos.toString();
    }

    /**
     * Main method that initializes and runs the chatbot.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Nguyen("data/Nguyen.txt").run();
    }
}
