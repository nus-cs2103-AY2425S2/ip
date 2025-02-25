package alex.ui;

import java.io.IOException;

import alex.Alex;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Alex alex = new Alex("data/alex.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAlex(alex);  // inject the Alex instance
            alex.setUi(fxmlLoader.<MainWindow>getController());
            stage.show();
            fxmlLoader.<MainWindow>getController().printWelcomeMsg();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
