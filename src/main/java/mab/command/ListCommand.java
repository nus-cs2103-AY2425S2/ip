package mab.command;

import java.util.ArrayList;

import mab.task.Task;
import mab.MabException;

/**
 * Displays all tasks in the list with their current status.
 */
public class ListCommand extends Command {
     /**
     * Creates a new list command processor.
     * 
     * @param args Unused parameter for command consistency
     */
    public ListCommand(String args) {
        super(args);
    }

    /**
     * Prints all tasks in numbered format with status indicators.
     * 
     * @param list The task list to display
     * @throws MabException Never thrown under normal operation
     */
    @Override
    public String execute(ArrayList<Task> list) throws MabException {
        if (list.isEmpty()){
            return "Sorry! There are no tasks in your list :P";
        }
        String output = "Here are all the tasks in your list!:\n";
        for (int i = 1; i <= list.size(); i++) {
            output += i + ". " + list.get(i - 1) + "\n";
        }
        return output;
    }
}
