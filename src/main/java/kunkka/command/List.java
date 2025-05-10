package kunkka.command;
import kunkka.tasklist.Tasklist;

/**
 * Represents the command to list all tasks in the task list.
 */
public class List extends Command {

    public List() {
        super("list");
    }

    /**
     * Executes the command to list all tasks in the task list.
     *
     * @param taskslist Tasklist object that contains the list of tasks.
     */
    public String execute(Tasklist taskslist) {
        return taskslist.printTasks();
        
    }

}
