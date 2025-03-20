package ujin;

import javafx.application.Platform;
import ujin.command.Command;
import ujin.helper.Parser;
import ujin.helper.TaskProcessor;
import ujin.task.TaskList;
import ujin.ui.Ui;

/**
 * Represents the main application for managing tasks. The `Ujin` class integrates the user interface (UI),
 * task list management, and file handling. It initializes the task list from a file, handles user commands,
 * and performs actions like adding, deleting, and updating tasks.
 * The application supports saving the task list to a file when the program is exiting using a shutdown hook.
 */
public class Ujin {

    /**
     * The list of tasks managed by the application.
     */
    private TaskList taskList;

    /**
     * The user interface (UI) component for interacting with the user.
     */
    private Ui ui;

    /**
     * Constructs a new `Ujin` application with the specified file path for loading and saving tasks.
     * It initializes the UI and loads the task list from the specified file.
     */
    public Ujin() {
        String filePath = "./data/tasks.txt";
        ui = new Ui();
        this.taskList = new TaskList(TaskProcessor.readTasksFromFile(filePath));
    }

    /**
     * Runs the application, displaying the welcome message and continuously reading and processing user commands.
     * The task list is saved to the file when the program shuts down (via a shutdown hook).
     */
    public String run() {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            String filePath = "./data/tasks.txt";
            TaskProcessor.writeTasksToFile(taskList, filePath);
        }));
        return ui.showWelcome();
    }

    public String getResponse(String input) {
        try{
            Command c = Parser.parse(input, ui);
            if (c.isExit()) {
                Platform.exit();
                System.exit(0);
            }
            return c.execute(taskList, ui);
        } catch (UjinException e) {
            ui.showError(e.getMessage());
        }
        return null;
    }

    /**
     * The entry point of the application. Initializes and runs the `Ujin` application with the task file.
     *
     * @param args The command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        System.out.println("Ujin");
        Ujin ujin = new Ujin();
        ujin.run();
    }

}
