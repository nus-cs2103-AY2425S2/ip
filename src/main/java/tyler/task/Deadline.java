package tyler.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    //@@author VikramGoyal23-reused
    // Reused from https://nus-cs2103-ay2425s2.github.io/website/se-book-adapted/projectDuke/
    // with minor modifications

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        by = by.strip();
        String[] byDateAndTime = by.split(" ");
        LocalDate byDate = LocalDate.parse(byDateAndTime[0]);
        LocalTime byTime;
        if (byDateAndTime.length == 1 || byDateAndTime[1].isBlank()) {
            byTime = LocalTime.NOON;
        } else {
            byTime = LocalTime.parse(byDateAndTime[1]);
        }
        this.by = LocalDateTime.of(byDate, byTime);
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getCategory() {
        return "D";
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deadline d)) {
            return false;
        }
        return this.getCategory().equals(d.getCategory()) && this.getDescription().equals(d.getDescription())
                && this.getBy().equals(d.getBy());
    }

    @Override
    public String toString() {
        return super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))
                + ")";
    }

    //@@author
}
