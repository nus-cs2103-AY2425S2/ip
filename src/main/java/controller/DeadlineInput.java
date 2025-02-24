package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import utils.DateTimeUtils;

/**
 * Controller for the deadline input form.
 */
public class DeadlineInput extends TaskInput {

    @FXML
    private TextField nameField;
    @FXML
    private DatePicker byDatePicker;
    @FXML
    private ChoiceBox<String> byHourChoiceBox;
    @FXML
    private ChoiceBox<String> byMinuteChoiceBox;

    /**
     * Constructs a DeadlineInput.
     */
    public DeadlineInput() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("fxml/DeadlineInput.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
        }
        populateChoiceBoxes();
    }

    /**
     * Populates the choice boxes with hour and minute values.
     */
    private void populateChoiceBoxes() {
        for (int i = 0; i < 24; i++) {
            byHourChoiceBox.getItems().add(String.format("%02d", i));
        }
        for (int i = 0; i < 12; i++) {
            byMinuteChoiceBox.getItems().add(String.format("%02d", i * 5
            ));
        }
    }

    /**
     * Generates the input string for the deadline task.
     *
     * @return the input string
     */
    @Override
    public String generateInput() {
        String name = nameField.getText();
        String dateTime = DateTimeUtils.formatRawDateTime(
            byDatePicker.getValue().toString(), byHourChoiceBox.getValue(), byMinuteChoiceBox.getValue()
        );
        return "deadline " + name + " /by " + dateTime;
    }

    /**
     * Clears the input fields.
     */
    @Override
    public void clear() {
        nameField.clear();
        byDatePicker.setValue(null);
        byHourChoiceBox.setValue(null);
        byMinuteChoiceBox.setValue(null);
    }
}
