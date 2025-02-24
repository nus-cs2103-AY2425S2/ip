package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the form sidebar. Currently it provides a means for the user
 * to use a form to circumvent typing complicated task commands.
 */
public class FormSidebar extends VBox {

    @FXML
    private Label taskTypeLabel;
    @FXML
    private ToggleButton todoButton;
    @FXML
    private ToggleButton deadlineButton;
    @FXML
    private ToggleButton eventButton;
    @FXML
    private Button addButton;
    @FXML
    private StackPane formPane;

    private TodoInput todoInput = new TodoInput();
    private DeadlineInput deadlineInput = new DeadlineInput();
    private EventInput eventInput = new EventInput();

    private MainWindow mainWindow;

    private enum TaskType {
        TODO, DEADLINE, EVENT
    }

    private TaskType selectedTaskType = TaskType.TODO;

    /**
     * Constructs a FormSidebar with the given main window. The form sidebar
     * currently only provides a means for the user to circumvent complicated
     * command usage. This only communcicates to the main window to parse user
     * input received as into a command to be sent as a message.
     *
     * @param mainWindow the main window
     */
    public FormSidebar(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/FormSidebar.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
        }
        this.selectedTaskType = TaskType.TODO;
        onTodoButtonAction();
    }

    /**
     * Updates the task buttons based on the selected task type.
     */
    private void updateTaskButtons() {
        todoButton.setSelected(selectedTaskType == TaskType.TODO);
        deadlineButton.setSelected(selectedTaskType == TaskType.DEADLINE);
        eventButton.setSelected(selectedTaskType == TaskType.EVENT);
    }

    /**
     * Handles the action for the todo button. Set selected event type to todo
     * and places the appropriate form in the form pane.
     */
    @FXML
    private void onTodoButtonAction() {
        selectedTaskType = TaskType.TODO;
        formPane.getChildren().clear();
        formPane.getChildren().add(todoInput);
        updateTaskButtons();
    }

    /**
     * Handles the action for the deadline button. Set selected event type to
     * deadline and places the appropriate form in the form pane.
     */
    @FXML
    private void onDeadlineButtonAction() {
        selectedTaskType = TaskType.DEADLINE;
        formPane.getChildren().clear();
        formPane.getChildren().add(deadlineInput);
        updateTaskButtons();
    }

    /**
     * Handles the action for the event button. Set selected event type to event
     * and places the appropriate form in the form pane.
     */
    @FXML
    private void onEventButtonAction() {
        selectedTaskType = TaskType.EVENT;
        formPane.getChildren().clear();
        formPane.getChildren().add(eventInput);
        updateTaskButtons();
    }

    /**
     * Handles the action for the add button. Format it as a user message and
     * send to MainWindow. Then clear all inputs.
     */
    @FXML
    private void onAddButtonAction() {
        switch (selectedTaskType) {
            case TODO -> {
                mainWindow.handleUserInput(todoInput.generateInput());
            }
            case DEADLINE -> {
                mainWindow.handleUserInput(deadlineInput.generateInput());
            }
            case EVENT -> {
                mainWindow.handleUserInput(eventInput.generateInput());
            }
        }
        todoInput.clear();
        deadlineInput.clear();
        eventInput.clear();
    }
}
