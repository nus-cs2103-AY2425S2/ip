package tyler.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    //@@author VikramGoyal23-reused
    // Reused from https://nus-cs2103-ay2425s2.github.io/website/se-book-adapted/projectDuke/
    // with minor modifications

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        from = from.strip();
        String[] fromDateAndTime = from.split(" ");
        LocalDate fromDate = LocalDate.parse(fromDateAndTime[0]);
        LocalTime fromTime;
        if (fromDateAndTime.length == 1 || fromDateAndTime[1].isBlank()) {
            fromTime = LocalTime.NOON;
        } else {
            fromTime = LocalTime.parse(fromDateAndTime[1]);
        }
        this.from = LocalDateTime.of(fromDate, fromTime);
        to = to.strip();
        String[] toDateAndTime = to.split(" ");
        LocalDate toDate = LocalDate.parse(toDateAndTime[0]);
        LocalTime toTime;
        if (toDateAndTime.length == 1 || toDateAndTime[1].isBlank()) {
            toTime = LocalTime.NOON;
        } else {
            toTime = LocalTime.parse(toDateAndTime[1]);
        }
        this.to = LocalDateTime.of(toDate, toTime);
    }

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getCategory() {
        return "E";
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Event e)) {
            return false;
        }
        return this.getCategory().equals(e.getCategory()) && this.getDescription().equals(e.getDescription())
                && this.getFrom().equals(e.getFrom()) && this.getTo().equals(e.getTo());
    }

    @Override
    public String toString() {
        return super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))
                + " to: "
                + to.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))
                + ")";
    }

    //@@author
}
