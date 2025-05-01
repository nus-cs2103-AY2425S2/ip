/**
 * Lara is the main entry point of the chatbot application.
 * It initializes the necessary components and runs the program.
 *
 * @author Maliha Haque
 * @version 1.0
 */

package Lara.ui;

import Lara.exception.LaraException;
import Lara.parser.Parser;
import Lara.storage.Storage;
import java.util.ArrayList;
import java.util.Scanner;

public class Lara {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Lara(String filePath) {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();

        try {
            ArrayList<Task> loadedTasks = storage.load();
            tasks = new TaskList(loadedTasks);
        } catch (LaraException e) {
            ui.errorMessage("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    public String getGreeting() {
        return "Hello! I'm Lara. How can I assist you today?";
    }


    public void run() {
        ui.welcomeMessage();
    }

    public String getResponse(String input) {
        try {
            if (input.equalsIgnoreCase("bye")) {
                storage.save(tasks.getTasks());
                return "Goodbye! Have a great day!"; // Return goodbye message first
            }
            return parser.handleCommandAndReturn(input, tasks, ui, storage);
        } catch (LaraException e) {
            return "Error: " + e.getMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Error: Invalid command format!";
        }
    }


    public static void main(String[] args) {
        new Lara("tasks.txt").run();
    }
}