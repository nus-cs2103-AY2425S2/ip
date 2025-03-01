package bebop.task;

/**
 * Todo task to store the description.
 */

public class Todo extends Task {

    /**
     * Todo Constructor
     *
     * @param description task that is meant to be done.
     * @param isDone status of whether task has been completed.
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the Todo Task.
     *
     * @return Todo task formatted.
     */
    @Override
    public String printTask() {
        return "[T]" + this.getStatus() + " " + this.description + "\n";
    };

    /**
     * Prints Successful adding into TaskList.
     *
     * @param size number of Task in the taskList.
     */
    @Override
    public String printSuccess(int size) {
        return "You got it buddy, get it done quick :D\n"
                + this.printTask() + "\n" + size + " tasks to be done";
    }

}
