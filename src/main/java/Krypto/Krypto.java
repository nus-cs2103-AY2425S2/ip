package Krypto;
import Krypto.Commands.Command;
import Krypto.Exceptions.KryptoExceptions;
import Krypto.IO.GUI;
import Krypto.IO.Storage;
import Krypto.Utils.Parser;
import Krypto.Utils.TaskList;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

/**
 * The main class for the Krypto application.
 * It initializes storage, task management, and user interface, and runs the main event loop.
 */
public class Krypto extends Application{
    private static final String FILE_PATH = "src/main/data/Krypto.txt";

    private static final String FXML_PATH = "/view/GUI.FXML";
    private Storage storage;
    private TaskList tasks;
    private GUI gui = null;

    private AnchorPane root;

    /**
     * Initializes a Krypto instance with the specified file path.
     * Loads tasks from storage and sets up the UI.
     *
     * @param filePath The path to the storage file.
     */
    public Krypto(String filePath) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH));
        try {
            root = fxmlLoader.load();
            gui = fxmlLoader.getController();
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load(), gui);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load GUI.fxml");
        } catch (KryptoExceptions e) {
            gui.showError(e);
            tasks = new TaskList();
        }
        assert gui != null : "GUI should be initialized after loading FXML";
    }
    public Krypto() {
        this(FILE_PATH);
    }

    /**
     * Runs the command given by the user.
     *
     * @param prompt  The user prompt.
     */

    public void run(String prompt) {
        assert prompt != null : "User input should not be null";
        try {
            Command cmd = Parser.parse(prompt);
            cmd.execute(gui, tasks, storage);
            if (cmd.isExit()) {
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
            }
        } catch (KryptoExceptions e) {
            gui.showError(e);
        }
    }

    @Override
    public void start(Stage stage) {
        assert root != null : "FXML root layout should be loaded properly";
        Scene scene = new Scene(root);
        stage.setScene(scene);
        gui.setKrypto(this);
        stage.show();
        gui.showWelcome();
    }
}
