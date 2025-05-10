package godbot;

import godbot.ui.Ui;
import godbot.storage.Storage;
import godbot.parser.Parser;
import godbot.task.Task;
import godbot.task.TaskList;
import godbot.exception.GodBotException;
import java.util.ArrayList;
import java.io.IOException;

/**
 * The GodBot class is the starting point for the chatbot.
 * It initializes the user interface, storage, and task list,
 * and continuously processes user commands.
 */
public class GodBot {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private static final String DEFAULT_FILE_PATH = "./data/godbot.txt";

    /**
     * Constructs a GodBot instance with the specified file path for storing tasks.
     *
     * @param filePath The path to the file used for storing task data.
     */
    public GodBot(String filePath) {
        assert filePath != null : "File path cannot be null, mortal.";
        ui = new Ui();
        assert ui != null : "Ui should be initialized, mortal.";
        storage = new Storage(filePath);
        assert storage!= null : "Stroage should be initialized, mortal.";
        try {
            taskList = new TaskList(storage.load());  
            assert taskList != null : "TaskList should not be null";
        } catch (IOException e) {
            ui.showMessage("Failed to load your tasks, mortal.");
            taskList = new TaskList();
        }
    }

    /**
     * The main method to start the GodBot program.
     * Initializes the bot with the default storage file and begins execution.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new GodBot(DEFAULT_FILE_PATH).run();
    }

    /**
     * Starts GodBot's main loop, displaying a welcome message and
     * processing user input until the termination command is received.
     */
    public void run() {
        ui.showWelcomeMessage();
        while (true) {
            String input = ui.readCommand();
            String response = Parser.processCommand(input, taskList, storage, ui);
            if (input.equalsIgnoreCase("bye")) {
                ui.showGoodbyeMessage();
                break;
            }
            ui.showMessage(response);
        }
    }

    /**
     * Processes user input and returns a response for the GUI.
     *
     * @param input The user input string.
     * @return The chatbot's response as a string.
     */
    public String getResponse(String input) {
        return Parser.processCommand(input, taskList, storage, ui);
    }

    public Ui getUi(){
        return ui;
    }
}

