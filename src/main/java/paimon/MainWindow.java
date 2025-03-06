package paimon;

import java.util.ArrayList;

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

    private Paimon paimon;

    // private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    // private Image paimonImage = new Image(this.getClass().getResourceAsStream("/images/Paimon_strong.png"));

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/tabibito.jpg"));
    private Image paimonImage = new Image(this.getClass().getResourceAsStream("/images/Paimon_checking.jpeg"));
    private ArrayList<Image> paimonImages = new ArrayList<Image>();

    /** 
     *  Bind the VBox height property to the ScrollPane viewport height property
     */
    @FXML 
    public void initialize() {
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /** 
     * Injects the Duke instance  
     */
    public void setPaimon(Paimon p) {
        this.paimon = p;
        
        // add all images with paimon in the name to the list 
        paimonImages.add(new Image(this.getClass().getResourceAsStream("/images/Paimon_checking.jpeg")));
        paimonImages.add(new Image(this.getClass().getResourceAsStream("/images/Paimon_strong.png")));
        paimonImages.add(new Image(this.getClass().getResourceAsStream("/images/Paimon_tired.jpeg")));
        paimonImages.add(new Image(this.getClass().getResourceAsStream("/images/Paimon_wakeup.jpeg")));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = paimon.getResponse(input);
        if (response.equals("Goodbye paimon!")) {
            System.exit(0);
        }
        
        // choose a random paimon image 
        int randomIndex = (int) (Math.random() * paimonImages.size());
        paimonImage = paimonImages.get(randomIndex);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, paimonImage)
        );
        userInput.clear();
    }

    /**
     * Displays the welcome message from Paimon.
     */
    @FXML
    public void displayWelcomeMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(paimon.getWelcomeMessage(), paimonImage)
        );
    }
}

