package tooth.command;
import tooth.stuff.Storage;
import tooth.stuff.TaskList;
import tooth.stuff.UI;

/**
 * Command abstract class
 */
public interface Command {
    void execute(TaskList tasks, UI ui, Storage storage);
}
