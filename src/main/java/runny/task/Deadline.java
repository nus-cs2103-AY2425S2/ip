package runny.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in Runny Chatbot.
 */
public class Deadline extends Task {
    protected final LocalDateTime by;

    /**
     * Creates a Deadline task with the given name and time.
     *
     * @param description The name of the deadline task.
     * @param by The deadline date and time as a string.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = convertTime(by);
    }

    /**
     * Converts a string time representation to a LocalDateTime object.
     *
     * @param dateTime The time as a string.
     * @return The LocalDateTime object representing the time.
     * @throws DateTimeException If there's an issue parsing the time string.
     */
    public LocalDateTime convertTime(String dateTime) throws DateTimeException {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (!dateTime.contains(" ")) {
            dateTime += " 2359";
        }
        return LocalDateTime.parse(dateTime, formatDate);
    }

    /**
     * Converts the deadline task to a string for saving.
     *
     * @return A string representation of the task for saving.
     */
    @Override
    public String save() {
        String dateTimeString = this.by.toString().replace("T", " ").replace(":", "");
        return (super.isDone ? "1 " : "0 ") + "deadline " + super.description + "/by" + dateTimeString;
    }

    /**
     * Converts the deadline task to a string.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}
