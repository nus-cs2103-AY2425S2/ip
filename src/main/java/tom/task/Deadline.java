package tom.task;

import java.time.LocalDate;

import tom.parser.Parser;

/**
 * Represents an deadline task with a end date.
 */
public class Deadline extends Task {
    protected LocalDate end;

    /**
     * Constructs a Deadline task with the specified description and end date.
     *
     * @param description The description of the task.
     * @param end The end date of the task.
     */
    public Deadline(String description, LocalDate end) {
        super(description);
        assert description != null : "Description should not be null";
        assert end != null : "End date should not be null";
        this.end = end;
    }

    /**
     * Returns a string representation of the task in file format.
     *
     * @return A string in the format "D | statusIcon | description".
     */
    @Override
    public String toFileFormatString() {
        return String.format(
            "D | %s | %s | %s",
            getStatusIcon(),
            getDescription(),
            Parser.dateToString(end)
        );
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string in the format "[D][statusIcon] description (by: endDate)".
     */
    @Override
    public String toString() {
        String endString = Parser.dateToString(end);
        return String.format("[D]%s (by: %s)", super.toString(), endString);
    }
}
