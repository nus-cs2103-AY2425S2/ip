package command;

import storage.Storage;
import task.PriorityRoster;
import ui.Ui;

/**
 * Unmarks a task as done based on its index number
 */
public class UnmarkCommand extends Command {
    private final int num;

    public UnmarkCommand(int num) {
        this.num = num;
    }

    @Override
    public String execute(PriorityRoster roster, Ui ui, Storage storage) {
        if(num < 1 || num > roster.numberOfTasks()) {
                throw new IndexOutOfBoundsException("Please input a task number between 1 and " +
                        (roster.numberOfTasks()));
        }
        roster.markTaskAsUncompleted(num);
        return ui.markAsUncompleted(roster,num) + "\n" + ui.priorityPoints(roster);
    }
}