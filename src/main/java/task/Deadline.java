package task;

import java.time.LocalDateTime;

import exception.SunpterException;
import parser.DateParser;

/**
Deadline type of task
 Has a due date
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String name, String deadline, int priority) throws SunpterException {
        super(name, priority);
        this.deadline = DateParser.parseDate(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + DateParser.formatDate(deadline) +")";
    }
    @Override
    public String toStorageString() {
        return "[D]" + " " + this.getStatus() + " " + getPriority() + " " +
                getDescription() + " /by " + DateParser.parseStorage(deadline);
    }
}
