package botato.command;

import botato.TaskList;
import botato.Ui;

/**
 * Encapsulates what actions to perform when a help command is parsed by the parser.
 * When executed, shows the user the list of commands and date formats they can use.
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui) {
        System.out.println("Opening my guide in a new window...");
        return "Opening my guide in a new window...";
    }
}
