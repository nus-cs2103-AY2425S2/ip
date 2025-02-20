package pixel.command;

import pixel.task.TaskList;
import pixel.util.Storage;
import pixel.util.Ui;

public class SearchCommand extends Command {
    private final String keyword;

    public SearchCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Ui.searchResponse(taskList.searchTask(this.keyword));
    }
}
