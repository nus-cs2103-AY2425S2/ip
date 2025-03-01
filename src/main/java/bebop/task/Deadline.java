package bebop.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;



/**
 * Deadline task to store the deadline date/time.
 */

public class Deadline extends Task {
    private final String start;
    private final LocalDateTime startDate;

    /**
     * Deadline Constructor.
     *
     * @param description task that is meant to be done.
     * @param isDone status of whether task has been completed.
     * @param start string representing start date.
     */
    public Deadline(String description, boolean isDone, String start) {
        super(description, isDone);
        this.start = start;
        String[] startTemp = start.split(" ");
        LocalTime t = LocalTime.parse(startTemp[1]);
        startDate = LocalDate.parse(startTemp[0]).atTime(t);
    }

    /**
     * Prints task.
     *
     * @return String of formatted Task.
     */
    @Override
    public String printTask() {
        return "[D]" + this.getStatus() + " " + this.description + " " + "(by: " + printDate(startDate) + ")" + "\n";
    };

    /**
     * Prints Successful adding into TaskList
     *
     * @param size number of Task in the taskList
     */
    @Override
    public String printSuccess(int size) {
        return "Deadlines, shag ah bro ;(.\n"
                + this.printTask() + "\n" + size + " tasks to be done";
    }

    public String getStart() {
        return this.start;
    }

}
