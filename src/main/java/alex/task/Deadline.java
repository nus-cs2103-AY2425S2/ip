package alex.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String timeStr;
    private LocalDate time;


    /**
     * Constructor for creating the deadline
     * @param content details of the task
     * @param time the time of deadline
     */
    public Deadline(String content, String time) {
        super(content);
        this.timeStr = time;
    }

    /**
     * Constructor for loading tasks from storage
     * @param content details of task
     * @param status the current status read from storage
     * @param time the time of deadline
     */
    public Deadline(String content, String status, String time) {
        super(content, status);
        this.timeStr = time;
    }

    /**
     * Checks if the time given is in valid date format that can be parsed
     * @return if the format is valid
     */
    private boolean isValidDateFormat() {
        try {
            time = LocalDate.parse(timeStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the format to be saved in the storage
     * @return data to be saved
     */
    @Override
    public String getSavedDataFormat() {
        return "D | " + super.getSavedDataFormat() + " | " + this.timeStr + "\n";
    }

    @Override
    public String toString() {
        if (isValidDateFormat()) {
            return "[D]" + super.toString() + " (by: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "[D]" + super.toString() + " (by: " + timeStr + ")";
    }
}
