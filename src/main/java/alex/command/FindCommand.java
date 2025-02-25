package alex.command;

import java.util.ArrayList;
import java.util.stream.Collectors;

import alex.Storage;
import alex.Ui;
import alex.task.Task;
import alex.task.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> result = tasks.getTasks().stream()
                .filter(task -> task.getContent().contains(this.keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        ui.showSearchResult(result);
    }

    /**
     * Parse user input to a find command
     * @param inputStr user input
     * @return find command
     */
    public static Command parseFind(String inputStr) {
        String keyword = inputStr.substring(5);
        return new FindCommand(keyword);
    }
}
