package aquadem;

import java.io.Serializable;

/**
 * A subclass of the Task class to represent deadlines.
 */
public class Deadline extends Task implements Serializable {

    protected String by;
    
    /**
     * Constructs a Deadline type Task.
     * @param description of type <Code>String</Code> descibing the task.
     * @param by of type String to describe the duedate.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides the Task to string method
     * @return a String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}