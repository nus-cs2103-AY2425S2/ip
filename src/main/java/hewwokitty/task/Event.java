package hewwokitty.task;

/**
 * Represents a type of Task that is an event carried out over a time period
 */
public class Event extends Task {
    private String fromDate;
    private String toDate;

    /**
     * Returns an Event object
     * @param message A string describing what the Event is about
     * @param fromDate A string representing when the Event starts
     * @param toDate A string representing when the Event ends
     */
    public Event(String message, String fromDate, String toDate) {
        super(message);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Formats a string into the format meant to save to file
     * @return A String that has been formatted with the type Event, isDone, fromDate and toDate
     */
    @Override
    public String writeToFile() {
        return "E|" + super.writeToFile() + "|" + this.fromDate + "|" + this.toDate + "\n";
    }

    /**
     * Formats a string into the format that is displayed to the user
     * @return A String to display the Event information to the user
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.fromDate + " to: " + this.toDate + ")";
    }
}
