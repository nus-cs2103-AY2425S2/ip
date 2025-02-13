package Aquadem;

import java.io.Serializable;

/**
 * A subclass of the Task class to represent doafters.
 */
public class Doafter extends Task implements Serializable {
    protected String after;


    /**
     * Constructs a Doafter.
     * @param description
     * @param after
     */
    public Doafter(String description, String after) {
        super(description);
        this.after = after;
    }

    /**
     * Overrides the Task to string method
     * @return a String
     */
    @Override
    public String toString() {
        return "[A]" + super.toString() + " (after:" + after + ")";
    }
}
