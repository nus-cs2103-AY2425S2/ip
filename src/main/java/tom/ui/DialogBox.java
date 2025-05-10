package tom.ui;

import java.io.IOException;
import java.util.Collections;
import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static final Image userImage = new Image(DialogBox.class.getResourceAsStream("/images/DaUser.png"));
    private static final Image tomImage = new Image(DialogBox.class.getResourceAsStream("/images/DaTom.png"));
    private int commandId;

    @FXML
    private Label dialog;
    @FXML
    private TextField userInput;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setText(text);
        displayPicture.setImage(img);
    }

    public int getCommandId() {
        return commandId;
    }

    public void setText(String text) {
        dialog.setText(text);
    }

    public void setHandler(Consumer<String> handler) {
        if (handler == null) {
            userInput.setVisible(false);
            return;
        }
        userInput.setVisible(true);
        userInput.setOnAction((event) -> {
            String input = userInput.getText();
            userInput.setVisible(false);
            handler.accept(input);
        });
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text) {
        DialogBox box = new DialogBox(text, userImage);
        return box;
    }

    public static DialogBox getTomDialog(String text, int commandId) {
        DialogBox box = new DialogBox(text, tomImage);
        box.commandId = commandId;
        box.flip();
        return box;
    }
}
