package boink.tasks;

/**
 * This class represents a task without deadline.
 */

public class ToDoTask extends Task {

    /**
     * Constructor for ToDoTask class.
     * @param name The name of task.
     */

    public ToDoTask(String name) {
        super(name);
    }

    /**
     * Creates output in format for saving task.
     * @return Task as string to write to file.
     */

    @Override
    public String saveTask() {
        return String.format(
                "T | %d | %s",
                super.getDoneIntValue(),
                this.getName()
        );
    }

    @Override
    public String toString() {
        return String.format(
                "[T] %s",
                super.toString()
        );
    }
}
