package dudu;

import dudu.utils.DuduException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

public class Main extends Application {
    private Dudu dudu = new Dudu();

    @Override
    public void start(Stage stage) {
        try {
            dudu.retrieveCachedTasks();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(600);
            stage.setMinWidth(417);
            stage.setTitle("Dudu");
            fxmlLoader.<MainWindow>getController().setDudu(dudu);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DuduException e) {
            throw new RuntimeException(e);
        }
    }

}
