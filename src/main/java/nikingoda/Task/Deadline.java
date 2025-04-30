package nikingoda.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;
    /**
     * Custom DateTimeFormatter for input and output
     */
    private final DateTimeFormatter inputForm = DateTimeFormatter.ofPattern("HHmm d/M/yyyy");
    private final DateTimeFormatter outputForm = DateTimeFormatter.ofPattern("h:mm a, MMM d yyyy");

    /**
     * Constructs a Deadline task with the given description and deadline.
     *
     * @param description Task description.
     * @param deadline    Deadline in the format HHmm d/M/yyyy.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, inputForm);
    }

    /**
     * Constructs a Deadline task with the given description and deadline (use to parse saving task from saving file).
     *
     * @param description Task description.
     * @param deadline    Deadline in the format HHmm d/M/yyyy.
     */
    public Deadline(String description, String deadline, Boolean isDone) {
        super(description, isDone);
        this.deadline = LocalDateTime.parse(deadline, inputForm);
    }

    /**
     * return deadline in input and output form
     */
    private String outputDeadline() {
        return this.deadline.format(outputForm);
    }

    private String inputDeadline() {
        return this.deadline.format(inputForm);
    }

    /**
     * method to update deadline
     *
     * @param newDeadline new deadline
     */
    public void updateDeadline(String newDeadline) {
        this.deadline = LocalDateTime.parse(newDeadline, inputForm);
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.getDescription()
                + " (by: " + this.outputDeadline() + ")";
    }

    @Override
    public String toFile() {
        int done = this.isDone ? 1 : 0;
        return "D|" + done + "|" + this.description + "|" + this.inputDeadline();
    }

}
