package charlie;

/**
 * The main class that orchestrates the Charlie task manager.
 * It initializes storage, loads tasks, and processes user commands.
 */

public class Charlie {
    private final Storage storage;
    private TaskList taskList;

    public Charlie(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage);
    }

    public String getResponse(String input) {
        String response = "";
        try {
            response = getString(input);
        } catch (Exception e) {
            response = Ui.showError(e.getMessage());
        }
        return response;
    }

    private String getString(String input) {
        String response;
        Parser parser = new Parser(input);
        response = parser.getResponse(taskList);
        return response;
    }

}
