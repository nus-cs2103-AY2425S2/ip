package nikingoda.NikiNgoda;

import javafx.application.Platform;
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

    private Nikingoda nikingoda;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(
                DialogBox.getNikingodaDialog("Hello! I'm nikingoda\n" + "What can I do for you?", dukeImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Nikingoda instance
     */
    public void setNikingoda(Nikingoda nikingoda) {
        this.nikingoda = nikingoda;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nikingoda.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNikingodaDialog(response, dukeImage)
        );
        if(input.equals("bye")) {
            try {
                exitProgram();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                Platform.exit();
            }
        }
        userInput.clear();
    }


    /**
     * wait for 500 ms then exit
     * @throws InterruptedException handle error
     */
    public void exitProgram() throws InterruptedException {
        wait(5000);
        Platform.exit();
    }
}

