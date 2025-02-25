package alex.command;

import alex.Parser;
import alex.Storage;
import alex.Ui;
import alex.exceptions.MissingContentException;
import alex.task.TaskList;

public class DeleteCommand extends Command {
    private int index;
    private int[] range;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public DeleteCommand(int[] range) {
        this.range = range;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (range != null) {
            tasks.delete(range, ui, storage);
            return;
        }
        tasks.delete(this.index, ui, storage);
    }

    /**
     * Parse a input string to a delete command
     * @param inputStr user input
     * @param tasks task list
     * @return a delete command
     * @throws Exception when the input does not contain integer
     */
    public static Command parseDelete(String inputStr, TaskList tasks) throws Exception {
        String indexStr = inputStr.substring(7);

        if (indexStr.contains("-")) {
            return new DeleteCommand(Parser.parseRange(indexStr, tasks));
        }

        int index = Integer.parseInt(indexStr);
        tasks.checkInBound(index);
        return new DeleteCommand(index);
    }
}
