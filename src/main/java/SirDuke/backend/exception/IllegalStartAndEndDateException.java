package SirDuke.backend.exception;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An exception to be thrown when the startTime is after the endTime
 */
public class IllegalStartAndEndDateException extends IllegalArgumentException {

    LocalDate startTime;
    LocalDate endTime;
    public IllegalStartAndEndDateException(LocalDate startTime, LocalDate endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public String toString() {
        return "The start date: "
                + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", cannot be later than the end date: "
                + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
