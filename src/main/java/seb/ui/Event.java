package seb.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String start;
    private String end;

    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Converts Event object to String to be stored in data file
     *
     * @return String of event details
     */
    @Override
    public String getDate() {
        return this.start;
    }

    /**
     * Update description, start time or end time of event task
     *
     * @param detail the attribute of event to be edited
     * @param value the new attribute
     * @throws SebException for invalid input
     */
    @Override
    public void update(String detail, String value) throws SebException {
        if (detail.contains("desc")) {
            this.description = value;
        } else if (detail.contains("start")) {
            DateTimeFormatter formatInput = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm");
            LocalDateTime compare = LocalDateTime.parse(this.end, formatInput);
            this.start = Parser.checkDateValidity(Parser.parseDateTime(value), compare)[0];
        } else if (detail.contains("end")) {
            DateTimeFormatter formatInput = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm");
            LocalDateTime compare = LocalDateTime.parse(this.start, formatInput);
            this.end = Parser.checkDateValidity(compare, Parser.parseDateTime(value))[1];
        } else {
            throw new SebException("Invalid format!");
        }
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + this.start + " | " + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: "
                + this.end + ")";
    }
}
