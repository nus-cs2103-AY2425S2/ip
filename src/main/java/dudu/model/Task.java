package dudu.model;

public class Task {
    /** Title of task **/
    protected String name;
    /** Status of task **/
    protected boolean isDone;

    /**
     * Creates Task object.
     *
     * @param name Title of task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    /**
     * Prints out task.
     *
     * @return Line with status and title of task.
     */
    @Override
    public String toString() {
        String status = this.isDone
                        ? "[X]"
                        : "[ ]";
        return status + " " + this.name + " ";
    }
}
