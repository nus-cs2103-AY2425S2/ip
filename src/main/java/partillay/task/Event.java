package partillay.task;

import java.time.LocalDateTime;

import partillay.parser.DateTimeFormatParser;

/**
 * Represents an event with a description, start time, and end time.
 * This class extends the Task class and provides functionality specific to an event.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    protected String fromString;
    protected String toString;

    /**
     * Constructs a new Event with the given description, start time, and end time.
     * The event is initially marked as not done.
     *
     * @param description the description of the event
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = DateTimeFormatParser.parseDateTime(from);
        this.to = DateTimeFormatParser.parseDateTime(to);
        this.fromString = from;
        this.toString = to;
        if (this.from.getHour() == 23 && this.from.getMinute() == 59) {
            this.from = this.from.withHour(0).withMinute(0);
        }
    }

    /**
     * Constructs a new Event with the given description, start time, end time, and status.
     * The status is represented by a binary number, where "1" means done and "0" means not done.
     *
     * @param description the description of the event
     * @param from the start time of the event
     * @param to the end time of the event
     * @param statusBinaryNumber the status of the event in binary format ("1" for done, "0" for not done)
     */
    public Event(String description, String from,
                 String to, String statusBinaryNumber) {
        super(description, statusBinaryNumber);
        this.from = DateTimeFormatParser.parseDateTime(from);
        this.to = DateTimeFormatParser.parseDateTime(to);
        this.fromString = from;
        this.toString = to;
        if (this.from.getHour() == 23 && this.from.getMinute() == 59) {
            this.from = this.from.withHour(0).withMinute(0);
        }
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: "
                + DateTimeFormatParser.getFormattedDateString(from)
                + " to: "
                + DateTimeFormatParser.getFormattedDateString(to)
                + ")";
    }

    /**
     * Returns a string representation of the Event in a format suitable for saving to a file.
     * The format is: "E | statusBinaryNumber | description | from | to".
     *
     * @return a string representation of the Event in a format suitable for file storage
     */
    public String getTxtFormat() {
        return "E"
                + " | "
                + getStatusBinaryNumber()
                + " | "
                + description
                + " | "
                + from
                + " | "
                + to;
    }

    /**
     * Checks if two Event objects are equal based on their description, start time, and end time.
     *
     * @param other the object to compare with
     * @return true if the Event objects have the same description, start time, and end time; false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Event) {
            return description.equals(((Event) other).description)
                    && from.equals(((Event) other).from)
                    && to.equals(((Event) other).to);
        }
        return false;
    }
}
