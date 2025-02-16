package Acheron.Tasks;

import java.time.LocalDate;

import Acheron.Exceptions.TaskExceptions;
import Acheron.Utility.DateFormatter;

/**
 * Represents a events task
 */
public class Events extends Task {

    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructor for the event class
     * @param name The name of the tasks
     * @param isDone Whether the tasks is done or not. Required when generating tasks from the saved file
     * @param startDate When the task starts
     * @param endDate WHen the task ends
     * @throws TaskExceptions Throws an exception if a wrong input is supplied
     */
    public Events(String name, boolean isDone, String startDate, String endDate) throws TaskExceptions {
        super(name, isDone);
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    /**
     * Overrides the to string method with a custom version
     * @return A string format of what the task should produce
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + DateFormatter.formatDate(startDate) + " to: "
                + DateFormatter.formatDate(endDate) + ")";
    }

    /**
     * Generates the string content of the task for saving
     * @param isLast Indicates if the task is the last task in the task list object. Needed so the
     *             file writer does not add an unecessary new line which can cause file corruption
     * @return The content of the task
     */
    @Override
    public String saveTask(boolean isLast) {
        return "E"
                + super.saveTask(isLast) + "|"
                + startDate.toString() + "|" + endDate.toString()
                + (isLast ? "" : "\n");
    }
}
