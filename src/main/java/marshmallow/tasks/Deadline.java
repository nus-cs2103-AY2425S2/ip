package marshmallow.tasks;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.mdimension.jchronic.Chronic;
import com.mdimension.jchronic.Options;
import com.mdimension.jchronic.tags.Pointer;
import com.mdimension.jchronic.utils.Span;

import marshmallow.MarshmallowException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    private LocalDateTime endTime;

    /**
     * Constructs a deadline.
     *
     * @param description The description of the deadline.
     * @param endTimeString The end time of the deadline.
     */
    public Deadline(String description, String endTimeString) {
        super(description);

        Span parsedSpan = Chronic.parse(endTimeString, new Options(Pointer.PointerType.FUTURE));
        if (parsedSpan == null) {
            throw new MarshmallowException("I don't understand the time :(");
        }

        this.endTime = Instant.ofEpochSecond(parsedSpan.getBegin()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @Override
    public String toSaveString() {
        return String.format("D | %s | %s", super.toSaveString(), endTime.format(formatter));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), endTime.format(formatter));
    }
}
