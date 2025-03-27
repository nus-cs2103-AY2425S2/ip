package qwerty.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import qwerty.exceptions.EmptyTaskNameException;

/**
 * Represents a Deadline task, which includes a description and a due date and time.
 * Inherits from {@link Task} and provides methods for formatting and saving the task.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private LocalDateTime by; // Deadline date and time.

    /**
     * Constructs a Deadline task with the specified name and deadline date/time.
     *
     * @param name The name or description of the Deadline task.
     * @param by The date and time by which the task is due, in the format "yyyy-MM-dd HHmm".
     * @throws EmptyTaskNameException If the task name is empty.
     * @throws IllegalArgumentException If the date format is incorrect.
     */
    public Deadline(String name, String by) throws EmptyTaskNameException {
        super(name); // Calls the parent constructor to set the task name.
        try {
            this.by = LocalDateTime.parse(by, INPUT_FORMATTER); // Parses the deadline date.
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Use yyyy-MM-dd HHmm (e.g., 2019-10-15 1800).");
        }
    }

    /**
     * Returns a string representation of the Deadline task, including the description and the formatted due date.
     *
     * @return A string representing the Deadline task, including its description and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMATTER) + ")"; // Includes formatted due date.
    }

    /**
     * Returns a string representation of the Deadline task for saving to file.
     *
     * @return A string representing the Deadline task in a save-friendly format.
     */
    @Override
    public String toSaveString() {
        return "D | " + (this.isDone ? "1 | " : "0 | ") + this.name + " | " + by.format(INPUT_FORMATTER);
    }
}
