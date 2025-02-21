package ekko.core;
import java.io.IOException;

import ekko.MainWindow;
import ekko.ui.Ekko;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    /**
     * Instantiate a new Ekko instance
     */
    private Ekko ekko = new Ekko();

    /**
     * Start the application
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            if (fxmlLoader.getLocation() == null) {
                throw new IOException("FXML file not found: /view/MainWindow.fxml");
            }
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Ekko Chatbot");
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setEkko(ekko);
            stage.setOnCloseRequest(event -> System.exit(0)); // Ensure proper exit
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading MainWindow.fxml. Check the file path.");
            e.printStackTrace();
        }
    }
}
