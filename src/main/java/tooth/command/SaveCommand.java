package tooth.command;

import tooth.stuff.Storage;
import tooth.stuff.TaskList;
import tooth.stuff.UI;

/**
 * Command that saves TaskList to save.txt
 */
public class SaveCommand implements Command {

    public SaveCommand() {}

    /**
     * Execute Task
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        storage.save(tasks);
        ui.say("Saved tasks");
    }
}
