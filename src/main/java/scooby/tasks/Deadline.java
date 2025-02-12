package scooby.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    // Input format (with time)
    private static final DateTimeFormatter INPUTFORMATTER  = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    // Output format
    private static final DateTimeFormatter OUTPUTFORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    private LocalDateTime deadlineTime;

    /**
     * Constructs a new Deadline task.
     *
     * @param description the name of the deadline task
     * @param by the deadline date and time
     */
    public Deadline(String description, String by) {
        super(description);
        this.deadlineTime = parseDate(by); // Parse the date and time
    }

    /**
     * Parses the date and time string from input format "yyyy-MM-dd HHmm" to LocalDateTime.
     *
     * @param dateStr the date and time in the form of String, in the format: "yyyy-MM-dd HHmm"
     * @return the formatted LocalDateTime object
     */
    private LocalDateTime parseDate(String dateStr) {
        return LocalDateTime.parse(dateStr, INPUTFORMATTER); // Parse both date and time
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return the string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + deadlineTime.format(OUTPUTFORMATTER) + ")";
    }

    /**
     * Returns the raw representation of the String to be read from the txt file
     * @return returns the string to be added into the txt file
     */
    @Override
    public String toRawString() {
        return "[D]" + super.toString()
                + " (by: " + deadlineTime.format(INPUTFORMATTER) + ")";
    }

    /**
     * Checks if the given object is equal to this task instance.
     *
     * @param obj The object to compare with this task.
     * @return {@code true} if both the description and type are equal; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Deadline deadline) {
            return this.deadlineTime.equals(deadline.deadlineTime)
                    && this.description.equalsIgnoreCase(deadline.description);
        }
        return false;
    }

    /**
     * Updates the deadline task details.
     *
     * @param newDescription The new task description.
     * @param newDeadline The new deadline date.
     */
    public void updateDetails(String newDescription, String newDeadline) {
        this.description = newDescription.trim();
        this.deadlineTime = LocalDateTime.parse(newDeadline, INPUTFORMATTER);
    }

}
