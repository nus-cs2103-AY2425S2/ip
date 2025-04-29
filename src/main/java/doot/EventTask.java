package doot;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Used for tasks of the Event type, extending from task
 */
public class EventTask extends Task {
    protected String start;
    protected String end;
    protected LocalDate datestart;
    protected LocalDate dateend;

    /**
     * Creates a EventTask object which has the attributes of the given parameters
     * @param description of the task
     * @param start start of the event, saved as LocalDate if possible, otherwise saved as a String
     * @param end end of the event, saved as LocalDate if possible, otherwise saved as a String
     */
    public EventTask(String description, String start, String end) {
        super(description);
        this.type = Type.E;
        this.start = start;
        this.end = end;



        for (String format : FormatConstants.DATE_FORMATS) {
            try {
                this.datestart = LocalDate.from(LocalDate.parse(start,
                        DateTimeFormatter.ofPattern(format)).atStartOfDay());
            } catch (DateTimeParseException ignored) {
            }
        }
        for (String format : FormatConstants.DATE_FORMATS) {
            try {
                this.dateend = LocalDate.from(LocalDate.parse(end, DateTimeFormatter.ofPattern(format)).atStartOfDay());
            } catch (DateTimeParseException ignored) {
            }
        }
    }

    /**
     * Returns the details of this task, which includes type, status, description, start and end time, as well
     * as the tag
     * @return the details of the task to be printed
     */
    @Override
    public String getDetails() {
        return this.getType() + this.getStatusIcon() + " " + this.getDescription()
                + " (from: " + getStart() + " to: " + getEnd() + ")" +
                (this.getTag() != null ? " #" + getTag():"");
    }

    /**
     * gets the start time of the event in the string format
     * @return datetime of the beginning
     */
    public String getStart() {
        return !(datestart == null) ? datestart.format(DateTimeFormatter.ofPattern("dd MMM, yyyy")) : start;
    }

    /**
     * gets the end time of the event in the string format
     * @return datetime of the end
     */
    public String getEnd() {
        return !(dateend == null) ? dateend.format(DateTimeFormatter.ofPattern("dd MMM, yyyy")) : end;
    }

    /**
     * stitches together the command needed to recreate the task
     * @return the string for recreation
     */
    @Override
    public String creationString() {
        StringBuilder list = new StringBuilder();
        if (this.isDone) {
            list.append("d ");
        }
        list.append("event ").append(this.getDescription()).append(" /from ").append(start).append(" /to ").append(end);
        if (getTag() != null) {
            list.append("/tag ").append(getTag());
        }

        return list.toString();
    }
}
