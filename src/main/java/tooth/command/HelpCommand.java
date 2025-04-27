package tooth.command;

import tooth.stuff.Storage;
import tooth.stuff.TaskList;
import tooth.stuff.UI;

/**
 * Command that gives menu of options
 */
public class HelpCommand implements Command {
    public HelpCommand() {}

    /**
     * Lists all commands for user
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.say("""
                Commands:
                1. deadline [description] /by [date](creates a new deadline task)
                2. todo [description](creates a new todo task)
                3. event [description] /from [date] /to [date](creates a new event task)
                4. delete [index] (removes task based on index)
                5. mark [index] (marks task as completed)
                6. unmark [index] (marks task as incomplete)
                7. find [description] (tries to find a task with the matching description)
                8. list (lists all tasks)
                9. save (saves all tasks)
                10. load (loads tasks from save.txt
                """);
    }
}
