package ziyang;

import java.time.LocalDate;

/**
 * Represents an event task with start and end dates.
 * Extends the basic Task class with time period functionality.
 */
public class eventTask extends Task {
    /** The start date of the event */
    public LocalDate start;
    /** The end date of the event */
    public LocalDate end;

    /**
     * Constructs a new event task with description and time period.
     * @param description the description of the event
     * @param start the start date in ISO format (yyyy-MM-dd)
     * @param end the end date in ISO format (yyyy-MM-dd)
     */
    public eventTask(String description, String start, String end) {
        super(description);
        LocalDate startdate = LocalDate.parse(start);
        LocalDate enddate = LocalDate.parse(end);
        this.start = startdate;
        this.end = enddate;
    }

    /**
     * Returns a string representation of the event task.
     * @return formatted string with event details and time period
     */
    @Override
    public String toString() {
        return "[E]" + this.getStatusIcon() + this.description + "(time: " + this.start + " ~ " + this.end + ")";
    }
}
