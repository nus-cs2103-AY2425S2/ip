package qwerty.task;

import qwerty.exceptions.EmptyTaskNameException;

/**
 * Represents an Event task, which includes a description and a time period (from and to).
 * Inherits from {@link Task} and provides methods for formatting and saving the task.
 */
public class Event extends Task {

    private final String FROM; // Start time of the event.
    private final String TO; // End time of the event.

    /**
     * Constructs an Event task with the specified name, start time, and end time.
     *
     * @param name The name or description of the Event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @throws EmptyTaskNameException If the task name is empty.
     */
    public Event(String name, String from, String to) throws EmptyTaskNameException {
        super(name); // Calls the parent constructor to set the task name.
        this.FROM = from;
        this.TO = to;
    }

    /**
     * Returns a string representation of the Event task, including the description and the time period.
     *
     * @return A string representing the Event task, including its description and the time range (from-to).
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + FROM + " to: " + TO + ")"; // Includes event time range.
    }

    /**
     * Returns a string representation of the Event task for saving to file.
     *
     * @return A string representing the Event task in a save-friendly format.
     */
    @Override
    public String toSaveString() {
        return "E | " + (this.isDone ? "1 | " : "0 | ") + this.name + " | " + FROM + " | " + TO;
    }
}
