package ui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import sunpter.Sunpter;

import static ui.DialogBox.getSunpterDialog;
import parser.Parser;

import java.util.Objects;

/**
 * Controller for the main GUI.
 */
public class GUIWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Sunpter sunpter;
    private final Parser parser = new Parser();

    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/chris.png")));
    private final Image sunpterImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/will.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                getSunpterDialog(Ui.showWelcome(), sunpterImage)
        );
    }

    /** Injects the sunpter.Sunpter instance */
    public void setSunpter(Sunpter s) {
        sunpter = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing sunpter.Sunpter's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String displayResponse = sunpter.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                getSunpterDialog(displayResponse, sunpterImage)
        );
        userInput.clear();
        if(input.startsWith("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}

