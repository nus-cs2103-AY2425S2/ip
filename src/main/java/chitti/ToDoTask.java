package chitti;

/**
 * Represents a to-do task.
 * This class extends the Task class and provides functionality specific to to-do tasks,
 * including a string representation for display and a format for saving to a file.
 */
public class ToDoTask extends Task{
    /**
     * Constructs a new ToDoTask with the specified name.
     * The task is initially marked as not done.
     *
     * @param name The name of the to-do task.
     */
    public ToDoTask(String name){
        super(name);
    }
    /**
     * Returns a string representation of the to-do task.
     * The string format includes the task's type (T), completion status, and name.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
    /**
     * Returns the file format representation of the to-do task.
     * This format is suitable for saving the task to a file.
     *
     * @return A string representing the to-do task in a format suitable for saving to a file.
     */
    @Override
    public String toFileFormat(){
        return "T|" + super.toFileFormat();
    }
}
