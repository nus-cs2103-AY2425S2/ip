package teddy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private final String start;
    private final String end;

    public Event(String input, String start, String end) {
        super(input);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        try {
            return "[E]" + super.toString() + " (from: " +
                    LocalDate.parse(this.start).format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: "
                            + LocalDate.parse(this.end).format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                            + ")";
        } catch (DateTimeParseException e) {
            return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Event) {
            Event task = (Event) obj;
            return this.getTask().equals(task.getTask()) && this.getStart().equals(task.getStart())
                    && this.getEnd().equals(task.getEnd());
        }
        return false;
    }
}
