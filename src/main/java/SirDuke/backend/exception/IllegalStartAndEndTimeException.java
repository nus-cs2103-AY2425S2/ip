package SirDuke.backend.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An exception to be thrown when the startTime is after the endTime
 */
public class IllegalStartAndEndTimeException extends IllegalArgumentException {

    LocalDateTime startTime;
    LocalDateTime endTime;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Create an IllegalStartAndEndTimeException.
     *
     * @param startTime The start time of the event task.
     * @param endTime The end time of the event task.
     */
    public IllegalStartAndEndTimeException(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public String toString() {
        return "The start date: "
                + this.startTime.format(formatter)
                + ", cannot be later than the end date: "
                + this.endTime.format(formatter);
    }
}
