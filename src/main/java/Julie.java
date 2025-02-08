import java.util.ArrayList;
import java.util.List;

public class Julie {
    private static final String FILE_PATH = "./data/julie.txt";

    private final UI ui;
    private final Storage storage;
    private final TaskList tasks;

    public Julie() {
        this.ui = new UI();
        this.storage = new Storage(FILE_PATH);

        // Load tasks from storage
        List<Task> loadedTasks = storage.loadTasks();
        this.tasks = new TaskList(new ArrayList<>(loadedTasks));
    }

    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (WrongFormatException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Julie().run();
    }
}
