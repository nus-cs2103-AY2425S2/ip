package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.Task.Task;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    /**
     * Command to find task whose description contain keyword
     *
     * @param keyword key word
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
        try {
            ArrayList<Task> tmpTasks = tasks.taskContainsKeyword(keyword);
            StringBuilder response = new StringBuilder("Here are the matching tasks: \n");
            int id = 1;
            for (Task task : tmpTasks) {
                response.append(id).append(". ").append(task.toString()).append("\n");
                id++;
            }
            this.setResponse(response.toString());
        } catch (Exception e) {
            throw new NikingodaException(e.getMessage());
        }
    }
}
