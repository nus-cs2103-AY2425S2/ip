import java.io.IOException;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import yapper.Yapper;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Yapper yapper;

    /**
     * Constructs a Main instance and initializes the Yapper instance.
     * The Yapper instance is created with a file path pointing to "data/YapperTasks.txt",
     * ensuring that task data is stored persistently in the project's data directory.
     */
    public Main() {
        String filePath = Paths.get(System.getProperty("user.dir"), "data", "YapperTasks.txt").toString();
        yapper = new Yapper(filePath);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(600);
            stage.setMinWidth(700);
            fxmlLoader.<MainWindow>getController().setYapper(yapper); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
