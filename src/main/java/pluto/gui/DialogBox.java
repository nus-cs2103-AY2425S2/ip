package pluto.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.Node;

/** Represents a dialog box consisting of an ImageView to
 * represent the speaker and a label containing text from
 * the speaker
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a DialogBox with the given text and image
     * @param s the String of text to be displayed
     * @param i an Image of the speaker
     */
    public DialogBox(String s, Image i) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(s);
        displayPicture.setImage(i);
    }

    /**
     * Flips the DialogBox such that the ImageView is
     * on the left
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String s, Image i) {
        DialogBox dialogBox = new DialogBox(s, i);
        dialogBox.getStyleClass().add("user-dialog");
        return dialogBox;
    }

    public static DialogBox getPlutoDialog(String s, Image i) {
        DialogBox db = new DialogBox(s, i);
        db.flip();
        db.getStyleClass().add("pluto-dialog");
        return db;
    }
}
