package tooth.command;

import tooth.stuff.Storage;
import tooth.stuff.TaskList;
import tooth.stuff.UI;

/**
 * Command that list all tasks in TaskList
 */
public class ListCommand implements Command {
    private int index = 0;

    public ListCommand() {}

    /**
     * Execute tasks
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.say("List: ");
        tasks.forEach((x) -> {
            ui.appendLn(index + " " + x.toString());
            index++;
        });
    }
}
