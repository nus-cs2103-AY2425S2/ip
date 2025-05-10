package charlie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific due date.
 * Inherits from the Task class and adds functionality for storing and displaying a deadline.
 */
public class Deadline extends Task {
    private final LocalDate date;

    public Deadline(String task) {
        super(task.split("/by")[0]);
        date = LocalDate.parse(task.split("/by")[1].trim());
    }

    public Deadline(String task, String date, Boolean marked) {
        super(task, marked);
        this.date = LocalDate.parse(date);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a string that can be written to a file to save the Deadline task.
     * The format includes "D" for the task type, followed by the task description and deadline date.
     *
     * @return A string representing the Deadline task in a file-save format.
     */
    public String writeToFile() {
        try {
            return "D" + super.writeToFile() + "|" + date.toString() + "\n";
        } catch (Exception e) {
            System.out.println(date);
        }
        return "D" + super.writeToFile() + "|" + date.toString() + "\n";
    }
}
