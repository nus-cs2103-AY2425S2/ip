package lebum.main;
import lebum.command.Command;
import lebum.exception.DukeException;


/**
 * Main class to run when user starts program
 */
public class Lebum {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Parser parser;

    /**
     * Main class that starts up
     * @param path the path to the txt file that loads and saves task
     */
    public Lebum(String path) {
        ui = new Ui();
        storage = new Storage(path);
        try {
            tasks = storage.loadTasks();
        } catch (Exception e) {
            ui.showErrorMessage(e);
            tasks = new TaskList();
        }
    }
    public static void main(String[] args) {
        new Lebum("data/tasks.txt").run();

    }

    /**
     * Load the UI
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;


        while (isExit == false) {
            try {
                String cmd = ui.readCommand();
                Command c = Parser.parse(cmd);
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            }
            catch (DukeException e) {
                ui.showErrorMessage(e);
            }

        }

    }

    /**
     * Handles input and output in javaFX.
     * @param input that user inputs in GUI.
     * @return a string to be displayed in javaFX.
     * @throws DukeException when unable to process input.
     */
    public String executeCommand(String input) throws DukeException {
        Command cmd = parser.parse(input);
        try {
            cmd.execute(tasks, storage, ui);
            String message = cmd.getResponse();
            return message;
        }
        catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

}
