package controller;

import javafx.scene.layout.VBox;

/**
 * Abstract class representing a task input form.
 */
public abstract class TaskInput extends VBox {

    /**
     * Generates the input string for the task.
     *
     * @return the generated input string
     */
    public abstract String generateInput();

    /**
     * Clears the input fields.
     */
    public abstract void clear();
}
