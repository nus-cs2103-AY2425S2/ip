package command;

import storage.Storage;
import task.PriorityRoster;
import ui.Ui;

public class HelpCommand extends Command {
    String addMessage = "Add a todo, deadline or event task\n";
    String numberCommands = "Mark, unmark or delete a task number \n";
    String listCommands = "List: Sorted list from oldest to newest\n" +
                            "Priority: Tasks sorted by priority\n";
    String searchCommand = "Search: search {keyword}\n";
    public HelpCommand() {}

    @Override
    public String execute(PriorityRoster roster, Ui ui, Storage storage) {
        return addMessage + numberCommands + listCommands + searchCommand;
    }
}
