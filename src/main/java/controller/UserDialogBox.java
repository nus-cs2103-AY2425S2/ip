package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

/**
 * Controller for the user dialog box.
 */
public class UserDialogBox extends DialogBox {

    /**
     * Constructs a UserDialogBox with the given input, name, and image URL.
     *
     * @param input the input message
     * @param name the name
     * @param imageUrl the image URL
     */
    private UserDialogBox(String input, String name, String imageUrl) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/UserDialogBox.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
        }
        this.fill(input, name, imageUrl);
    }

    /**
     * Factory method to create a user dialog box.
     *
     * @param input the input message
     * @return the user dialog box
     */
    public static UserDialogBox of(String input) {
        return of(input, DEFAULT_USER_NAME, DEFAULT_USER_IMAGE);
    }

    /**
     * Currently unused method to create a user dialog box. One day when there
     * are customizations for the user dialog box, this method will be useful.
     *
     * @param input the input message
     * @param name the name
     * @param imageUrl the image URL
     * @return the user dialog box
     */
    public static UserDialogBox of(String input, String name, String imageUrl) {
        return new UserDialogBox(input, name, imageUrl);
    }

}
