package g;

import g.commands.Command;
import g.parser.Parser;
import g.storage.Storage;
import g.tasks.TaskList;
import g.ui.Ui;
import javafx.application.Platform;

/**
 * The main chatbot class that handles user input, processes commands, 
 * and manages storage, tasks, and UI interactions.
 */
public class G {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Default constructor that initializes the chatbot with a predefined storage file path.
     */
    public G() {
        this(System.getProperty("user.home") + "/Documents/CS2103T/tasks.txt");
    }

    /**
     * Constructs a chatbot instance with a specified file path for task storage.
     * Initializes UI, storage, and loads existing tasks from the file.
     *
     * @param filePath The file path where tasks are stored.
     */
    public G(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println(ui.showLoadingError());
            tasks = new TaskList();
        }

        // Assertions to ensure all essential components are initialized
        assert ui != null : "UI should not be null!";
        assert storage != null : "Storage should not be null!";
        assert tasks != null : "TaskList should not be null!";
    }

    /**
     * Processes user input and returns an appropriate response.
     * Commands are parsed and executed, and tasks are saved after every execution.
     * If the "bye" command is detected, the application will exit.
     *
     * @param input The user input command string.
     * @return A response message from the chatbot.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);
            
            // Automatically save after every command execution
            storage.save(tasks.getTasks());
            
            // Close the application if the "bye" command is detected
            if (c.isExit()) {
                Platform.exit();
            }
            
            assert response != null : "Response should not be null!";
            return response;
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }
}
