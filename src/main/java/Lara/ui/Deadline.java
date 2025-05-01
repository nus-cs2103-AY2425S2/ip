/**
 * The Deadline class represents a task that has a specific due date/time.
 * It is a subclass of Task and includes a deadline field.
 *
 * Example: "Submit assignment /by Sunday"
 *
 * @author Maliha Haque
 * @version 1.0
 */

package Lara.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            this.by = LocalDateTime.parse(by, formatter1);
        } catch (Exception e) {
            this.by = LocalDateTime.parse(by, formatter2);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")";

    }
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
    @Override
    public LocalDateTime getComparableDate() {
        return by;
    }
}
