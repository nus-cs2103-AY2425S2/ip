package amara.task;

/**
 * Represents a to-do task, a simple task without any date or time constraints.
 * <p>
 * A {@code ToDo} task stores a description and a completion status. It can be
 * serialized into a saveable format and displayed in a user-friendly string format.
 * </p>
 */
public class ToDo extends Task {
    private static final String stringFormat = "%s,%d,%s";
    private static final int SORTING_ORDER = 0;

    public ToDo(String taskDescription) {
        super(taskDescription, false);
    }

    public ToDo(boolean isMarked, String taskDescription) {
        super(taskDescription, isMarked);
    }

    @Override
    public String getSavedFormat() {
        return String.format(ToDo.stringFormat, "T", this.isMarked ? 1 : 0,
                this.taskDescription);
    }

    @Override
    public int getSortingOrder() {
        return ToDo.SORTING_ORDER;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isMarked ? "X" : " ", this.taskDescription);
    }
}
