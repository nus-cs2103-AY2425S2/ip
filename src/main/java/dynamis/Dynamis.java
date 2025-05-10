package dynamis;

/**
 * Represents an instance of the Dynamis chatbot.
 */
public class Dynamis {
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasks;

    private static final String STORAGE_FILE_PATH = "./data/dynamis.txt";

    /**
     * Constructs a new Dynamis object and loads previously saved tasks.
     * File to load is given in the STORAGE_FILE_PATH constant global variable.
     * Creates new file if does not exist.
     */
    public Dynamis() {
        this.ui = new Ui();
        this.parser = new Parser();
        storage = new Storage(STORAGE_FILE_PATH);
        storage.initializeFile();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns the appropriate output after saving the current tasks
     * and passes the user's command input to parser to process the input.
     *
     * @param input The user's command input.
     * @return The processed output or chatbot response based on the input given.
     */
    public String getResponse(String input) {
        try {
            storage.saveToFile(tasks.getTasks());
        } catch (Exception e) {
            System.out.println(e);
        }
        return parser.processInput(input, tasks, ui);
    }
}
