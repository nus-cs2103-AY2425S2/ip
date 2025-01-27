import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private LocalDateTime deadline;  // Use LocalDateTime for storing the deadline

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
     * Constructs a new Deadline task.
     *
     * @param desc the name of the deadline task.
     * @param by is the date where the task is due by.
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.deadline = parseDate(by);  // Parse the string to LocalDateTime
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + deadline.format(formatter) + ")";
    }
}