package mars.command;

import mars.task.Task;

import mars.task.TaskList;
import mars.ui.UI;
import mars.storage.Storage;


public class deleteCommand extends Command{
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private int itemNumber;

    public deleteCommand(int itemNumber){
        super();
        this.itemNumber = itemNumber;
    }

    public void execute(TaskList tasklist, UI ui, Storage storage){
        Task task = tasklist.get(itemNumber - 1);
        assert task != null : "Task cannot be null.";

        String firstMessage = "Noted. I've removed this task: " + task.toString();
        tasklist.delete(itemNumber -1);
        String secondMessage = "\nNow you have " + tasklist.size() + " tasks in the list.\n";
        ui.setResponse(firstMessage + secondMessage);
    }

}
