package lebum.task;

/**
 * Todo class
 */
public class ToDo extends Task {
    private String title;
    private Boolean isDone;

    /**
     * Constructor of todo.
     * @param title to call the parent constructor
     */
    public ToDo(String title) {
        super(title);
    }

    /**
     * Method to get string representation of date
     * @return the new message with type [T]
     */
    @Override
    public String print() {
        return "[T]" + " " + this.getStatus() + " " + this.getTitle();
    }
}
