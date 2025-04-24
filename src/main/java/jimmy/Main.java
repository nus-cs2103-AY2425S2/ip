package jimmy;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The {@code Main} class serves as the entry point for the Jimmy Chatbot application.
 * It initializes the GUI using JavaFX and loads the UI defined in FXML.
 */
public class Main extends Application {

    private Jimmy jimmy;

    @Override
    public void start(Stage stage) {
        try {
            jimmy = new Jimmy("data/jimmy.txt");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();
            MainWindow controller = fxmlLoader.getController();
            controller.setJimmy(jimmy);
            Scene scene = new Scene(root);
            stage.setTitle("Jimmy Chatbot");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setFullScreenExitHint("Press ESC to exit fullscreen");
            scene.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                case F11 -> stage.setFullScreen(!stage.isFullScreen());
                default -> { }
                }
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
