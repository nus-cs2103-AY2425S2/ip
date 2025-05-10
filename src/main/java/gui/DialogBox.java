package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import java.util.Collections;

/**
 * Represents a dialog box consisting of an ImageView for the speaker's face
 * and a Label for the text message. Provides static factory methods to create
 * dialog boxes for user and system messages.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor that initializes the DialogBox by creating the Label and ImageView.
     * @param text The dialog text.
     * @param img  The image representing the speaker.
     */
    private DialogBox(String text, Image img) {
        dialog = new Label(text);
        dialog.setWrapText(true);
        dialog.maxWidthProperty().bind(this.widthProperty().subtract(120));

        dialog.setStyle("-fx-background-color: lightpink; -fx-padding: 10; -fx-background-radius: 10;");

        displayPicture = new ImageView(img);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        Circle clip = new Circle(50, 50, 50);
        displayPicture.setClip(clip);

        // Add horizontal margin between the text and the image.
        HBox.setMargin(dialog, new Insets(5, 10, 5, 10));

        // Set a bottom margin for this dialog box when added to a VBox.
        VBox.setMargin(this, new Insets(0, 0, 5, 0));
    }

    /**
     * Flips the dialog box so that the ImageView is on the left and the text on the right.
     * This is used to differentiate between user and system messages.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box for the user's message.
     * The user's dialog box displays text on the left and the user's image on the right.
     * @param text The user's message.
     * @param img  The user's avatar image.
     * @return A DialogBox instance representing the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.getChildren().addAll(db.dialog, db.displayPicture);
        db.setAlignment(Pos.TOP_RIGHT);
        return db;
    }

    /**
     * Creates a dialog box for the system (Koji) message.
     * The system's dialog box is flipped so that the image appears on the left and the text on the right.
     * @param text The system's message.
     * @param img  The system's avatar image.
     * @return A DialogBox instance representing the system's message.
     */
    public static DialogBox getKojiDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.getChildren().addAll(db.dialog, db.displayPicture);
        db.flip();
        return db;
    }
}
