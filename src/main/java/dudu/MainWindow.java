package dudu;

import dudu.controller.Command;
import dudu.controller.Parser;
import dudu.model.TaskList;
import dudu.utils.DuduException;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Dudu dudu;
    private TaskList tasks;
    private File cachedTasks;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/bubu.jpg"));
    private Image duduImage = new Image(this.getClass().getResourceAsStream("/images/dudu.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDudu(Dudu d) {
        dudu = d;
        tasks = d.getTasks();
        cachedTasks = d.getCachedTasks();

        showWelcomeMessage();
    }

    @FXML
    private void handleUserInput() throws DuduException, IOException {
        String userText = userInput.getText();
        Command command = Parser.parse(userText);
        String duduText = command.execute(tasks, cachedTasks);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDuduDialog(duduText, duduImage)
        );
        userInput.clear();

        if (duduText.trim().equals("See you next time!")) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    javafx.application.Platform.exit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Dudu!\nWhat can I do for you today?";
        dialogContainer.getChildren().add(DialogBox.getDuduDialog(welcomeMessage, duduImage));
    }

}
