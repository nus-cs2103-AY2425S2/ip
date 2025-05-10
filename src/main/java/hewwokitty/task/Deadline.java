package hewwokitty.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a type of Task that needs to be completed before a certain date
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Returns a Deadline object
     * @param description A string describing what the Deadline is for
     * @param dateEntered A string in the format yyyy-MM-dd for when the task should be completed
     */
    public Deadline(String description, String dateEntered) {
        super(description);
        try {
            this.deadline = LocalDate.parse(dateEntered);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("");
        }
    }

    /**
     * Formats a string into the format meant to save to file
     * @return A String that has been formatted with the type Deadline, isDone, and the deadline description and when it is due
     */
    @Override
    public String writeToFile() {
        return "D|" + super.writeToFile() + "|" + this.deadline + "\n";
    }

    /**
     * Formats a string into the format that is displayed to the user
     * @return A String to display the Deadline information to the user
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.printDeadline() + ")";
    }

    private String printDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
