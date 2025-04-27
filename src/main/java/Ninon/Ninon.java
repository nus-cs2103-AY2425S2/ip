package Ninon;

import Ninon.Command.Command;

public class Ninon {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Ninon(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (NinonException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public Ninon() {
        ui = new Ui();
        storage = new Storage("tasks.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (NinonException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.split(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (NinonException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.split();
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String message = c.execute(taskList, ui, storage);
            return message;
        } catch (NinonException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Ninon("tasks.txt").run();
    }


}
