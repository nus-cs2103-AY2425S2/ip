package alex.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String startTime;
    private String endTime;
    private LocalDate startTimeDate;
    private LocalDate endTimeDate;

    /**
     * Constructor for new event
     * @param content task details
     * @param start start time
     * @param end end time
     */
    public Event(String content, String start, String end) {
        super(content);

        assert content != null : "Content should not be null";
        assert start != null : "Start time should not be null";
        assert end != null : "End time should not be null";

        this.startTime = start.trim();
        this.endTime = end.trim();
    }

    /**
     * Constructor for loading existing event
     * @param content task details
     * @param status if the status is done
     * @param start start time
     * @param end end time
     */
    public Event(String content, String status, String start, String end) {
        super(content, status);

        assert content != null : "Content should not be null";
        assert start != null : "Start time should not be null";
        assert end != null : "End time should not be null";

        this.startTime = start.trim();
        this.endTime = end.trim();
    }

    private boolean isValidDateFormat() {
        try {
            startTimeDate = LocalDate.parse(startTime);
            endTimeDate = LocalDate.parse(endTime);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public String getSavedDataFormat() {
        return "E | " + super.getSavedDataFormat() + " | " + startTime + " | " + endTime + "\n";
    }

    @Override
    public String toString() {
        if (isValidDateFormat()) {
            return "[E]" + super.toString() + " (from: " + startTimeDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + endTimeDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
