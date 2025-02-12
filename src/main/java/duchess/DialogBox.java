package duchess;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of a text message and a display picture.
 * This class extends {@link HBox} to provide a structured layout for user and system messages.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} with the specified message, image, and user role.
     *
     * @param s       The message to be displayed in the dialog box.
     * @param i       The image to be used as the display picture.
     * @param isUser  {@code true} if the dialog box represents the user,
     *     {@code false} if it represents the system or another entity.
     */
    public DialogBox(String s, Image i, boolean isUser) {
        text = new Label(s);
        displayPicture = new ImageView(i);
        displayPicture.setFitWidth(50);
        displayPicture.setFitHeight(50);
        text.setWrapText(true);
        text.setPadding(new Insets(5));
        text.setStyle(
            "-fx-background-color: #81D4FA; "
            + "-fx-text-fill: black; "
            + "-fx-padding: 10px; "
            + "-fx-background-radius: 10px;"
        );
        this.setSpacing(10);

        if (isUser) {
            this.setAlignment(Pos.CENTER_RIGHT);
            this.getChildren().addAll(text, displayPicture);
        } else {
            this.setAlignment(Pos.CENTER_LEFT);
            this.getChildren().addAll(displayPicture, text);
        }
    }
}
