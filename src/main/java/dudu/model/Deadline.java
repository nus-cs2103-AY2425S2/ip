package dudu.model;

import dudu.utils.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    /** Deadline of task **/
    private final LocalDateTime by;

    /**
     * Creates Deadline object.
     *
     * @param name Title of the deadline task.
     * @param by Deadline of the task.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns true if target date is the same date as the current date.
     *
     * @param targetDate Date of day to be viewed.
     * @return True if target date is same day as day to be viewed.
     */
    public boolean isOnSameDay(LocalDateTime targetDate) {
        return by.toLocalDate().equals(targetDate.toLocalDate());
    }

    /**
     * Returns Deadline task in String format for data.txt.
     *
     * @param taskType Task type to be displayed.
     * @param isDone Completion of task to be displayed.
     * @return String format of task for data.txt.
     */
    public String toStringFileFormat(String taskType, String isDone) {
        return String.format("%s | %s | %s | %s", taskType, isDone, this.getName(), this.getBy());
    }

    /**
     * Returns deadline task in String format.
     *
     * @return Line with status, title and deadline of task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeParser.checkFormat(by);
        return "[D]" + super.toString() + "(by: " + by.format(formatter) + ")";
    }
}
