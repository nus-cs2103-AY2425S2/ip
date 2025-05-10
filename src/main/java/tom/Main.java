package tom;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tom.ui.Gui;
import tom.ui.MainWindow;

/**
 * A GUI for Tom using FXML.
 */
public class Main extends Application {

    private Tom tom;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            // Set up Tom.
            MainWindow window = fxmlLoader.<MainWindow>getController();
            Gui ui = new Gui(window);
            tom = new Tom(ui);
            window.setTom(tom); // inject the Tom instance

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
