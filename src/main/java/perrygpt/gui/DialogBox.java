package perrygpt.gui;

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
 * Represents a dialog box consisting of an {@code ImageView} to represent the speaker's face
 * and a {@code Label} containing text from the speaker.
 * This class is used in the GUI to display user and chatbot messages.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} with the specified text and image.
     * The FXML file is loaded to style and structure the dialog box.
     *
     * @param text The text message to be displayed.
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
    }

    /**
     * Flips the dialog box such that the {@code ImageView} appears on the left and the text on the right.
     * This is typically used to differentiate chatbot messages from user messages.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a dialog box for user messages, where the text and image are aligned normally.
     *
     * @param text The message text.
     * @param img The user’s profile image.
     * @return A {@code DialogBox} representing a user message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box for chatbot messages, where the dialog box is flipped
     * so that the image appears on the left.
     *
     * @param text The chatbot's response text.
     * @param img The chatbot's profile image.
     * @return A {@code DialogBox} representing a chatbot message.
     */
    public static DialogBox getChatbotDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}