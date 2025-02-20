package amara.task;

/**
 * An abstract class representing a contract for various {@link Task} implementations
 * that {@link Amara} keeps track of in her {@code ArrayList<Task>}.
 * <p>
 * A {@code Task} object encapsulates a task's description and its completion statys.
 * Concrete subclasses implement specific types of tasks and provide their own behavior
 * for storage formatting.
 * </p>
 */
public abstract class Task {
    protected String taskDescription;
    protected boolean isMarked;

    /**
     * Constructs a {@code Task} with the given description and completion isMarked.
     *
     * @param taskDescription   The description of the task.
     * @param isMarked          The completion status of the task.
     */
    public Task(String taskDescription, boolean isMarked) {
        this.taskDescription = taskDescription;
        this.isMarked = isMarked;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public void markTask() {
        this.isMarked = true;
    }

    public void unmarkTask() {
        this.isMarked = false;
    }

    public boolean getisMarked() {
        return this.isMarked;
    }

    public abstract String getSavedFormat();

    public abstract int getSortingOrder();
}
