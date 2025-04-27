package tooth.task;

import java.time.LocalDate;

/**
 * Deadline Task Object
 */
public class Deadline extends Task {
    private LocalDate deadline;
    private Deadline(String name, boolean isDone, LocalDate deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    public static Deadline of(String name, LocalDate deadline) {
        return new Deadline(name, false, deadline);
    }

    public static Deadline of(String name, boolean isDone, LocalDate deadline) {
        return new Deadline(name, isDone, deadline);
    }

    @Override
    public Deadline complete() {
        return new Deadline(this.name, true, this.deadline);
    }

    @Override
    public Deadline incomplete() {
        return new Deadline(this.name, false, this.deadline);
    }

    @Override
    public String serialize() {
        return "D|" + name + "|" + deadline + "|" + isDone;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (Due on: " + deadline + ")";
    }
}
