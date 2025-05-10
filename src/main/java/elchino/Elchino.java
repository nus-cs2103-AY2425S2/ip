package elchino;

import java.util.ArrayList;
import elchino.exceptions.*;
import elchino.ui.Ui;
import elchino.tasks.*;
import elchino.storage.Storage;
import elchino.parser.Parser;
import elchino.commands.*;

/**
 * Represents the El Chino chatbot.
 * Initializes the chatbot and runs the chatbot.
 */
public class Elchino {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for El Chino with a specified file path.
     * Handles any exceptions during initialization gracefully.
     *
     * @param filePath The file path to store the tasks.
     */
    public Elchino(String filePath) {
        this.ui = new Ui();
        Storage tempStorage;
        TaskList tempTasks;

        try {
            tempStorage = new Storage(filePath);
        } catch (ElchinoException e) {
            ui.printMessage("Error al inicializar el almacenamiento: " + e.getMessage());
            tempStorage = null;
        }

        if (tempStorage != null) {
            try {
                tempTasks = new TaskList(tempStorage.loadTasks());
            } catch (ElchinoException e) {
                ui.printMessage("Error al cargar las tareas: " + e.getMessage());
                tempTasks = new TaskList(new ArrayList<>());
            }
        } else {
            tempTasks = new TaskList(new ArrayList<>());
        }

        this.storage = tempStorage;
        this.tasks = tempTasks;
    }

    /**
     * Runs the El Chino chatbot, handling user input and executing commands.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);

                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (ElchinoException e) {
                ui.printMessage(e.getMessage());
            } catch (Exception e) {
                ui.printMessage("¡Error desconocido! Por favor, inténtalo de nuevo.");
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Starts ElChino chatbot on command line.
     * Entry point of the program.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Elchino("data/tasks.txt").run();
    }

    /**
     * Gets the chatbot's response to user input (used for GUI interaction).
     *
     * @param input The user input.
     * @return The chatbot's response.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (ElchinoException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "¡Error desconocido! Por favor, inténtalo de nuevo.";
        }
    }
}
