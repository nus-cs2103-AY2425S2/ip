package mab.task;

import java.time.LocalDateTime;

import mab.util.DateTimeUtil;

/**
 * Represents a deadline-bound task.
 * Includes time-based completion target.
 */
public class Deadlines extends Task {
    LocalDateTime deadline;

    /**
     * Creates deadline task with time constraint.
     * 
     * @param t Description text
     * @param d Completion status
     * @param dl Deadline date/time string (multiple formats supported)
     */
    public Deadlines(String t, boolean d, String dl) {
        super(t,d); 
        deadline = DateTimeUtil.parseDateTime(dl);
    }

    /**
     * @return Display format: {@code [D][status] [description] (by: [formatted date])}
     */
    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)", "D", super.toString(), 
                DateTimeUtil.localDateTimeToString(deadline)
                );
    }
    public String getDeadline() {
        return deadline.toString();
    }

    /**
     * returns the deadline date/time
     *
     * @return Deadline date/time
     */
    @Override
    public LocalDateTime getStartDateTime() {
        return deadline;
    }

    /**
     * @return Storage format: {@code D | [status] | [description] | [datetime]}
     * @implSpec Uses {@link DateTimeUtil#localDateTimeToString} for datetime formatting
     */
    public String getSaveString(){
        return String.format("D | %s | %s | %s", Boolean.toString(super.getIsDone()), super.getText(), 
                DateTimeUtil.localDateTimeToString(deadline)
                );
    }
}
