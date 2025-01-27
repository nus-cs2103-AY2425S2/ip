import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"); // Input format (with time)
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"); // Output format
    private LocalDateTime deadlineTime;

    /**
     * Constructs a new Deadline task.
     *
     * @param desc the name of the deadline task
     * @param by the deadline date and time
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.deadlineTime = parseDate(by); // Parse the date and time
    }

    /**
     * Parses the date and time string from input format "yyyy-MM-dd HHmm" to LocalDateTime.
     *
     * @param dateStr the date and time in the form of String, in the format: "yyyy-MM-dd HHmm"
     * @return the formatted LocalDateTime object
     */
    private LocalDateTime parseDate(String dateStr) {
        return LocalDateTime.parse(dateStr, inputFormatter); // Parse both date and time
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return the string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + deadlineTime.format(outputFormatter) + ")";
    }

    public String toRawString() {
        return "[D]" + super.toString()
                + " (by: " + deadlineTime.format(inputFormatter) + ")";
    }
}
