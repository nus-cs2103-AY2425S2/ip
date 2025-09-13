package universe.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import universe.Ui;
import universe.Universe;

/**
 * Controller class for the main GUI.
 * The code is modified from the JavaFx tutorial.
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

    private Universe universe;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserAstronaut.png"));
    private final Image universeImage = new Image(this.getClass().getResourceAsStream("/images/Universe.png"));

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.greetUser();
    }

    /**
     * Injects the Universe instance.
     */
    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    /**
     * Creates a dialog box to greet the user.
     */
    @FXML
    private void greetUser() {
        Ui ui = new Ui();
        String greeting = ui.getGreeting();
        dialogContainer.getChildren().add(
                DialogBox.getUniverseDialog(greeting, universeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Universe's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = universe.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getUniverseDialog(response, universeImage)
        );
        userInput.clear();
    }
}

