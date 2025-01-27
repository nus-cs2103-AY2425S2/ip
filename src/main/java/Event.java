import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     *
     * @param dateStr is the date in the form of String, in the format: "yyyy-mm-dd HHmm"
     * @return the formatted local date time in the form of "MMM dd yyyy"
     */
    private LocalDateTime parseDate(String dateStr) {
        // Expected format: yyyy-MM-dd HHmm
        return LocalDateTime.parse(dateStr, formatter);
    }

    /**
     * Constructs a new Event task.
     *
     * @param desc the name of the deadline task
     * @param from is the start date
     * @param to is the end date of the event
     */
    public Event(String desc, String from, String to) {
        super(desc);
        this.startTime = parseDate(from);
        this.endTime = parseDate(to);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startTime.format(formatter) + " to: " + endTime.format(formatter) + ")";
    }
}