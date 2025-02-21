//Solution below adapted from https://se-education.org/guides/tutorials/javaFx.html

import java.io.IOException;

import avocado.Avocado;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Avocado avo = new Avocado("data/tasks.txt");

    /**
     * Starts the GUI application.
     *
     * @param stage The stage to start the GUI application.
     */

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAvo(avo);  
            fxmlLoader.<MainWindow>getController().showWelcome();
            stage.setTitle("Avocado");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
