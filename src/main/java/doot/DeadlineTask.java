package doot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Class DeadlineTask is extended from the abstract Task class and represents deadline tasks
 */
public class DeadlineTask extends Task {
    protected String deadline;
    protected LocalDate datedeadline;

    /**
     * Constructs a DeadlineTask
     * @param description of the task
     * @param deadline of said task
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.type = Type.D;
        this.deadline = deadline;
        for (String format : FormatConstants.DATE_FORMATS) {
            try {
                this.datedeadline = LocalDate.from(LocalDate.parse(deadline,
                        DateTimeFormatter.ofPattern(format)).atStartOfDay());
            } catch (DateTimeParseException ignored) {
            }
        }
    }

    /**
     *deadline tasks include information on the actual deadline. This method converts the localtime to a string format
     *if there isn't a localtime, it just returns the string instead
     * @return the datetime of the deadline
     */
    public String getDeadline() {
        return !(datedeadline == null) ? datedeadline.format(DateTimeFormatter.ofPattern("dd MMM, yyyy")) : deadline;
    }

    /**
     * Returns the details as a string, in the format the ui will print
     * @return A string representing all the information needed for printing
     */
    @Override
    public String getDetails() {
        return this.getType() + this.getStatusIcon() + " " + this.getDescription() + " (by: " + getDeadline() + ")" +
                (this.getTag() != null ? " #" + getTag() : "");
    }

    /**
     * returns the input needed to create an identical task
     * @return String needed to create the identical task
     */
    @Override
    public String creationString() {
        StringBuilder list = new StringBuilder();
        if (this.isDone) {
            list.append("d ");
        }
        list.append("deadline ").append(this.getDescription()).append(" /by ").append(deadline);
        if (this.tag != null) {
            list.append(" /tag ").append(this.getTag());
        }
        return list.toString();
    }
}
