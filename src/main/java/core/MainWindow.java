package core;

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

    private Baimi baimi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image baimiImage = new Image(this.getClass().getResourceAsStream("/images/robot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getBaimiDialog("Hello! I'm Baimi, a task management chatbot.\n What can I do for you today?", baimiImage)
        );
    }

    public void setBaimi(Baimi b) {
        this.baimi = b;
    }

    /**
     * Handles user input by sending it to Baimi and displaying the response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = baimi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBaimiDialog(response, baimiImage)
        );
        userInput.clear();
    }
}

