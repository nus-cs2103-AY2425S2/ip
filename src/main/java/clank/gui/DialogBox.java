package clank.gui;

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
import javafx.scene.layout.Region;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     *
     * @param text The text content of the dialog.
     * @param img The image representing the speaker.
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

        dialog.setText(text);
        displayPicture.setImage(img);
        this.setSpacing(15);
        dialog.setWrapText(true);
        dialog.setMaxWidth(259);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        dialog.setPrefHeight(Region.USE_COMPUTED_SIZE);
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

    /**
     * Creates a dialog box for the user.
     *
     * @param text The user's input text.
     * @param img The user's profile image.
     * @return A DialogBox representing the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setAlignment(Pos.CENTER_RIGHT);
        db.dialog.setStyle("-fx-background-color: #9cd3ff; "
                + "-fx-text-fill: black; "
                + "-fx-background-radius: 10; "
                + "-fx-padding: 10; "
                + "-fx-border-color: #038cfc; "
                + "-fx-border-radius: 10; "
                + "-fx-border-width: 3px;"
        );
        return db;
    }

    /**
     * Creates a dialog box for Clank.
     *
     * @param text The chatbot's response text.
     * @param img The chatbot's profile image.
     * @return A DialogBox representing Clank's message, flipped for proper alignment.
     */
    public static DialogBox getClankDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.setAlignment(Pos.CENTER_LEFT);
        db.dialog.setStyle("-fx-background-color: #fdcaa2; "
                + "-fx-text-fill: black; "
                + "-fx-background-radius: 10; "
                + "-fx-padding: 10; "
                + "-fx-border-color: #E26313; "
                + "-fx-border-radius: 10; "
                + "-fx-border-width: 3px;"
        );
        return db;
    }
}
