package task;
import parser.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDateTime by;

    public Deadlines(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public String fromatToString(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return dateTime.format(formatter);

    }

    @Override
    public Deadlines clone() {
        if (this.getDone()){
            Deadlines d =  new Deadlines(this.description, this.by);
            d.markAsDone();
            return d;
        } else {
            return new Deadlines(this.description, this.by);
        }

    }

    public void updateBy(LocalDateTime by){
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.fromatToString(by) + ")";
    }
}
