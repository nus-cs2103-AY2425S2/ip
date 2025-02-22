package marshmallow.ui;

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
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * A dialog box to display messages.
 */
public class DialogBox extends HBox {
    @FXML
    private Label label;
    @FXML
    private Rectangle dp;

    /**
     * Creates a dialog box with the given text and image.
     *
     * @param text The text to display.
     * @param img The image to display.
     * @param alignment The alignment of the dialog box.
     */
    public DialogBox(String text, Image img, DialogBoxAlignment alignment) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainViewController.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        label.setText(text);
        dp.setFill(new ImagePattern(img));

        if (alignment == DialogBoxAlignment.LEFT) {
            flip();
        }
    }

    /**
     * Creates a dialog box with the given text and image.
     *
     * @param text The text to display.
     * @param img The image to display.
     */
    public DialogBox(String text, Image img) {
        this(text, img, DialogBoxAlignment.RIGHT);
    }

    private void flip() {
        ObservableList<Node> children = FXCollections.observableArrayList(getChildren());
        Collections.reverse(children);
        getChildren().setAll(children);
        setAlignment(Pos.TOP_LEFT);
        label.getStyleClass().add("reply-label");
    }
}
