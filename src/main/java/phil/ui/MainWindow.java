package phil.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import phil.main.Phil;

/**
 * Represents an AnchorPane that is the main window.
 * The main window stores a scrollpane, a vbox for the dialogs,
 * and a textfield for storing the user input.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    // Referenced in TextField fx:id
    @FXML
    private TextField userInput;

    private Phil phil;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image philImage = new Image(this.getClass().getResourceAsStream("/images/Phil.png"));

    /**
     * Initialises the scrollpane to scroll to the height of the dialog container.
     */
    @FXML
    private void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    /**
     * Sets the Phil attribute to the Phil instance passed.
     *
     * @param phil Phil instance passed.
     */
    public void setPhil(Phil phil) {
        this.phil = phil;
    }

    // Don't forget to add the '@FXML' tag for the .fxml file to recognise this function
    // The FXML tag exposes the function to fxml file without needing public access
    // Referenced in TextField onAction

    /**
     * Adds the corresponding user and duke dialogs once Action is detected
     * at the TextField or the sendButton (in /view/DialogBox.fxml).
     * Closes the stage if user inputs bye.
     */
    @FXML
    private void addInputAndOutputDialogs() {
        String input = this.userInput.getText();
        DialogBox userDialog = new DialogBox(input, this.userImage, 50.0);
        DialogBox philDialog = new DialogBox(this.phil.getResponse(input), this.philImage, 50.0);
        philDialog.flip();

        this.dialogContainer.getChildren().addAll(userDialog, philDialog);
        this.userInput.clear();

        if (input.equals("bye")) {
            // Code adapted from a.b on https://stackoverflow.com/questions/13567019/close-fxml-window-by-code-javafx
            Stage stage = (Stage) this.dialogContainer.getScene().getWindow();
            stage.close();
        }
    }
}

