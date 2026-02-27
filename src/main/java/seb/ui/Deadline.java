package seb.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String deadLine;

    public Deadline(String description, String deadLine, boolean isDone) {
        super(description, isDone);
        this.deadLine = deadLine;
    }

    /**
     * Converts Deadline object to String to be stored in data file
     *
     * @return String of event details
     */
    @Override
    public String getDate() {
        return this.deadLine;
    }

    /**
     * Update description or deadline of deadline task
     *
     * @param detail the attribute of deadline to be edited
     * @param value the new attribute
     * @throws SebException for invalid input
     */
    @Override
    public void update(String detail, String value) throws SebException {
        if (detail.contains("desc")) {
            this.description = value;
        } else if (detail.contains("deadline")) {
            this.deadLine = Parser.checkDateValidity(Parser.parseDateTime(value));
        } else {
            throw new SebException("Invalid format!");
        }
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + this.deadLine;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadLine + ")";
    }
}
