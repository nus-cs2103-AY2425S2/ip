package bun.ui;

import java.util.Scanner;

import javafx.application.Platform;

/**
 * Bun is the main entity we'll be using to launch the functionalities of Bun.
 * @author OVOtter
 */
public class Bun {

    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a new Bun instance with an initial task list stored locally.
     * @param filePath File destination of the initial task list.
     */
    public Bun(String filePath) {
        TaskList taskList1;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList1 = new TaskList(this.storage.load());
        } catch (BunException e) {
            ui.getLoadingError();
            taskList1 = new TaskList();
        }
        this.taskList = taskList1;
    }

//    public static void main(String[] args) {
//        new Bun("data/tasks.txt").run();
//    }

//    /**
//     * Runs the Bun application.
//     */
//    public void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                Scanner scanner = new Scanner(System.in);
//                String[] fullCommand = ui.readCommand(scanner);
//                Command c = Parser.parse(fullCommand);
//                c.execute(taskList, ui, storage);
//                isExit = c.isExit();
//            } catch (BunException e) {
//                ui.showError(e.getMessage());
//            }
//        }
//    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) throws InvalidCommandException, MissingFieldException, DateFormatException, InvalidIndexException {
        try {
            String[] fullCommand = ui.readCommand(input);
            Command c = Parser.parse(fullCommand);
            if (c.isExit()) {
                Platform.exit();
            }
            return c.execute(taskList, ui, storage);
        } catch (BunException e) {
            return ui.getError(e.getMessage());
        }
    }
}
