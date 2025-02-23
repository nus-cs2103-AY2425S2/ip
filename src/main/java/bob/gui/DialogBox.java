package bob.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * This class represents a dialog box to represent either user or Bob's message.
 */
public class DialogBox extends HBox {

    private static final Image userImage = new Image(DialogBox.class.getResourceAsStream("/images/user.png"));
    private static final Image bobImage = new Image(DialogBox.class.getResourceAsStream("/images/bob.png"));

    @FXML
    private HBox container;

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/DialogBox.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(container.getChildren());
        Collections.reverse(tmp);
        container.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT); // Align to the left after flipping
    }

    /**
     * Returns a dialog box representing the user's message.
     *
     * @param text user's message.
     * @return DialogBox object.
     */
    public static DialogBox getUserDialogBox(String text) {
        var db = new DialogBox(text, userImage);
        db.setAlignment(Pos.TOP_RIGHT); // Align user dialog to the right
        return db;
    }

    /**
     * Returns a dialog box representing Bob's message.
     *
     * @param text bob's message.
     * @return DialogBox object.
     */
    public static DialogBox getBobDialogBox(String text) {
        var db = new DialogBox(text, bobImage);
        db.flip(); // Flip Bob's dialog to align it to the left
        return db;
    }
}
