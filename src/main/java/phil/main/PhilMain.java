package phil.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import phil.ui.MainWindow;

/**
 * Represents PhilMain instance that is an Application
 * to be launched by the Launcher class upon calling gradle run.
 */
public class PhilMain extends Application {
    private Phil phil = new Phil();

    /**
     * Loads the MainWindow.fxml file as an AnchorPane, attach the Scene to the Stage
     * and Start showing the Stage to initialise the App.
     *
     * @param stage Stage to set up scene on, and show for the Chatbot Graphic User Interface.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(PhilMain.class.getResource("/view/MainWindow.fxml"));
            Image philImage = new Image(this.getClass().getResourceAsStream("/images/Phil.png"));
            AnchorPane ap = loader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            loader.<MainWindow>getController().setPhil(this.phil);
            stage.setTitle("Phil");
            stage.getIcons().add(philImage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
