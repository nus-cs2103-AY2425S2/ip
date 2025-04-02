package orim;

import orim.command.Command;
import orim.myexception.MyException;
import orim.parser.Parser;
import orim.storage.Storage;
import orim.tasklist.TaskList;
import orim.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a chatbot that allows user to keep track of their tasks.
 */
public class Orim {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Orim(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
            File file = new File(filePath);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Ui.showError(ex.getMessage());
            }
        }
    }

    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MyException e) {
                Ui.showError(e.getMessage());
            } finally {
                Ui.showLine();
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (MyException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Orim("tasks.txt").run();
    }

}
