package tooth.command;

import tooth.stuff.Storage;
import tooth.stuff.TaskList;
import tooth.stuff.UI;
import tooth.task.Task;

/**
 * Command that list all task with match strings
 */
public class FindCommand implements Command {

    private String s;

    public FindCommand(String s) {
        this.s = s;
    }

    /**
     * Execute task
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.say("Here is what I found:");
        tasks.forEach((x) -> {
            if (canFind(x)) {
                ui.appendLn(x.toString());
            }
        });
    }

    private boolean canFind(Task task) {
        String temp = task.toString();
        return temp.contains(s);
    }
}
