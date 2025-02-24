package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

/**
 * Controller for the todo input form.
 */
public class TodoInput extends TaskInput {

    @FXML
    private TextField nameField;

    public TodoInput() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("fxml/TodoInput.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
        }
    }

    @Override
    public String generateInput() {
        String name = nameField.getText();
        return "todo " + name;
    }

    @Override
    public void clear() {
        nameField.clear();
    }
}
