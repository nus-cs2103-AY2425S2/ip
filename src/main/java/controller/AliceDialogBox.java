package controller;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import model.response.Response;

/**
 * Controller for the Alice dialog box.
 */
public class AliceDialogBox extends DialogBox {

    private AliceDialogBox(String message, String name, String imageUrl) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/AliceDialogBox.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
        }
        this.fill(message, name, imageUrl);
    }

    /**
     * Factory method to create an Alice dialog box.
     *
     * @param input the input message
     * @return the Alice dialog box
     */
    public static AliceDialogBox of(String input) {
        return of(input, DEFAULT_ALICE_NAME, DEFAULT_ALICE_IMAGE);
    }

    public static AliceDialogBox of(String input, String imageUrl) {
        return of(input, DEFAULT_ALICE_NAME, imageUrl);
    }

    /**
     * Currently unused factory method to create an Alice dialog box. One day
     * when there are customizations for the Alice dialog box, this method will
     * be useful.
     *
     * @param input the input message
     * @param imageUrl the image URL. Currently used to reflect her anger level.
     * @return the Alice dialog box
     */
    public static AliceDialogBox of(String input, String name, String imageUrl) {
        return new AliceDialogBox(input, name, imageUrl);
    }

    /**
     * Converts a response into an array of Alice dialog boxes.
     *
     * @param response the response
     * @return the array of Alice dialog boxes
     */
    public static AliceDialogBox[] fromResponse(Response response) {
        return List.of(response.getMessages()).stream()
                .map(AliceDialogBox::of)
                .toArray(AliceDialogBox[]::new);
    }

    /**
     * Converts a response into an array of Alice dialog boxes with the given
     * image URL.
     *
     * @param response the response
     * @param imageUrl the image URL
     * @return the array of Alice dialog boxes
     */
    public static AliceDialogBox[] fromResponse(Response response, String imageUrl) {
        return List.of(response.getMessages()).stream()
                .map(resp -> AliceDialogBox.of(resp, imageUrl))
                .toArray(AliceDialogBox[]::new);
    }

}
