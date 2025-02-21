package ekko.task;
import java.time.LocalDateTime;

/**
 * A deadline.
 */
public class Deadline extends Todo {
    private LocalDateTime dueDate;

    /**
     * Create a deadline.
     * @param description task description
     * @param dueDate date and time when the task is due
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * String representation of the deadline.
     * @return tag, done status and description
     */
    @Override
    public String toString() {
        return String.format("[D] %s %s (by: %s)", super.printDone(), super.printDes(), this.dueDate.toString());
    }

    @Override
    public String toStore() {
        return "DEADLINE//" + super.storeDone() + "//" + super.printDes() + "//"
                + this.dueDate.toString();
    }
}
