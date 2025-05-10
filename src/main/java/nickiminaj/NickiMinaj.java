package nickiminaj;

import nickiminaj.command.Command;

public class NickiMinaj {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public NickiMinaj(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (NickiMinajException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new NickiMinaj("./data/nickiminaj.NickiMinaj.txt").run();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.executeWithOutput(tasks, ui, storage);
        } catch (NickiMinajException e) {
            return e.getMessage();
        }
    }
}