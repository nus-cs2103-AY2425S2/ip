package helix.ui;

import helix.Helix;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controls the layout and interactions for the MainWindow GUI.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Helix helix;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image helixImage = new Image(this.getClass().getResourceAsStream("/images/helix.png"));

    /**
     * Initializes the main window and displays welcome message.
     */
    @FXML
    public void initialize() {
        configureScrollPane();
        showWelcomeMessage();
    }

    /**
     * Sets the Helix instance for this controller.
     *
     * @param helix the Helix instance to be used for processing user commands
     */
    public void setHelix(Helix helix) {
        this.helix = helix;
    }

    /**
     * Sets the stage reference for closing the window.
     */
    public void setCloseHandler(Stage stage) {
        this.stage = stage;
    }

    /**
     * Configures the scroll pane properties.
     */
    private void configureScrollPane() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
    }

    /**
     * Displays the welcome message when the window starts.
     */
    private void showWelcomeMessage() {
        dialogContainer.getChildren().add(DialogBox.getHelixDialog(
                "Welcome to Helix! How can I assist you today?",
                helixImage)
        );
    }

    /**
     * Handles the user input and displays the appropriate response from Helix.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        processUserInput(input);
        userInput.clear();
    }

    /**
     * Processes user input and updates the UI accordingly.
     */
    private void processUserInput(String input) {
        String response = helix.executeCommand(input);
        displayUserDialog(input);
        displayHelixResponse(response);
        checkExitCommand(input);
    }

    /**
     * Displays the user input in the dialog container.
     */
    private void displayUserDialog(String input) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
    }

    /**
     * Displays Helix's response in the dialog container.
     */
    private void displayHelixResponse(String response) {
        dialogContainer.getChildren().add(DialogBox.getHelixDialog(response, helixImage));
    }

    /**
     * Checks if the input is an exit command and closes the application if necessary.
     */
    private void checkExitCommand(String input) {
        if (input.trim().equalsIgnoreCase("bye") && stage != null) {
            stage.close();
        }
    }
}
