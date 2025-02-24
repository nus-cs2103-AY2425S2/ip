package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import utils.UiUtils;

/**
 * Abstract class representing a dialog box.
 */
public abstract class DialogBox extends HBox {

    protected static final String DEFAULT_USER_NAME = "annoyinguser";
    protected static final String DEFAULT_USER_IMAGE = "images/defaultuser.jpg";
    protected static final String DEFAULT_ALICE_NAME = "alice";
    protected static final String DEFAULT_ALICE_IMAGE = "images/defaultuser.jpg";

    @FXML
    private Circle imageCircle;
    @FXML
    private TextField nameField;
    @FXML
    private TextArea messageArea;

    /**
     * Fills the dialog box with the given message, name, and image URL.
     *
     * @param message the message
     * @param name the name
     * @param imageUrl the image URL
     */
    protected void fill(String message, String name, String imageUrl) {
        nameField.setText(name);
        messageArea.setText(message);
        double messageAreaPrefHeight = computeMessageAreaPrefHeight(messageArea, message);
        messageArea.setPrefHeight(messageAreaPrefHeight);
        setPrefHeight(messageAreaPrefHeight + nameField.getHeight() + 
                      getPadding().getTop() + getPadding().getBottom());
        imageCircle.setFill(new ImagePattern(new Image(Main.class.getResourceAsStream(imageUrl))));
    }

    /**
     * Computes the preferred height of the message area.
     *
     * @param messageArea the message area
     * @param message the message
     * @return the preferred height
     */
    protected static double computeMessageAreaPrefHeight(TextArea messageArea, String message) {
        final double BUFFER = 50;
        double fontSizePixels = UiUtils.pointsToPixels(messageArea.getFont().getSize());
        Matcher m = Pattern.compile("(\r\n)|(\n)|(\r)").matcher(message);
        int lines = 1;
        while (m.find()) {
            lines++;
        }
        return BUFFER + fontSizePixels * lines;
    }
}
