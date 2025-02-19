package tasks;

import java.time.LocalDateTime;

/**
 * Concrete class that encapsulates information about an event task. It has the
 * additional variables fromTime and toTime to keep track of this.
 */
public class TaskEvent extends Task {
    private LocalDateTime fromTime;
    private LocalDateTime toTime;

    /**
     * Constructor for the TaskEvent class
     * @param name Name of the task
     * @param fromTime Time that the task starts
     * @param toTime Time that the task ends
     */
    public TaskEvent(String name, LocalDateTime fromTime, LocalDateTime toTime) {
        super(name);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    @Override
    public String getFullName() {
        return "[E]" + super.getFullName() + " (from: " + getFromTime() + " | to: " + getToTime() + ")";
    }

    public String getFromTime() {
        return getFormattedTime(fromTime);
    }

    public String getToTime() {
        return getFormattedTime(toTime);
    }

    public LocalDateTime getFromTimeLdt() {
        return fromTime;
    }

    /**
     * Formats datetime in the correct string format and returns it
     * @return Formatted deadline
     */
    public String getFormattedTime(LocalDateTime dateTime) {
        String year = String.format("%04d", dateTime.getYear());
        String month = String.format("%02d", dateTime.getMonthValue());
        String day = String.format("%02d", dateTime.getDayOfMonth());
        String hour = String.format("%02d", dateTime.getHour());
        String minute = String.format("%02d", dateTime.getMinute());
        return year + "-" + month + "-" + day + " " + hour + ":" + minute;
    }
}
