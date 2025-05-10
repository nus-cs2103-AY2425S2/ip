package kaji;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    public Button sendButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Kaji kaji;
    private final Ui ui = new Ui();

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaPeach.png"));
    private final Image kajiImage = new Image(this.getClass().getResourceAsStream("/images/DaMochi.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getMochiDialog(ui.showWelcome(), kajiImage));
    }

    /** Injects the Kaji instance */
    public void setKaji(Kaji k) {
        kaji = k;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Kaji's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kaji.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input + ui.addIndentation(), userImage),
                DialogBox.getMochiDialog(response, kajiImage)
        );

        if (input.trim().equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2)); // 2-second delay
            delay.setOnFinished(event -> {
                Stage stage = (Stage) userInput.getScene().getWindow();
                stage.close();
            });
            delay.play();
        }

        userInput.clear();
    }
}
