package woof.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import woof.exception.IllegalDateException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Create an instance of a deadline task with parsing of local date.
     *
     * @param string Description of the task.
     * @param deadline Date on which the task is due in string format.
     * @throws IllegalDateException Date is not given in the correct format of "yyyy-MM-dd".
     */
    public Deadline(String string, String deadline) throws IllegalDateException {
        super(string);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new IllegalDateException("");
        }
    }

    /**
     * Returns a description of the task to be displayed on CLI.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns the description of the task to be recorded locally.
     *
     * @return The description of the task.
     */
    @Override
    public String print() {
        return String.format("D | %s | %s", super.print(),
                deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
