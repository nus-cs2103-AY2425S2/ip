package Lara;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * A custom HBox component representing a dialog box used in the Lara application.
 * This class provides a convenient way to display messages and images in a visually
 * appealing and structured format.
 */
public class DialogBox extends HBox {

    private Label messageText;
    private ImageView avatar;

    /**
     * Constructs a new DialogBox with specified text, image and background.
     *
     * @param message The text to be displayed in message box.
     * @param image The image to be displayed in the message box.
     * @param bgColor The background color of the message box.
     */
    public DialogBox(String message, Image image, Color bgColor, Font fontStyle) {
        this.messageText = new Label(message);
        this.avatar = new ImageView(image);

        messageText.setWrapText(true);
        messageText.setFont(fontStyle);
        avatar.setFitWidth(100.0);
        avatar.setFitHeight(100.0);

        this.setSpacing(10);
        this.setPadding(new Insets(10));

        Circle clip = new Circle(50, 50, 50);
        avatar.setClip(clip);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setBackground(new Background(new BackgroundFill(bgColor, CornerRadii.EMPTY, Insets.EMPTY)));

        this.getChildren().addAll(messageText, avatar);
    }

    private void flipLayout() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> temp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(temp);
        this.getChildren().setAll(temp);
    }

    public static DialogBox getUserMessage(String message, Image image) {
        Color paleBlue = Color.color(Color.LIGHTBLUE.getRed(),
                Color.LIGHTBLUE.getGreen(),
                Color.LIGHTBLUE.getBlue(),
                0.40);
        Font userFontStyle = new Font("Times New Roman", 14);
        return new DialogBox(message, image, paleBlue, userFontStyle);
    }

    public static DialogBox getDukeMessage(String message, Image image) {
        Color palePurple = Color.color(Color.ORCHID.getRed(),
                Color.ORCHID.getGreen(),
                Color.ORCHID.getBlue(),
                0.40);
        Font botFontStyle = new Font("Bodoni 72", 12);
        var msgBox = new DialogBox(message, image, palePurple, botFontStyle);
        msgBox.flipLayout();
        return msgBox;
    }
}
