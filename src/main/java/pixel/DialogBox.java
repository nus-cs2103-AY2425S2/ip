package pixel;

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
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
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

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Returns a DialogBox formatted for displaying user input.
     *
     * @param text Input by user
     * @param img Icon for user
     * @return DialogBox displaying user input
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a DialogBox formatted for displaying response from Pixel after successful operation.
     *
     * @param text Response from Pixel
     * @param img Icon for Pixel
     * @return DialogBox displaying Pixel response
     */
    public static DialogBox getPixelDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Returns a DialogBox formatted for displaying error message.
     *
     * @param errorMessage Error message of a PixelException
     * @param img Icon for Pixel
     * @return DialogBox displaying error message
     */
    public static DialogBox getPixelExceptionDialog(String errorMessage, Image img) {
        var db = new DialogBox(errorMessage, img);
        db.flip();
        db.dialog.getStyleClass().add("error-label");
        return db;
    }
}
