package Aquadem;

import java.io.Serializable;
/**
 * A subclass of the Task class to represent deadlines
 */
public class Todo extends Task implements Serializable {

    protected String by;

    /**
     * Default constructor which takes in a description
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrding the toString method of the Task class
     * @return
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}