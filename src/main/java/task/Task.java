package task;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Represents a task in the task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;
    protected Set<String> tags;

    /**
     * Creates a new task with the specified description and type.
     *
     * @param description The description of the task.
     * @param type The type of the task.
     */
    public Task(String description, TaskType type) {
        assert description != null : "Task description cannot be null";
        assert type != null : "Task type cannot be null";
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.tags = new HashSet<>();
    }

    public void addTag(String tag) {
        tags.add(tag);
    }
    public void removeTag(String tag) {
        tags.remove(tag);
    }
    public Set<String> getTags() {
        return tags;
    }
    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    @Override
    public String toString() {
        String tagString = tags.isEmpty() ? "" : " " + tags.stream().map(t -> "#" + t).collect(Collectors.joining(" "));
        return "[" + getStatusIcon() + "] " + description + tagString;
    }
}
