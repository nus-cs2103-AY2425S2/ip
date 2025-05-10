package botato;
import botato.command.Command;
import botato.exception.BotatoException;

/**
 * The Botato class represents the main bot application that interacts with the user to manage tasks.
 * It initializes and runs the user interface, processes commands, and manages tasks.
 */
public class Botato {
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor used for testing.
     * @param ui Mock UI to test with
     * @param taskList Mock tasklist to test with
     * @param parser
     */
    public Botato(Ui ui, TaskList taskList, Parser parser) {
        this.ui = ui;
        this.tasks = taskList;
    }
    /**
     * Constructs a new Botato instance with an empty task list and initializes the user interface.
     */
    public Botato() {
        ui = new Ui();
        tasks = new TaskList();
        Ui.showWelcome(); // Show welcome message to the user.
    }

    /**
     * Takes in an input in the form of an user input string and returns a string response.
     *
     * @param input string to parse.
     * @return String response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input); // Parse the command entered by the user.
            return c.execute(tasks, ui); // Execute the parsed command, affecting the task list and UI.
        } catch (BotatoException e) {
            System.out.println(e.getMessage());
            return e.getMessage(); // Show error message if there's an issue with the command.
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return "An error occurred: " + e.getMessage();
        } finally {
            // Ensures tasks are saved even if user force quits the application.
            tasks.save();
        }
    }
    /**
     * Runs the bot application by displaying a welcome message, reading user input,
     * parsing commands, and executing the respective actions. The bot continues running until
     * an exit command is received.
     */
    public void run() {
        boolean isExit = false;
        // Loop that keeps the bot running until the user requests an exit.
        while (!isExit) {
            try {
                String cmd = ui.readCommand(); // Read the user's command.
                Ui.showLine(); // Display a separator line.
                Command c = Parser.parse(cmd); // Parse the command entered by the user.
                c.execute(tasks, ui); // Execute the parsed command, affecting the task list and UI.
                isExit = c.isExit(); // Check if the exit condition is met.
            } catch (BotatoException e) {
                ui.showError(e.getMessage()); // Show error message if there's an issue with the command.
            } finally {
                Ui.showLine(); // Always show a line separator after command execution.
            }
        }
    }

    /**
     * The main entry point of the Botato application.
     * Initializes and starts the bot's execution.
     *
     * @param args Command line arguments (not used in this case).
     */
    public static void main(String[] args) {
        new Botato().run(); // Create an instance of Botato and run the application.
    }
}
