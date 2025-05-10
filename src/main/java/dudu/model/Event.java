package dudu.model;

import dudu.utils.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    /** Start of event **/
    private final LocalDateTime from;
    /** End of event **/
    private final LocalDateTime to;

    /**
     * Creates Event object.
     *
     * @param name Title of task.
     * @param from Start of event.
     * @param to End of event.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Returns true if the target date falls within the duration of this event task.
     *
     * @param targetDate Date of day to be viewed.
     * @return True if target date is same day as day to be viewed.
     */
    public boolean isWithinDuration(LocalDateTime targetDate) {
        LocalDate fromLocalDate = from.toLocalDate();
        LocalDate toLocalDate = to.toLocalDate();
        LocalDate targetLocalDate = targetDate.toLocalDate();

        boolean sameStartDate = targetLocalDate.equals(fromLocalDate);
        boolean afterStartDate = targetLocalDate.isAfter(fromLocalDate);
        boolean sameEndDate = targetLocalDate.equals(toLocalDate);
        boolean beforeEndDate = targetLocalDate.isBefore(toLocalDate);

        return (sameStartDate || afterStartDate) && (sameEndDate || beforeEndDate);
    }

    /**
     * Returns Event task in String format for data.txt.
     *
     * @param taskType Task type to be displayed.
     * @param isDone Completion of task to be displayed.
     * @return String format of task for data.txt.
     */
    public String toStringFileFormat(String taskType, String isDone) {
        return String.format("%s | %s | %s | %s | %s", taskType, isDone, this.getName(),
                this.getFrom(), this.getTo());
    }

    /**
     * Returns event task in String format.
     *
     * @return Line with status, title and duration of task.
     */
    @Override
    public String toString() {
        DateTimeFormatter fromFormatter = DateTimeParser.checkFormat(from);
        DateTimeFormatter toFormatter = DateTimeParser.checkFormat(to);
        return "[E]" + super.toString() + "(from: " +
                from.format(fromFormatter) + " to: " + to.format(toFormatter) + ")";

    }
}
