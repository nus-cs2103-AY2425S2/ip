package nikingoda.Task;

import nikingoda.NikingodaException.NikingodaException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime begin;
    protected LocalDateTime end;

    /**
     * Custom DateTimeFormatter for input and output
     */
    private final DateTimeFormatter inputForm = DateTimeFormatter.ofPattern("HHmm d/M/yyyy");
    private final DateTimeFormatter outputForm = DateTimeFormatter.ofPattern("h:mm a, MMM d yyyy");

    /**
     * constructor for event (use to create new file)
     *
     * @param description description
     * @param begin       begin_time
     * @param end         end_time
     */
    public Event(String description, String begin, String end) throws NikingodaException {
        super(description);
        LocalDateTime tmpBegin = LocalDateTime.parse(begin, inputForm);
        LocalDateTime tmpEnd = LocalDateTime.parse(end, inputForm);
        if (tmpEnd.isBefore(tmpBegin)) {
            throw new NikingodaException("End time must be after begin time");
        }
        this.begin = tmpBegin;
        this.end = tmpEnd;
    }

    /**
     * constructor for event (using to parse saving task from saving file)
     *
     * @param description description
     * @param begin       begin_time
     * @param end         end_time
     * @param done        status whether it is done
     */
    public Event(String description, String begin, String end, Boolean done) {
        super(description, done);
        this.begin = LocalDateTime.parse(begin, inputForm);
        this.end = LocalDateTime.parse(end, inputForm);
    }

    /**
     * return begin date in input and output form
     */
    private String inputBegin() {
        return this.begin.format(inputForm);
    }

    private String outputBegin() {
        return this.begin.format(outputForm);
    }

    /**
     * return end date in input and output form
     */
    private String intputEnd() {
        return this.end.format(inputForm);
    }

    private String outputEnd() {
        return this.end.format(outputForm);
    }

    /**
     * function to update begin_time
     *
     * @param newBegin new begin_time
     */
    public void updateBegin(String newBegin) throws NikingodaException {
        LocalDateTime tmpBegin = LocalDateTime.parse(newBegin, inputForm);
        if (this.end.isBefore(tmpBegin)) {
            throw new NikingodaException("End time must be after begin time");
        }
        this.begin = tmpBegin;
    }

    /**
     * function to update end_time
     *
     * @param newEnd new end_time
     */
    public void updateEnd(String newEnd) throws NikingodaException {
        LocalDateTime tmpEnd = LocalDateTime.parse(newEnd, inputForm);
        if (this.begin.isAfter(tmpEnd)) {
            throw new NikingodaException("End time must be after begin time");
        }
        this.end = tmpEnd;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.getDescription()
                + " (from: " + this.outputBegin() + " to: " + this.outputEnd() + ")";
    }

    @Override
    public String toFile() {
        int done = this.isDone ? 1 : 0;
        return "E|" + done + "|" + this.description + "|" + this.inputBegin() + "|" + this.intputEnd();
    }
}
