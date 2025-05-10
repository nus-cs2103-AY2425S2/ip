package kunkka.command;
import kunkka.tasklist.Tasklist;

/**
 * Represents a command to mark a task as done.
 */
public class Mark extends Command {
    protected int index;
    
    /**
     * Constructor for Mark command.
     * 
     * @param index Index of the task to be marked as done.
     */
    public Mark(int index) {
        super("mark");
        this.index = index;
    }

    /**
     * Returns the index of the task to be marked as done.
     * 
     * @return Index of the task to be marked as done.
     */
    public int getIndex() {
        return index;
    }   

    /**
     * Marks the task at the specified index as done.
     * 
     * @param tasklist Tasklist object that contains the list of tasks.
     */
    public String execute(Tasklist tasklist) {
        try{
            return tasklist.markTaskAsDone(index);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
