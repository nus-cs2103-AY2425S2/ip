package woof;

import woof.command.Command;
import woof.gui.MainWindow;
import woof.gui.Ui;
import woof.parser.Parser;
import woof.storage.Storage;
import woof.task.TaskList;

/**
 * The main class for the Woof project. Woof is a task management application that allows users
 * to add, delete, mark, and list various kinds of tasks.
 */

public class Woof {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a new instance of the Woof project, with a specified file path. It is set to be "data/tasks.txt"
     * in the entry point.
     *
     * @param filePath The path to the file where the tasks are stored locally.
     */
    public Woof(String filePath) {
        ui = new MainWindow();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.loadTasks(filePath));
        } catch (Exception e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public void getResponse(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(taskList, ui);
            Storage.saveTasks("data/tasks.txt");
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Woof("data/tasks.txt").ui.showWelcome();
    }
}
