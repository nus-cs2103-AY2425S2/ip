package javaFx;

import aquadem.Aquadem;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


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

    private Stage mainStage;
    private Aquadem aquadem;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image aquaImage = new Image(this.getClass().getResourceAsStream("/images/DaAquaDem.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void showIntro() {
        String welcome = aquadem.getIntro();
        dialogContainer.getChildren().add(DialogBox.getAquaDemDialog(welcome, aquaImage));
    }

    /** Injects the Aquadem instance */
    public void setAquadem(Aquadem d) {
        aquadem = d;
        showIntro();
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = aquadem.getResponse(input);
        Platform.runLater(()->dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAquaDemDialog(response, aquaImage))
        );
        userInput.clear();


        if (input.equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> closeStage());
            delay.play();
        }
    }

    /**
     * Sets the stage of this controller to s.
     * @param s The stage to be set to.
     */
    public void setStage(Stage s) {
        this.mainStage = s;
    }

    /**
     * Closes the current stage.
     */
    public void closeStage() {
        this.mainStage.close();
    }
}
