package tasker.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that handles date and time.
 */
public abstract class DateTimeTask extends Task {
    /** Format of date and time input */
    public static final String INPUT_FORMAT = "d/M/yyyy HHmm";

    /**
     * Class contructor.
     *
     * @param description Description of this task.
     * @param type        The type of this task.
     */
    DateTimeTask(String description, TaskType type) {
        super(description, type);
    }

    /**
     * Class contructor.
     *
     * @param description Description of this task.
     * @param type        The type of this task.
     * @param isDone      Whether this task is done.
     */
    DateTimeTask(String description, TaskType type, boolean isDone) {
        super(description, type, isDone);
    }

    /**
     * Parses the input of date and time string.
     *
     * @param dateTimeString The string to parse.
     * @return The date and time.
     */
    public static LocalDateTime parseInput(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(DateTimeTask.INPUT_FORMAT));
    }

    /**
     * Formats the output of date and time string.
     *
     * @param dateTime The date and time to output.
     * @return The date and time as a String.
     */
    String formatOutput(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }
}
