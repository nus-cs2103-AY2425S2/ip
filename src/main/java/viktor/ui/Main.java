package viktor.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Viktor using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setResizable(true);

            stage.setScene(scene);

            MainWindow controller = fxmlLoader.getController();
            controller.setViktor(new Viktor());

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
