package botato.command;

import botato.TaskList;
import botato.Ui;
import botato.task.Deadline;
import botato.task.Task;

/**
 * This class encapsulates the actions to be performed when a deadline command is parsed by the parser.
 * When executed, it creates a new {@link Deadline} task and adds it to the given task list.
 * Then, it prints a completion message.
 */
public class DeadlineCommand extends Command {
    private final String cmd;
    public DeadlineCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    public String execute(TaskList tasks, Ui ui) {
        Task task = new Deadline(cmd);
        tasks.addTask(task);
        System.out.println("Deadline added: \n" + task);
        return "Deadline added: \n" + task;
    }
}
