package tasker.ui;

import java.io.IOException;

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
import tasker.exception.TaskerException;

/**
 * Component for displaying diaglog.
 */
class DialogBox extends HBox {
    /** Text content of dialog */
    @FXML
    private Label text;
    /** Image of speaker */
    @FXML
    private ImageView displayPicture;

    /**
     * Class constructor.
     *
     * @param textContent The text spoken.
     * @param image       Location of speaker image.
     */
    private DialogBox(String textContent, Image image) throws TaskerException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new TaskerException("Failed to render dialog.");
        }

        text.setText(textContent);
        displayPicture.setImage(image);
    }

    /**
     * Creates a dialog by the user.
     *
     * @param text The text conveyed.
     */
    static DialogBox getUserDialog(String text) throws TaskerException {
        Image userImage = new Image(DialogBox.class.getResourceAsStream("/images/DaUser.png"));
        return new DialogBox(text, userImage);
    }

    /**
     * Creates a dialoog by Tasker.
     *
     * @param text The response to the user.
     */
    static DialogBox getTaskerDialog(String text) throws TaskerException {
        Image taskerImage = new Image(DialogBox.class.getResourceAsStream("/images/DaTasker.png"));
        DialogBox db = new DialogBox(text, taskerImage);
        db.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(db.getChildren());
        FXCollections.reverse(tmp);
        db.getChildren().setAll(tmp);
        return db;
    }
}
