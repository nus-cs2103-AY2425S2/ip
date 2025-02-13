package zoe;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import zoe.command.Command;
import zoe.command.Parser;
import zoe.storage.Storage;
import zoe.task.TaskList;
import zoe.ui.Ui;

/**
 * Main application
 */
public class Zoe extends Application {
    private static final String DEFAULT_FILE_PATH = "data/zoe.txt";
    private final Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs a new Zoe chatbot instance.
     * Initializes the storage and task list by loading tasks from the specified file path.
     * If the file does not exist or an error occurs while loading tasks, an empty task list is created instead.
     *
     * @param filePath The path to the file where tasks are stored. This file is used to load and save tasks.
     */
    public Zoe(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (ZoeIoException e) {
            taskList = new TaskList();
        }
    }

    @SuppressWarnings("unused")
    public Zoe() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Processes the user input by parsing it into a command and executing it.
     * If the command is an exit command, the application will terminate.
     * If an error occurs during parsing or execution, the error is displayed to the user via the UI.
     *
     * @param input The raw user input to be processed. Leading and trailing whitespace is trimmed before processing.
     * @see Command#execute(Storage, TaskList, Ui)
     * @see Parser#parse(String)
     * @see Ui#showError(ZoeException)
     */
    public void notify(String input) {
        try {
            Command c = Parser.parse(input.trim());
            c.execute(storage, taskList, ui);
            if (c.isExit()) {
                Platform.exit();
            }
        } catch (ZoeException e) {
            ui.showError(e);
        }

    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Zoe.class.getResource("/view/MainWindow.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        ui = fxmlLoader.getController();
        ui.setZoe(this);
        ui.showWelcome();
        stage.show();
    }
}
