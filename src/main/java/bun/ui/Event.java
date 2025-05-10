package bun.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, String start, String end) throws DateFormatException {
        super(description);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), start, end);
    }

    @Override
    public String getStoredString() {
        return String.format("E | %s | %s | %s", super.getStoredString(), this.start, this.end);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event e) {
            return super.equals(obj) || this.start.equals(e.start) || this.end.equals(e.end);
        } else {
            return false;
        }
    }
}
