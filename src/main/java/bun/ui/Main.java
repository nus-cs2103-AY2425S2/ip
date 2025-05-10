package bun.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bun using FXML.
 */
public class Main extends Application {

    private Bun bun = new Bun("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("Bun");
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBun(bun);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bun getBun() {
        return bun;
    }

    public void setBun(Bun bun) {
        this.bun = bun;
    }
}
