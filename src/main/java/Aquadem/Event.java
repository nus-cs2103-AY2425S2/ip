package Aquadem;

import java.io.Serializable;

public class Event extends Task implements Serializable {

    protected String t1;

    protected String t2;
    public Event(String description, String t1, String t2) {
        super(description);
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + t1 + "to:" + t2 +  ")";
    }
}
