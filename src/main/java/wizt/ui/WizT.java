package wizt.ui;

import java.io.FileWriter;
import java.io.IOException;

import wizt.command.Command;
import wizt.parser.Parser;
import wizt.storage.Storage;
import wizt.task.Task;
import wizt.task.TaskList;



//Tiew Jia Liang
//A0273239Y
/**
 *  Represents the main progam flow
 */
public class WizT {
    private static TaskList tasks;
    private Ui ui;
    private Storage storage;



    /**
     * Represents a Constructor that takes in filename
     * @param filename
     * @throws WizTException
     */
    public WizT(String filename) throws WizTException {
        ui = new Ui();
        storage = new Storage(filename);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Represents the main program execution
     * @throws WizTException
     */
    public void run() throws WizTException {
        ui.showLine();
        ui.showWelcome();
        ui.showLine();
        String filename = "wizt.txt";

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (WizTException e) {
                ui.showError(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.showError("Please enter a wizt.task.Task number!");
            }
        }
    }

    /**
     * Represents the method to write to disk
     */
    public static void writeToFile() {
        String filename = "wizt.txt";
        try {
            FileWriter fw = new FileWriter(filename);
            for (Task task : tasks.getTasksList()) {
                fw.write(task.toString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
    }


    /**
     * Represents the retrieval the response for a command.
     * @param fullCommand The full command entered by the user.
     * @return The response message.
     * @throws WizTException if an error occurs during command execution.
     */
    public String getResponse(String fullCommand) throws WizTException {
        Command c = Parser.parse(fullCommand);
        return c.execute(tasks, ui, storage);
    }
}
