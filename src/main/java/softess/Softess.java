package softess;

import java.io.IOException;
import java.util.Scanner;

public class Softess {
    private static final String FILE_PATH = "src/main/resources/data/softess.txt";
    private DataHandler dataHandler;
    private TaskList tasks;
    private UserInterface ui;

    public Softess(String filePath) {
        this.ui  = new UserInterface();
        this.dataHandler = new DataHandler();
        this.tasks = new TaskList(dataHandler.loadData());
    }

    @Deprecated
    public void run() {
        this.ui.showWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(ui, tasks);
        try {
            while (true) {
                Command c = parser.parseCommand(scanner.nextLine());
                c.trigger();
                dataHandler.saveData(tasks.getTasks());
            }
        } catch (SoftessException e) {
            ui.showErrorMessage(e.getMessage());
        } catch (IOException e) {
            ui.showErrorMessage(e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public String getResponse(String text) {
        Parser parser = new Parser(ui, tasks);
        try {
           Command c = parser.parseCommand(text);
           return c.trigger();
        } catch (SoftessException e) {
            return "Oops! Softess got an error : \n " + e.getMessage();
        }

    }

    public void updateData() throws IOException {
        this.dataHandler.saveData(this.tasks.getTasks());
    }
    public static void main(String[] args) {
        new Softess(FILE_PATH).run();
    }
}