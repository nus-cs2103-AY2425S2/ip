package tasks;

import java.time.LocalDateTime;

/**
 * Concrete class that encapsulates information about a task with deadline. It has the
 * additional variable deadline to keep track of this.
 */
public class TaskDeadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for the TaskDeadline class
     * @param name Name of the task
     * @param deadline Deadline of the task
     */
    public TaskDeadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String getFullName() {
        return "[D]" + super.getFullName() + " (by: " + getDeadline() + ")";
    }

    /**
     * Formats the deadline in the correct string format and returns it
     * @return Formatted deadline
     */
    public String getDeadline() {
        String year = String.format("%04d", deadline.getYear());
        String month = String.format("%02d", deadline.getMonthValue());
        String day = String.format("%02d", deadline.getDayOfMonth());
        String hour = String.format("%02d", deadline.getHour());
        String minute = String.format("%02d", deadline.getMinute());
        return year + "-" + month + "-" + day + " " + hour + ":" + minute;
    }

    public LocalDateTime getDeadlineLdt() {
        return deadline;
    }
}
