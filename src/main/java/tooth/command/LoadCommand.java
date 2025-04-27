package tooth.command;

import tooth.stuff.Storage;
import tooth.stuff.TaskList;
import tooth.stuff.UI;

/**
 * Command that loads task from save.txt to TaskList
 */
public class LoadCommand implements Command {

    public LoadCommand() {}

    /**
     * Execute task
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        storage.load(tasks);
        ui.say("Loaded tasks");
    }
}
