package tasker;

import static tasker.Parser.parseCommand;

import tasker.command.Command;
import tasker.exception.TaskerException;
import tasker.task.TaskList;

/**
 * Main class for project.
 */
public class Tasker {
    /** Tasks of the current session */
    private TaskList tasks;
    /** Storage of tasks */
    private Storage storage;

    /**
     * Class constructor.
     */
    public Tasker() throws TaskerException {
        try {
            storage = new Storage("./data/tasker.txt");
        } catch (TaskerException e) {
            System.err.println(e.getMessage() + " Exiting...");
            System.exit(1);
        }

        try {
            tasks = new TaskList(storage);
        } catch (TaskerException e) {
            tasks = new TaskList();
            throw new TaskerException(e.getMessage() + " Creating new empty list.");
        }
    }

    /**
     * Processes an input from a user.
     *
     * @param input The user's input.
     */
    public String respond(String input) throws TaskerException {
        Command parsedCmd = parseCommand(input);
        return parsedCmd.execute(tasks, storage);
    }
}
