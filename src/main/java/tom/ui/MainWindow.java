package tom.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tom.Tom;

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

    private Tom tom;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setTom(Tom d) {
        tom = d;
    }

    public DialogBox getBox(int commandId) {
        for (Node node: dialogContainer.getChildren()) {
            if (!(node instanceof DialogBox)) {
                continue;
            }
            DialogBox box = (DialogBox) node;
            if (box.getCommandId() == commandId) {
                return box;
            }
        }
        return addTomDialog("", commandId);
    }

    /**
     * Adds a dialog box containing Tom's response to the dialog container.
     *
     * @param msg The message to be displayed in Tom's dialog box.
     * @return The DialogBox containing Tom's response.
     */
    public DialogBox addTomDialog(String msg, int id) {
        DialogBox box = DialogBox.getTomDialog(msg, id);
        dialogContainer.getChildren().add(box);
        return box;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == "") {
            return;
        }
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input));
        userInput.clear();

        tom.handleUserInput(input);
    }
}
