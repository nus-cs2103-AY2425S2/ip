package tasker.task;

import java.time.Duration;

/**
 * A task that requires a fixed duration to complete.
 */
public class FixedDuration extends Task {
    private Duration duration;

    /**
     * Class constructor.
     *
     * @param description Description of this task.
     * @param duration    The amount of time it takes to complete the task.
     */
    public FixedDuration(String description, Duration duration) {
        super(description, TaskType.F);
        this.duration = duration;
    }

    /**
     * Class constructor specifying isDone.
     *
     * @param description Description of this task.
     * @param isDone      Whether this task is done.
     * @param duration    The amount of time it takes to complete the task.
     */
    public FixedDuration(String description, boolean isDone, Duration duration) {
        super(description, TaskType.F, isDone);
        this.duration = duration;
    }

    @Override
    public String toStorage() {
        return String.format("%s|%s", super.toStorage(), duration);
    }

    @Override
    public String toString() {
        return String.format("%s (needs: %sH %sM)", super.toString(), this.duration.toHours(),
                this.duration.toMinutes() % 60);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        FixedDuration other = (FixedDuration) obj;
        return this.duration == null ? other.duration == null : duration.equals(other.duration);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + duration.hashCode();
    }
}
