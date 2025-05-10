package phil.ui;

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
import phil.main.PhilMain;


/**
 * Represents a Dialog Box instance, which when used with /view/DialogBox.fxml
 * represents a dialog for either an input by the user, or an output by the chatbot.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Sets up the DialogBox instance.
     *
     * @param text Text String to be shown in the dialog.
     * @param img Image to be used in the dialog box as either the user or the chatbot.
     * @param pictureHeight Height of picture to represent the user or chatbot.
     */
    public DialogBox(String text, Image img, double pictureHeight) {
        try {
            FXMLLoader loader = new FXMLLoader(PhilMain.class.getResource("/view/DialogBox.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        if (text.length() > 50) {
            dialog.setStyle(dialog.getStyle() + "-fx-font-size: " + 15);
        } else {
            dialog.setStyle(dialog.getStyle() + "-fx-font-size: " + 20);
        }
        displayPicture.setImage(img);
        displayPicture.setFitHeight(pictureHeight);
        displayPicture.setFitWidth(pictureHeight);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    public void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
}

