package woof.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import woof.exception.IllegalDateTimeException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Create an instance of an event task with parsing of local date and time.
     *
     * @param string Description of the task.
     * @param from Date and time on which the task starts.
     * @param to Date and time on which the task ends
     * @throws IllegalDateTimeException Date and time is not given in the correct format of "yyyy-MM-dd hh:mm"
     */
    public Event(String string, String from, String to) throws IllegalDateTimeException {
        super(string);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            throw new IllegalDateTimeException("");
        }
    }

    /**
     * Returns a description of the task to be displayed on CLI.
     *
     * @return The description of the task.
     */
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")";
    }

    /**
     * Returns the description of the task to be recorded locally.
     *
     * @return The description of the task.
     */
    @Override
    public String print() {
        return String.format("E | %s | %s | %s", super.print(),
                from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
