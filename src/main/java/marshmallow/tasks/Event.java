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
 * Represents an event task.
 */
public class Event extends Task {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs an event.
     *
     * @param description The description of the event.
     * @param startTimeString The start time of the event.
     * @param endTimeString The end time of the event.
     */
    public Event(String description, String startTimeString, String endTimeString) {
        super(description);

        Options parserOpts = new Options(Pointer.PointerType.FUTURE);
        Span parsedSpanStart = Chronic.parse(startTimeString, parserOpts);
        Span parsedSpanEnd = Chronic.parse(endTimeString, parserOpts);
        if (parsedSpanStart == null || parsedSpanEnd == null) {
            throw new MarshmallowException("I don't understand the time :(");
        }

        ZoneId zoneId = ZoneId.systemDefault();
        this.startTime = Instant.ofEpochSecond(parsedSpanStart.getBegin()).atZone(zoneId).toLocalDateTime();
        this.endTime = Instant.ofEpochSecond(parsedSpanEnd.getBegin()).atZone(zoneId).toLocalDateTime();
    }

    @Override
    public String toSaveString() {
        return String.format("E | %s | %s | %s",
                super.toSaveString(),
                startTime.format(formatter),
                endTime.format(formatter));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                startTime.format(formatter),
                endTime.format(formatter));
    }
}
