import java.io.IOException;

import bobandsteve.BobAndSteve;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static final BobAndSteve bobAndSteve = new BobAndSteve("data/bobAndSteve.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "Expected to load fxmlLoader into anchor pane";
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Bob And Steve");
            fxmlLoader.<MainWindow>getController().setBobAndSteve(bobAndSteve);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
