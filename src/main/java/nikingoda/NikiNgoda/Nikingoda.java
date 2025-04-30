package nikingoda.NikiNgoda;

import nikingoda.Command.Command;
import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Parser.Parser;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public class Nikingoda {
    private final Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Nikingoda(String folderPath, String filePath) {
        this.ui = new Ui();

        try {
            this.storage = new Storage(folderPath, filePath);
            this.taskList = new TaskList(this.storage.loadTasks());
        } catch (NikingodaException e) {
            this.ui.showError(e);
        }
    }

    public static void main(String[] args) {
        Nikingoda nikingoda = new Nikingoda("data", "tasks.txt");
        nikingoda.run();
    }


    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.read();
                Command c = Parser.parse(command);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (NikingodaException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(taskList, ui, storage);
            return c.getString();
        } catch (NikingodaException e) {
            return e.getMessage();
        }
    }
}

