package bun.ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList foundTasks = taskList.filterByWord(this.keyword);
        return ui.getFoundForPrint(foundTasks);
    }
}
