package kunkka.command;
import kunkka.tasklist.Tasklist;

/**
 * Represents a command to unmark a task as done.
 */
public class Unmark extends Command {
    private int taskNumber;

    /**
     * Constructor for Unmark command.
     * 
     * @param taskNumber The task number to unmark.
     */
    public Unmark(int taskNumber) {
        super("unmark");
        this.taskNumber = taskNumber;
    }

    /**
     * Gets the task number to unmark.
     * 
     * @return The task number to unmark.
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Executes the Unmark command.
     * 
     * @param tasklist The tasklist to unmark the task from.
     */
    public String execute(Tasklist tasklist) {
        try{
            assert(taskNumber >= 0 && taskNumber < tasklist.getSize()): "Task number is out of range";
            return tasklist.unmarkTaskAsDone(taskNumber);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        
    }
    
}
