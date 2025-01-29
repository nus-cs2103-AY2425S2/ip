package scooby.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    // Input format (with time)
    private static final DateTimeFormatter INPUTFORMATTER  = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    // Output format
    private static final DateTimeFormatter OUTPUTFORMATTER  = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs a new Event task.
     *
     * @param desc the name of the event task
     * @param from the start date and time of the event
     * @param to the end date and time of the event
     */
    public Event(String desc, String from, String to) {
        super(desc);
        this.startTime = parseDate(from); // Parse the date and time
        this.endTime = parseDate(to); // Parse the date and time
    }

    /**
     * Parses the date and time string from input format "yyyy-MM-dd HHmm" to LocalDateTime.
     *
     * @param dateStr the date and time in the form of String, in the format: "yyyy-MM-dd HHmm"
     * @return the formatted LocalDateTime object
     */
    private LocalDateTime parseDate(String dateStr) {
        return LocalDateTime.parse(dateStr, INPUTFORMATTER); // Parse both date and time
    }

    /**
     * Returns the string representation of the event.
     *
     * @return the string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startTime.format(OUTPUTFORMATTER) + " to: " + endTime.format(OUTPUTFORMATTER) + ")";
    }

    /**
     * Returns the raw representation of the String to be read from the txt file
     * @return returns the string to be added into the txt file
     */
    @Override
    public String toRawString() {
        return "[E]" + super.toString()
                + " (from: " + startTime.format(INPUTFORMATTER) + " to: " + endTime.format(INPUTFORMATTER) + ")";
    }
}
