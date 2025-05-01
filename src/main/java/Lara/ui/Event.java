/**
 * The Event class represents a task that occurs at a specific time.
 * It is a subclass of Task and includes a date/time field.
 *
 * Example: "Team meeting /at Monday 2pm"
 *
 * @author Maliha Haque
 * @version 1.0
 */
package Lara.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;


    public Event(String description, String start, String end) {
        super(description);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            this.start = LocalDateTime.parse(start, formatter1);
            this.end = LocalDateTime.parse(end, formatter1);
        } catch (Exception e) {
            this.start = LocalDateTime.parse(start, formatter2);
            this.end = LocalDateTime.parse(end, formatter2);
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter console = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[E]" + super.toString() + " (from: " + start.format(console) + " to: " + end.format(console) + ")";

    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + start.format(formatter) + " | " + end.format(formatter);
    }

    @Override
    public LocalDateTime getComparableDate() {
        return start;
    }
}

