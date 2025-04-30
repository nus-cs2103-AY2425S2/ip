package nikingoda.Parser;

import nikingoda.Command.Command;
import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Task.Deadline;
import nikingoda.Task.Event;
import nikingoda.Task.Task;
import nikingoda.Task.Todo;

public abstract class Parser {
    /**
     * parse task from each line of saved txt file
     *
     * @param line line that contain information about task
     * @return Task
     */
    public static Task parseTask(String line) {
        String[] lineSplitted = line.split("\\|");
        boolean isDone = lineSplitted[1].equals("1");
        if (lineSplitted[0].equals("T")) {
            return new Todo(lineSplitted[2], isDone);
        } else if (lineSplitted[0].equals("D")) {
            return new Deadline(lineSplitted[2], lineSplitted[3], isDone);
        } else {
            return new Event(lineSplitted[2], lineSplitted[3], lineSplitted[4], isDone);
        }
    }

    /**
     * parse Command from input command of user
     *
     * @param command input command of user
     * @return Command being parsed
     * @throws NikingodaException handle whether if syntax invalid
     */
    public static Command parse(String command) throws NikingodaException {
        return Command.findCommand(command);
    }
}
