/**
 * Represents a subclass of Task class, a task with a deadline
 * The description argument describes what the deadline task is about
 * and the date argument specifies the deadline of the task in YYYY-MM-DD format
 */

package lemon;

import java.time.LocalDate;

public class Deadline extends Task{
    protected LocalDate date;
    //protected String by;

    /**
     * Construct a deadline task with a specified description and deadline
     * @param description The description of the task
     * @param date The deadline of the task
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "/"
                + date.getDayOfMonth() + " " + date.getMonth() + " " + date.getYear();
    }

}
