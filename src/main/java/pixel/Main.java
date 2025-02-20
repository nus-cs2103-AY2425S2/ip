package pixel;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {

    private Pixel pixel = new Pixel();
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Pixel");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/robot-idle.png")));
            fxmlLoader.<MainWindow>getController().setPixel(pixel);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
