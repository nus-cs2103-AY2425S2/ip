package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.ElmachoException;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by, boolean isDone) throws ElmachoException {
        super(description, isDone);
        assert by != null : "Date should not be null";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            throw new ElmachoException("HELLOOO! Wrong date-time format! Use: yyyy-MM-dd HHmm");
        }
    }

    public String getInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (isDone) {
            return "D " + 1 + "/" + description + "/By" + by.format(formatter);
        }
        return "D " + 0 + "/" + description + "/By" + by.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return "[D]" + super.toString() + " (By: " + by.format(formatter) + ")";
    }
}
