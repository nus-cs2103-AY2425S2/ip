package zoe.task;

/**
 * Represents a Todo task.
 * A Todo task is a basic task type with a name and no additional date-related constraints.
 * <p>
 * Extends the {@link Task} class, utilizing its core functionality such as task status management
 * and description-related retrieval, while customizing its type icon and description format.
 */
public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }

    @Override
    public String getDescription() {
        return taskName;
    }
}
