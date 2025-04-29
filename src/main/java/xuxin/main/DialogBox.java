package xuxin.main;

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
import javafx.scene.shape.Circle;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
class DialogBox extends HBox {
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
        setMinHeight(Region.USE_PREF_SIZE);
        displayPicture.setImage(img);

        double radius = 50; // Adjust as needed
        Circle clip = new Circle(radius);
        clip.setCenterX(radius);
        clip.setCenterY(radius);
        displayPicture.setFitWidth(radius * 2);
        displayPicture.setFitHeight(radius * 2);
        displayPicture.setClip(clip);

        this.setSpacing(20);  // Add spacing between image and text
        this.setPadding(new Insets(10));

        // Increase font size
        dialog.setFont(new Font(14));

        // Styling the chat bubble
        dialog.setPadding(new Insets(20));
        dialog.setBackground(new Background(new BackgroundFill(
                Color.LIGHTGRAY, new CornerRadii(20), Insets.EMPTY)));

        // Ensure text wraps nicely inside the bubble
        dialog.setWrapText(true);
        dialog.setMaxWidth(300);
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

    static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}