package task;
import parser.Parser;

import java.time.LocalDateTime;

public class Events extends Task {
    protected  LocalDateTime from;
    protected LocalDateTime to;

    public Events(String des, LocalDateTime from, LocalDateTime to){
        super(des);
        this.from = from;
        this.to = to;
    }

    public void updateTo(LocalDateTime to){
        this.to = to;
    }

    public void updateFrom(LocalDateTime from){
        this.from = from;
    }

    @Override
    public Events clone() {
        if (this.getDone()){
            Events e = new Events(this.description, this.from, this.to);
            e.markAsDone();
            return e;
        } else {
            return new Events(this.description, this.from, this.to);
        }
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + Parser.fromatToString(from) + " to: "
                + Parser.fromatToString(to) + ")";
    }
}
