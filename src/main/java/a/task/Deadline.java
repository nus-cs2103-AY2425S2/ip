package a.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * task of type deadline
 */
public class Deadline extends Task {

    protected TaskType type = TaskType.DEADLINE;

    private LocalDate by;

    /**
     *
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * return the saved format
     * @return the format to be saved in storage
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
