package lebum.task;

/**
 * Event class
 */
public class Event extends Task {
    private String title;
    private Boolean isDone;
    /**
     * 2 new variables specifying start and end date
     */
    private String startDate;
    private String endDate;

    /**
     * Constructor for event class.
     * @param title of event
     * @param startDate of event
     * @param endDate of event
     */
    public Event(String title, String startDate, String endDate) {
        super(title);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Method to get the period of event.
     * @return the string that contains start and end date
     */
    public String getPeriod() {
        return " (from: " + startDate + " to: " + endDate + ")";
    }

    /**
     * Method to get the string representation of event.
     * @return the string that will be printed if list is called
     */
    @Override
    public String print() {
        return "[E]" + " " + this.getStatus() + " " + this.getTitle() + this.getPeriod();
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

}
