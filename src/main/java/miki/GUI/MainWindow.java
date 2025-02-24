package miki.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import miki.Miki;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Miki miki;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image mikiImage = new Image(this.getClass().getResourceAsStream("/images/DaDog.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Miki instance */
    public void setMiki(Miki m) {
        miki = m;
        dialogContainer.getChildren().add(DialogBox.getMikiDialog(miki.getGreeting(), mikiImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Miki's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = miki.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMikiDialog(response, mikiImage));
        if (miki.checkExit()) {
            miki.scheduleExit(5);
        }
        userInput.clear();
    }
}
