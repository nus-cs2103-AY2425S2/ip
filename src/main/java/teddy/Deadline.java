package teddy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private final String time;

    public Deadline(String input, String time) {
        super(input);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        try {
            return "[D]" + super.toString() + " (by: "
                    + LocalDate.parse(this.time).format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        } catch (DateTimeParseException e) {
            return "[D]" + super.toString() + " (by: " + this.time + ")";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Deadline) {
            Deadline task = (Deadline) obj;
            return this.getTask().equals(task.getTask()) && this.getTime().equals(task.getTime());
        }
        return false;
    }
}
