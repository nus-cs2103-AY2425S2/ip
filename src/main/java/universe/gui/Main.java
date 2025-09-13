package universe.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import universe.Universe;

/**
 * The Main class for the GUI.
 * The code is modified from the JavaFx tutorial.
 */
public class Main extends Application {
    private Universe universe = new Universe("data/chores.txt");

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Universe");
            stage.getIcons().add(new Image("/images/Universe.png"));
            fxmlLoader.<MainWindow>getController().setUniverse(universe);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
