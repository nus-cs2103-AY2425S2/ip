package model.task;

public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified name.
     *
     * @param name the name of the task
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructs a new Todo task with the specified name and marked status.
     *
     * @param name the name of the task
     * @param isMarked the marked status of the task
     */
    public Todo(String name, Boolean isMarked) {
        super(name, isMarked);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return a string representation of the Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a data string representation of the Todo task.
     *
     * @return a data string representation of the Todo task
     */
    @Override
    public String toDataString() {
        return "T|" + (isMarked ? "1" : "0") + "|" + name;
    }

}
