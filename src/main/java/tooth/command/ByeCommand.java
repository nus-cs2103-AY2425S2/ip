package tooth.command;

import tooth.stuff.Storage;
import tooth.stuff.TaskList;
import tooth.stuff.UI;

/**
 * Command that closes app with a goodbye message
 */
public class ByeCommand implements Command {
    public ByeCommand() {}

    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.bye();
    }
}
