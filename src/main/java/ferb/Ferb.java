package ferb;

import ferb.command.*;
import ferb.exception.FerbException;
import ferb.parser.Parser;
import ferb.tasklist.TaskList;
import ferb.ui.Ui;
import ferb.filehandler.FerbFileHandler;


/**
 * Represents the main class of the Ferb application.
 */
public class Ferb {
    private TaskList tasks;
    private FerbFileHandler fileHandler;
    private Parser parser;
    private Ui ui = new Ui();

    /**
     * Constructs a Ferb object with the specified file path.
     *
     * @param filePath the file path where the task list is stored
     */
    public Ferb(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";
        fileHandler = new FerbFileHandler(filePath);
        String content = fileHandler.readContent();
        if (!content.isEmpty()) {
            loadTasks(content);
        } else {
            loadTasks();
        }
        parser = new Parser(tasks, fileHandler);
    }

    private void loadTasks(String content) {
        assert content != null : "Content should not be null";
        try {
            tasks = new TaskList(content);
        } catch (FerbException e) {
            ui.showLoadingError();
            loadTasks();
        }
    }

    private void loadTasks() {
        this.tasks = new TaskList();
    }

    public String getResponse(String input) {
        try {
            Command c = this.parser.parse(input);
            c.execute(ui, fileHandler, tasks);
        } catch (FerbException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
        return ui.getMessage();
    }

    public void saveTasks() {
        fileHandler.writeContent(tasks);
    }

}
