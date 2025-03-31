package clovis.gui;

import clovis.Clovis;
import clovis.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Clovis clovis;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image clovisImage = new Image(this.getClass().getResourceAsStream("/images/ClovisBrayAI.png"));

    /**
     * Initializes the GUI and display the welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getClovisDialog(Ui.displayWelcome(), clovisImage));
    }

    /** Injects the Clovis instance */
    public void setClovis(Clovis clovis) {
        this.clovis = clovis;
    }

    /**
     * Creates two dialog boxes, the user input and the other containing Clovis's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = clovis.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getClovisDialog(response, clovisImage)
        );
        userInput.clear();
    }
}
