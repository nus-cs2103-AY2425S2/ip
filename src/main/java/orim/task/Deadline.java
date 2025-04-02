package orim.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent a subclass of Task which is a task with deadline.
 */
public class Deadline extends Task {
    private LocalDate time;

    public Deadline(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time);
    }

    public Deadline(String isDone, String description, String time) {
        super(description, isDone.equals("1"));
        this.time = LocalDate.parse(time);
    }

    /**
     * Transforms the state and detail of the tasks to a string.
     * Will be store in the data file and be reloaded when user open Orim again.
     *
     * @return log to represents the state and detail of the tasks
     */
    public String generateLog() {
        return "D," + (isDone ? 1 : 0) + "," + super.description + "," + time;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
