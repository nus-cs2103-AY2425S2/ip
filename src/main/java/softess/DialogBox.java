package softess;

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
 * Represents a dialog box component in the user interface that displays a message along with
 * a speaker's profile picture. The dialog box extends HBox and is configured through FXML.
 * It can be displayed in two orientations - one for user messages and another for Softess messages.
 *
 * The dialog box consists of:
 * <ul>
 *   <li>An ImageView displaying the speaker's profile picture</li>
 *   <li>A Label containing the speaker's message</li>
 * </ul>
 *
 */
public class DialogBox extends HBox {
    /** The label component that displays the dialog text. */
    @FXML
    private Label dialog;

    /** The ImageView component that displays the speaker's profile picture. */
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox with the specified text and profile image.
     * The constructor loads the FXML layout and initializes the dialog components.
     *
     * @param text The message text to be displayed in the dialog box
     * @param img The profile image of the speaker
     * @throws RuntimeException if the FXML file cannot be loaded
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(text);
        this.displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box orientation by reversing the order of its child nodes.
     * This method is used to change the layout from right-aligned (user messages) to
     * left-aligned (Softess messages).
     * After flipping, the ImageView appears on the left and the text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a new DialogBox instance for user messages.
     * The dialog box will be right-aligned with the image on the right side.
     *
     * @param text The user's message text
     * @param img The user's profile image
     * @return A new DialogBox instance configured for user messages
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a new DialogBox instance for Softess messages.
     * The dialog box will be left-aligned with the image on the left side.
     *
     * @param text The Softess message text
     * @param img The Softess profile image
     * @return A new DialogBox instance configured for Softess messages
     */
    public static DialogBox getSoftessDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}