package boink;

import java.io.IOException;

import boink.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Boink using FXML.
 */

public class Main extends Application {

    private Boink boink = new Boink();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBoink(boink); // inject the Boink instance
            fxmlLoader.<MainWindow>getController().loadWelcome(); // Load Welcome Message
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
