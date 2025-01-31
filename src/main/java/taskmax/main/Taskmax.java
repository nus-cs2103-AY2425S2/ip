package taskmax.main;

import taskmax.storage.Storage;
import taskmax.storage.TaskList;

import taskmax.ui.Ui;

import taskmax.parser.Parser;

import taskmax.command.Command;

import taskmax.exception.TaskmaxException;

import java.io.IOException;


public class Taskmax {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Taskmax(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
            ui.showMessage("Loaded previous tasks from file.");
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                isExit = command.execute(tasks, ui, storage);
            } catch (TaskmaxException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        new Taskmax("data/tasks.txt").run();
    }
}



