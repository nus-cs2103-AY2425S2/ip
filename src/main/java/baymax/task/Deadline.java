package baymax.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task in the Baymax chatbot.
 * A deadline has a name and a due date, which can either be a full date-time
 * (e.g., {@code "yyyy-MM-dd HHmm"}) or just a date (e.g., {@code "yyyy-MM-dd"}).
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER_1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter INPUT_FORMATTER_2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private LocalDateTime byDateTime;
    private LocalDate byDate;
    /**
     * Constructs a new {@code Deadline} task with the specified name and deadline.
     * The task is initially marked as incomplete.
     *
     * @param name The name of the deadline task.
     * @param deadline The due date/time in the format {@code "yyyy-MM-dd"} or {@code "yyyy-MM-dd HHmm"}.
     */
    public Deadline(String name, String deadline) {
        super(name);
        determineDeadlineType(deadline);
        assert (byDate != null || byDateTime != null) : "Deadline date should not be null";
    }

    /**
     * Constructs a new {@code Deadline} task with the specified name, deadline, and completion status.
     *
     * @param name The name of the deadline task.
     * @param deadline The due date/time in the format {@code "yyyy-MM-dd"} or {@code "yyyy-MM-dd HHmm"}.
     * @param isCompleted {@code true} if the deadline is completed, {@code false} otherwise.
     */
    public Deadline(String name, String deadline, boolean isCompleted) {
        super(name, isCompleted);
        determineDeadlineType(deadline);
        assert (byDate != null || byDateTime != null) : "Deadline date should not be null";
    }

    /**
     * Determines whether the deadline is a full date-time or just a date,
     * and assigns it to the respective field.
     *
     * @param deadline The deadline string provided by the user.
     * @throws IllegalArgumentException If the deadline format is invalid.
     */
    private void determineDeadlineType(String deadline) {
        if (deadline.contains(" ")) {
            this.byDateTime = safelyParseDateTime(deadline);
        } else {
            this.byDate = safelyParseDate(deadline);
        }
    }

    /**
     * Safely parses the deadline as a {@code LocalDateTime}.
     *
     * @param deadline The deadline string to parse.
     * @return The parsed {@code LocalDateTime}.
     * @throws IllegalArgumentException If the deadline format is invalid.
     */
    private LocalDateTime safelyParseDateTime(String deadline) {
        try {
            return LocalDateTime.parse(deadline, INPUT_FORMATTER_2);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid datetime format! Use 'yyyy-MM-dd HHmm'.");
        }
    }

    /**
     * Safely parses the deadline as a {@code LocalDate}.
     *
     * @param deadline The deadline string to parse.
     * @return The parsed {@code LocalDate}.
     * @throws IllegalArgumentException If the deadline format is invalid.
     */
    private LocalDate safelyParseDate(String deadline) {
        try {
            return LocalDate.parse(deadline, INPUT_FORMATTER_1);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format! Use 'yyyy-MM-dd'.");
        }
    }

    /**
     * Formats the deadline for display purposes.
     *
     * @return A formatted string representing the deadline.
     */
    private String formatDeadline() {
        if (byDateTime != null) {
            return byDateTime.format(DISPLAY_FORMATTER);
        }
        if (byDate != null) {
            return byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return "Invalid date";
    }

    /**
     * Returns a string representation of the deadline task.
     * The format is: {@code [D]} followed by the task's status, name, and deadline.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDeadline() + ")";
    }

    /**
     * Returns a formatted string suitable for saving the deadline task to a file.
     * The format is: {@code D | isCompleted | Task Name | Due Date}.
     *
     * @return A formatted string representing the deadline task for file storage.
     */
    @Override
    public String toFileString() {
        if (byDateTime != null) {
            return "D" + super.toFileString() + " | " + byDateTime.format(INPUT_FORMATTER_2);
        }
        if (byDate != null) {
            return "D" + super.toFileString() + " | " + byDate.format(INPUT_FORMATTER_1);
        }
        return "D" + super.toFileString() + " | Invalid date";
    }
}
