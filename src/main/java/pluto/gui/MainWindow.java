package pluto.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pluto.Pluto;

/**
 * Represents the main window of Pluto's GUI
 */
public class MainWindow extends AnchorPane{
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Pluto pluto;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/cat.jpg"));
    private Image plutoImage = new Image(this.getClass().getResourceAsStream("/images/pluto.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Pluto instance and displays the welcome message
     * @param p the Pluto instance to be set
     */
    public void setPluto(Pluto p) {
        pluto = p;
        dialogContainer.getChildren().add(DialogBox.getPlutoDialog(pluto.displayWelcomeMessage(), plutoImage));
    }


    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String plutoText = pluto.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getPlutoDialog(plutoText, plutoImage)
        );
        userInput.clear();
    }
}
