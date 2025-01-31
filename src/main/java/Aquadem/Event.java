package Aquadem;

import java.io.Serializable;

/**
 * A subclass of the Task class used to represent Events
 */
public class Event extends Task implements Serializable {

    protected String t1;

    protected String t2;

    /**
     * A constructor for the event classs
     * @param description description of task
     * @param t1 date from
     * @param t2 date to
     */

    public Event(String description, String t1, String t2) {
        super(description);
        this.t1 = t1;
        this.t2 = t2;
    }

    /**
     * Overrides the Task to string method
     * @return a String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + t1 + "to:" + t2 +  ")";
    }
}
