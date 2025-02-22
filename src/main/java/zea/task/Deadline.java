package zea.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A task that has a deadline
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor of a Deadline
     * @param description - a description of the deadline
     * @param by - the datetime of the deadline
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFileFormattedString() {
        ArrayList<String> tags = this.getTags();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tags.size(); i++) {
            if (i != tags.size() - 1) {
                sb.append(tags.get(i)).append(',');
            } else {
                sb.append(tags.get(i));
            }
        }
        return "D|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + this.by.format(FORMATTER) + "|" + sb;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")" + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Deadline deadline = (Deadline) o;
        return deadline.description.equals(this.description) && deadline.by.equals(this.by);
    }
}
