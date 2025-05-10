package eve;

import java.io.IOException;

import eve.exception.EveException;
import eve.ui.Eve;
import eve.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Eve eve;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            stage.setTitle("Eve");
            stage.getIcons().add(new Image("/images/icon.png"));
            eve = new Eve();
            fxmlLoader.<MainWindow>getController().setEve(eve); // inject the Eve instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EveException e) {
            e.printStackTrace();
        }
    }
}
