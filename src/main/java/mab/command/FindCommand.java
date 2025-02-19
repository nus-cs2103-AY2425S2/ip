package mab.command;

import java.util.ArrayList;

import mab.MabException;
import mab.task.Task;
import mab.util.Ui;

/**
 * Handles searching for tasks containing specified keywords.
 */
public class FindCommand extends Command{

    /**
     * Creates a new search command processor.
     * 
     * @param args Search keyword(s) string
     */
    public FindCommand(String args) {
        super(args);
    }

    /**
     * Displays tasks containing search keyword in their description.
     * 
     * @param list Task list to search through
     * @throws MabException If search keyword is empty
     * 
     * @implSpec Search is case-insensitive and matches partial words
     * @implNote Output format matches ListCommand but only shows matches
     */
    @Override
    public String execute(ArrayList<Task> tasks) throws MabException {
        if (args.isEmpty()) {
            throw new MabException("Huh? Please provide a keyword to search for.");
        }
        String keyword = args.toLowerCase();
        String output ="";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getText().toLowerCase().contains(keyword)) {
                output += String.format("%d. %s\n", i + 1, task.toString());
            }
        }
        if (output.isBlank()){
            output = "I tried but there are no matching tasks found :P";
        }
        return String.format("Here are the matching tasks in your list:\n%s", output);
    }
}
