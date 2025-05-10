package perrygpt.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import perrygpt.PerryGPT;
import perrygpt.exceptions.PerryGPTException;

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

    private PerryGPT perryGPT;

    private Image PhineasImage = new Image(this.getClass().getResourceAsStream("/images/Phineas.png"));
    private Image FerbImage = new Image(this.getClass().getResourceAsStream("/images/Ferb.png"));

    @FXML
    public void initialize() {scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());}

    /** Injects the Duke instance */
    public void setPerry(PerryGPT r) {
        perryGPT = r;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws PerryGPTException {
        String input = userInput.getText();
        try {
            String response = perryGPT.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, PhineasImage),
                    DialogBox.getChatbotDialog(response, FerbImage)
            );
            userInput.clear();
        } catch (PerryGPTException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, PhineasImage),
                    DialogBox.getChatbotDialog(e.getMessage(), FerbImage)
            ); // Display error message in GUI instead of crashing
        } catch (Exception e) {
            e.printStackTrace(); // Log unexpected exceptions for debugging
        } finally {
            userInput.clear();
        }
    }
}
