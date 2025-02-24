package chitti;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents a deadline-based task.
 * This subclass of Task stores a deadline and provides methods to display
 * the task in a user-friendly format and in a file-friendly format.
 */
public class DeadlineTask extends Task{
    private LocalDate deadline;
    /**
     * Constructs a DeadlineTask with a specified name and deadline.
     * The deadline is parsed from the provided string and stored as a LocalDate.
     *
     * @param name The name of the task.
     * @param deadline The deadline of the task in string format (e.g., "2025-02-24").
     */
    public DeadlineTask(String name, String deadline){
        super(name);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Returns string representation of deadline task
     * @return String to be printed
     */
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the file format for a DeadlineTest
     * @return String representation to be saved in a file
     */
    @Override
    public String toFileFormat(){
        return "D|" + super.toFileFormat() + "|" + this.deadline;
    }
}
