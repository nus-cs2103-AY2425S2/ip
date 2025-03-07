package interfaces;

/**
 * Represent a Task that can be stored by the user
 * Tasks implementing this interface will define specific behaviours
 * of their respective task
 */
public interface ITask {
    /**
     * Marks the task to be completed
     */
    public void markDone();

    /**
     * Marks the task as incomplete
     */
    public void markUndone();

    /**
     * Get the String representation of Task
     *
     * @return String representation of Task
     */
    public String toString();

    /**
     * Converts the task to a String format to be saved in storage
     *
     * @return String representation of task to be saved in storage
     */
    public String toFileFormat();

}
