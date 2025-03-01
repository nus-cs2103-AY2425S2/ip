package bebop.task;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Event task to store the event date/time.
 */

public class Event extends Task {
    private final String start;
    private final String end;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    /**
     * Event Constructor.
     *
     * @param description task that is meant to be done.
     * @param isDone status of whether task has been completed.
     * @param start string representing start date.
     * @param end string representing end date.
     */
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
        String[] startTemp = this.start.split(" ");
        LocalTime t1 = LocalTime.parse(startTemp[1]);
        this.startDate = LocalDate.parse(startTemp[0]).atTime(t1);
        String[] endTemp = this.end.split(" ");
        LocalTime t2 = LocalTime.parse(endTemp[1]);
        this.endDate = LocalDate.parse(endTemp[0]).atTime(t2);
    }

    /**
     * Prints task.
     *
     * @return String of formatted Task.
     */
    @Override
    public String printTask() {
        return "[E]" + this.getStatus() + " " + this.description + " "
                + "(from: " + super.printDate(startDate) + " to: " + printDate(endDate) + ")" + "\n";
    };

    /**
     * Prints Successful adding into TaskList
     *
     * @param size number of Task in the taskList
     */
    @Override
    public String printSuccess(int size) {
        return "Yippee, hope it's a fun event :D\n"
                + this.printTask() + "\n" + size + " tasks to be done";
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }



}
