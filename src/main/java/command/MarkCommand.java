package command;

import storage.Storage;
import task.PriorityRoster;
import ui.Ui;

/**
 * Mark a task as done based on its index number
 */
public class MarkCommand extends Command {
    private final int num;

    public MarkCommand(int num) {
        this.num = num;
    }

    @Override
    public String execute(PriorityRoster roster, Ui ui, Storage storage) {
        if (num < 1 || num > roster.numberOfTasks()) {
            throw new IndexOutOfBoundsException("Please input a valid task number between 1 and "
                                            + (roster.numberOfTasks()));
        }
        roster.markTaskAsCompleted(num);
        return ui.markAsCompleted(roster,num) + "\n" + ui.priorityPoints(roster);
    }
}
