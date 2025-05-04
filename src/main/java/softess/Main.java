package softess;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Softess using FXML.
 */
public class Main extends Application {

    private static final String FILE_PATH = "src/main/data/softess.txt";

    private Softess softess = new Softess(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSoftess(softess);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
