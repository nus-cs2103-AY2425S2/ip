package lebum.task;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Deadline class that inherits from Task
 */
public class Deadline extends Task {

    private String title;
    private Boolean isDone;
    private Date deadlineDate;
    //map to format date if applicable
    private static final Map<Integer, String> dateMap = Map.of(
            1, "st",
            2, "nd",
            3, "rd",
            21, "st",
            22, "nd",
            23, "rd",
            31, "st"
    );

    /**
     * new variable for end date and time
     */
    private String endDate;

    /**
     * Constructor
     * @param title name of todo task
     * @param endDate end date and time of todo
     */
    public Deadline(String title, String endDate) {
        super(title);
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("d/MM/yyyy HHmm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/MM/yyyy");

        try {
            try {
                // Try to parse date with time first if user input time
                Date deadlineTime = dateTimeFormat.parse(endDate);
                deadlineDate = deadlineTime;

                // Get the day of month
                int day = deadlineTime.getDate();
                // Get the ordinal indicator
                String ordinal = dateMap.getOrDefault(day, "th");

                // Custom format with date indicator
                SimpleDateFormat monthYearFormat = new SimpleDateFormat("MMMM yyyy, h:mm a");
                this.endDate = day + ordinal + " of " + monthYearFormat.format(deadlineTime);

            } catch (ParseException e) {
                // If time parsing fails, try date only
                Date deadline = dateFormat.parse(endDate);
                deadlineDate = deadline;

                // Get the day of month
                int day = deadline.getDate();
                // Get the date indicator
                String ordinal = dateMap.getOrDefault(day, "th");

                SimpleDateFormat monthYearFormat = new SimpleDateFormat("MMMM yyyy");
                this.endDate = day + ordinal + " of " + monthYearFormat.format(deadline);
            }
        } catch (ParseException e) {
            this.endDate = endDate;
        }
    }

    /**
     * Get the end date of the deadline
     * @return end date time
     */
    public String getEndDate() {
        return "(by:" + endDate + ")";
    }

    public String getEndDateFile() {
        return endDate;
    }
    @Override
    public String print() {
        return "[D]" + " " + this.getStatus() + " " + this.getTitle() + this.getEndDate();
    }


}
