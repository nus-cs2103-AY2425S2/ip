package botato.command;

import botato.TaskList;
import botato.Ui;

/**
 * This class encapsulates the actions to be performed when an exit command is parsed by the parser.
 * When executed, it sets isExit to true, saves tasks the task list to local storage, then prints an exit message.
 * Then, the main class terminates the while loop, and exits the program.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui) {
        super.isExit = true;
        tasks.save();
        System.out.println("Hope I helped! See you~");
        return "Hope I helped! See you~";
    }
}
