package mars.command;

import mars.task.Task;

import mars.task.TaskList;
import mars.ui.UI;
import mars.storage.Storage;


public class unmarkCommand extends Command{
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private int itemNumber;

    public unmarkCommand(int itemNumber){
       super();
       this.itemNumber = itemNumber;
   }

    public void execute(TaskList tasklist, UI ui, Storage storage){
        Task task = tasklist.get(itemNumber - 1);
        assert task != null : "Task cannot be null.";
        if(task.getStatusIcon().equals("X")){
            tasklist.unmark(itemNumber -1);
            String message = "OK, I've marked this task as not done yet: " + task + "\n";
            ui.setResponse(message);
        } else{
            ui.setResponse("This task was already unmarked.\n");
        }
    }

}
