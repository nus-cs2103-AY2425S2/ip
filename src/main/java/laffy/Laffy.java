package laffy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import laffy.command.Command;
import laffy.tasklist.TaskList;


/**
 * Represents the main class of the application.
 */
public class Laffy {
    private static final String FILEPATH = "data/laffy.txt";
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructor for Laffy.
     *
     * @throws IOException If an I/O error occurs.
     */
    public Laffy() throws IOException {

        this.storage = new Storage(FILEPATH);
        this.ui = new Ui();
        ArrayList<ArrayList<String>> loadedData = this.storage.getTasksData();
        if (loadedData.isEmpty()) {
            this.taskList = new TaskList();
        } else {
            this.taskList = new TaskList(loadedData);
        }
    }

    /**
     * Runs the Laffy program.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            System.out.print("> ");
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method for Laffy. No args are expected.
     *
     * @param args The command line arguments.
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        new Laffy().run();
    }

    public String getResponse(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            return c.execute(taskList, storage);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String greet() {
        return "Hello! I'm L.A.F.F.Y\nWhat can I do for you?";
    }
}
