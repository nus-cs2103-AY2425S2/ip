package taskmax.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.effect.DropShadow;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;



/**
 * Custom control representing dialog boxes for user and Taskmax messages.
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified message, image, and background color.
     *
     * @param message The text message to be displayed.
     * @param img     The display image for the dialog box.
     * @param bgColor The background color for the message bubble.
     */
    private DialogBox(String message, Image img, String bgColor) {
        text = new Label(message.replace(Ui.LINE, "").trim());
        text.setWrapText(true);
        text.setMaxWidth(700);
        text.setPadding(new Insets(0, 10, 0, 10));
        text.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 12px;");
        text.setAlignment(Pos.CENTER);

        VBox textContainer = new VBox(text);
        textContainer.setPadding(new Insets(8, 10, 8, 10));
        textContainer.setAlignment(Pos.CENTER);
        textContainer.setStyle("-fx-background-color: " + bgColor + "; " +
                "-fx-background-radius: 15px; " +
                "-fx-padding: 8px 10px; " +
                "-fx-font-size: 12px; " +
                "-fx-border-color: #6495ED; " +
                "-fx-border-radius: 15px; " +
                "-fx-border-width: 2.5px;");

        DropShadow dialogShadow = new DropShadow();
        dialogShadow.setRadius(30.0);
        dialogShadow.setSpread(0.6);
        dialogShadow.setColor(Color.rgb(173, 216, 230, 0.8));
        textContainer.setEffect(dialogShadow);

        displayPicture = new ImageView(img);
        displayPicture.setFitWidth(90);
        displayPicture.setFitHeight(90);
        displayPicture.setPreserveRatio(true);
        
        double width = img.getWidth();
        double height = img.getHeight();
        double size = Math.min(width, height);
        
        Rectangle2D viewport = new Rectangle2D(
            (width - size) / 2,
            (height - size) / 2,
            size,
            size
        );
        displayPicture.setViewport(viewport);

        Circle clip = new Circle(45, 45, 45);
        displayPicture.setClip(clip);

        DropShadow profileShadow = new DropShadow();
        profileShadow.setRadius(30.0);
        profileShadow.setSpread(0.6);
        profileShadow.setColor(Color.rgb(173, 216, 230, 0.8));
        displayPicture.setEffect(profileShadow);

        this.setSpacing(15);
        this.setPadding(new Insets(10));
        this.setMinHeight(Region.USE_PREF_SIZE);
        this.setAlignment(Pos.CENTER_RIGHT);

        this.getChildren().addAll(textContainer, displayPicture);
    }

    /**
     * Flips the dialog box so the image appears on the left.
     */
    private void flip() {
        ObservableList<Node> nodes = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(nodes);
        getChildren().setAll(nodes);
        setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Returns a dialog box for user messages.
     *
     * @param text The message text.
     * @param img  The user's profile image.
     * @return A DialogBox instance for user messages.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "#a8c6ed");
    }

    /**
     * Returns a dialog box for Taskmax's responses.
     *
     * @param text The response text.
     * @param img  The Taskmax profile image.
     * @return A DialogBox instance for Taskmax responses.
     */
    public static DialogBox getTaskDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img, "#D6E0FF");
        db.flip();
        return db;
    }
}