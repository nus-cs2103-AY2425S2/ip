package a.task;

/**
 * Task of type fixed duration
 */
public class FixedDurationTask extends Task {

    private int duration;

    public FixedDurationTask(String description, int duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String toSaveFormat() {
        return "F | " + (isDone ? "1" : "0") + " | " + description + " | " + duration;
    }

    @Override
    public String toString() {
        return "[F]" + super.toString() + " (needs " + duration + " hours)";
    }
}