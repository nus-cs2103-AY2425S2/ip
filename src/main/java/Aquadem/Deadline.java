package Aquadem;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Deadline extends Task implements Serializable {

    protected String by;
    protected LocalDateTime dueDate;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}