package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import utils.DateTimeUtils;

/**
 * Controller for the event input form.
 */
public class EventInput extends TaskInput {

    @FXML
    private TextField nameField;
    @FXML
    private DatePicker fromDatePicker;
    @FXML
    private DatePicker toDatePicker;
    @FXML
    private ChoiceBox<String> fromHourChoiceBox;
    @FXML
    private ChoiceBox<String> fromMinuteChoiceBox;
    @FXML
    private ChoiceBox<String> toHourChoiceBox;
    @FXML
    private ChoiceBox<String> toMinuteChoiceBox;

    /**
     * Constructs an EventInput.
     */
    public EventInput() {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("fxml/EventInput.fxml"));
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
            fromHourChoiceBox.getItems().add(String.format("%02d", i));
            toHourChoiceBox.getItems().add(String.format("%02d", i));
        }
        for (int i = 0; i < 12; i++) {
            fromMinuteChoiceBox.getItems().add(String.format("%02d", i * 5));
            toMinuteChoiceBox.getItems().add(String.format("%02d", i * 5));
        }
    }

    /**
     * Generates the input string for the event task.
     *
     * @return the input string
     */
    @Override
    public String generateInput() {
        String name = nameField.getText();
        String fromDateTime = DateTimeUtils.formatRawDateTime(
            fromDatePicker.getValue().toString(), fromHourChoiceBox.getValue(), fromMinuteChoiceBox.getValue()
        );
        String toDateTime = DateTimeUtils.formatRawDateTime(
            toDatePicker.getValue().toString(), toHourChoiceBox.getValue(), toMinuteChoiceBox.getValue()
        );
        return "event " + name + " /from " + fromDateTime + " /to " + toDateTime;
    }

    /**
     * Clears the input fields.
     */
    @Override
    public void clear() {
        nameField.clear();
        fromDatePicker.setValue(null);
        toDatePicker.setValue(null);
        fromHourChoiceBox.setValue(null);
        fromMinuteChoiceBox.setValue(null);
        toHourChoiceBox.setValue(null);
        toMinuteChoiceBox.setValue(null);
    }
}
