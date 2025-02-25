package mars.command;

import mars.storage.Storage;
import mars.task.Task;
import mars.task.TaskList;
import mars.ui.UI;


public class listCommand extends Command{
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";

    public void execute(TaskList tasklist, UI ui, Storage storage){
        StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
        for (Task task : tasklist.getTasks()) {
            message.append(task.toString()).append("\n");
        }
        ui.setResponse(message.toString());
    }
}
