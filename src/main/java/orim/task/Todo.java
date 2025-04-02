package orim.task;

/**
 * Represent a subclass of Task which is a task without deadline.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String isDone, String description) {
        super(description, isDone.equals("1"));
    }

    /**
     * Transforms the state and detail of the tasks to a string.
     * Will be store in the data file and be reloaded when user open Orim again.
     *
     * @return log to represents the state and detail of the tasks
     */
    public String generateLog() {
        return "T," + (isDone ? 1 : 0) + "," + super.description;
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
