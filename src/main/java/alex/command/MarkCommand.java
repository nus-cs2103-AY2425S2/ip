package alex.command;

import alex.Parser;
import alex.Storage;
import alex.Ui;
import alex.task.TaskList;

public class MarkCommand extends Command {
    private int index;
    private boolean markDone;
    private int[] range;

    public MarkCommand(int index, boolean markDone) {
        this.index = index;
        this.markDone = markDone;
    }

    public MarkCommand(int[] range, boolean markDone) {
        this.range = range;
        this.markDone = markDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (range != null) {
            tasks.mark(range, markDone, ui, storage);
            return;
        }
        tasks.mark(index, markDone, ui, storage);
    }

    public static Command parseMark(String inputStr, TaskList tasks) throws Exception {
        String indexStr = inputStr.substring(5);
        if (indexStr.contains("-")) {
            int[] range = Parser.parseRange(indexStr, tasks);
            return new MarkCommand(range, true);
        }
        int index = Integer.parseInt(indexStr);
        tasks.checkInBound(index);
        return new MarkCommand(index, true);
    }


    public static Command parseUnmark(String inputStr, TaskList tasks) throws Exception {
        String indexStr = inputStr.substring(7);
        if (indexStr.contains("-")) {
            int[] range = Parser.parseRange(indexStr, tasks);
            return new MarkCommand(range, false);
        }
        int index = Integer.parseInt(indexStr);
        tasks.checkInBound(index);
        return new MarkCommand(index, false);
    }

}
