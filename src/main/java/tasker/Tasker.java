package tasker;

import static tasker.Parser.parseCommand;

import tasker.command.ByeCommand;
import tasker.command.Command;
import tasker.exception.TaskerException;
import tasker.task.TaskList;

/**
 * Main class for project.
 */
public class Tasker {
    /** List of the user's tasks */
    private static TaskList tasks;

    public static void main(String[] args) {
        Storage storage;
        try {
            storage = new Storage("./data/tasker.txt");
            tasks = new TaskList(storage);
        } catch (TaskerException e) {
            Ui.respond(e.getMessage());
            return;
        }

        Ui.respond("""
                Hello! I'm Tasker
                What can I do for you?""");

        while (true) {
            String cmd = Ui.input();
            Command parsedCmd = null;

            try {
                parsedCmd = parseCommand(cmd);
                Ui.respond(parsedCmd.execute(tasks, storage));
            } catch (TaskerException e) {
                Ui.respond(e.getMessage());
            }

            if (parsedCmd instanceof ByeCommand) {
                break;
            }
        }

        Ui.close();
    }
}
