package charlie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate startTime;
    private final LocalDate endTime;

    public Event(String task) {
        super(task.split("/from")[0]);
        startTime = LocalDate.parse(task.split("/from")[1].split("/to")[0].trim());
        endTime = LocalDate.parse(task.split("/to")[1].trim());
    }

    public Event(String task, String startTime, String endTime, Boolean marked) {
        super(task, marked);
        this.startTime = LocalDate.parse(startTime);
        this.endTime = LocalDate.parse(endTime);
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a string that can be written to a file to save the Deadline task.
     * The format includes "E" for the task type, followed by the task description and event start date and end date.
     *
     * @return A string representing the Deadline task in a file-save format.
     */
    public String writeToFile() {
        return "E" + super.writeToFile() + "|" + startTime + "|" + endTime + "\n";
    }
}
