package kunkka.command;
import kunkka.tasklist.Tasklist;

/**
 * Represents a delete command.
 */
public class Delete extends Command {
    protected int index;
    
    /**
     * Constructor for Delete command.
     * 
     * @param index Index of task to be deleted.
     */
    public Delete(int index) {
        super("delete");
        this.index = index;
    }

    /**
     * Returns the index of task to be deleted.
     * 
     * @return Index of task to be deleted.
     */
    public int getIndex() {
        return index;
    }   

    /**
     * Executes the delete command.
     * 
     * @param tasklist Tasklist to execute the command on.
     */
    public String execute(Tasklist tasklist) {
        try{
            assert(index >= 0 && index < tasklist.getSize()) : "Invalid index";
            return tasklist.deleteTask(index);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        
    }    
}
