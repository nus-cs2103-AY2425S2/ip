package gui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * The controller for the main GUI window of the Koji application.
 * <p>
 * This class manages user interactions, displaying dialog boxes for both user input
 * and system responses. It extends {@link ScrollPane} and contains an interactive
 * text field for user input.
 * </p>
 */
public class MainWindow extends ScrollPane {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    private KojiGui kojiGui;
    private final Image userImage = new Image(getClass().getResourceAsStream("/images/User.png"));
    private final Image kojiImage = new Image(getClass().getResourceAsStream("/images/Koji.png"));

    /**
     * Sets the instance of {@link KojiGui} that handles user input and responses.
     *
     * @param kojiGui The instance of {@link KojiGui} to be linked to this window.
     */
    public void setKojiGui(KojiGui kojiGui) {
        this.kojiGui = kojiGui;
    }

    /**
     * Initializes the GUI components.
     */
    @FXML
    public void initialize() {
        dialogContainer.setStyle("-fx-background-color: lavenderblush");
        dialogContainer.minHeightProperty().bind(scrollPane.heightProperty());
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getKojiDialog("Welcome to Koji! How can I help you today?", kojiImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kojiGui.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKojiDialog(response, kojiImage)
        );
        userInput.clear();
    }
}
