package godbot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline.
 * Inherits from the {@link Task} class and adds a deadline attribute.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;
    
    private static final DateTimeFormatter[] INPUT_FORMATTERS = {
        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
        DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")
    };

    private static final DateTimeFormatter[] DATE_ONLY_FORMATTERS = {
        DateTimeFormatter.ofPattern("yyyy-MM-dd"),
        DateTimeFormatter.ofPattern("d/M/yyyy"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy"),
        DateTimeFormatter.ofPattern("d-M-yyyy") 
    };

    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter FILE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a Deadline task with a description and deadline.
     * The task is marked as not done by default.
     *
     * @param description The description of the deadline task.
     * @param deadline    The due date and time of the task in accepted formats.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = parseDeadline(deadline);
    }

    /**
     * Constructs a Deadline task with a description, deadline, and completion status.
     *
     * @param description The description of the deadline task.
     * @param deadline    The due date of the task in accepted formats.
     * @param isDone      The completion status of the task. True if completed, otherwise false.
     */
    public Deadline(String description, String deadline, boolean isDone) {
        super(description);
        this.deadline = parseDeadline(deadline);
        this.isDone = isDone;
    }

    /**
     * Parses the deadline string into a LocalDateTime object, supporting multiple date formats.
     *
     * @param deadlineString The string containing the deadline.
     * @return A LocalDateTime object representing the deadline.
     */
    private LocalDateTime parseDeadline(String deadlineString) {
        for (DateTimeFormatter formatter : INPUT_FORMATTERS) {
            try {
                return LocalDateTime.parse(deadlineString, formatter);
            } catch (DateTimeParseException ignored) {
            }
        }
        
        for (DateTimeFormatter formatter : DATE_ONLY_FORMATTERS) {
            try {
                return LocalDate.parse(deadlineString, formatter).atTime(23, 59);
            } catch (DateTimeParseException ignored) {
            }
        }

        throw new IllegalArgumentException("Invalid date format, mortal. Use one of: yyyy-MM-dd HHmm, d/M/yyyy HHmm, dd-MM-yyyy HHmm, yyyy-MM-dd, d/M/yyyy, or dd-MM-yyyy.");
    }

    /**
     * Returns a string representation of the Deadline task for display purposes.
     *
     * @return A formatted string showing the task type, status, description, and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(OUTPUT_FORMATTER) + ")";
    }

    /**
     * Converts the Deadline task to a file-friendly format for saving to storage.
     *
     * @return A string representing the task in the format {@code D | isDone | description | deadline}.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline.format(FILE_FORMATTER);
    }
}

