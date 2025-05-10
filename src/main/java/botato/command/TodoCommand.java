package botato.command;

import botato.TaskList;
import botato.Ui;
import botato.task.Task;
import botato.task.Todo;

/**
 * This class encapsulates the actions to be performed when a todo command is parsed by the parser.
 * When executed, it creates and adds a new todo task to the given task list with a description extracted from the user
 * input string.
 */
public class TodoCommand extends Command {
    private final String cmd;
    public TodoCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    public String execute(TaskList tasks, Ui ui) {
        Task task = new Todo(cmd);
        tasks.addTask(task);
        System.out.println("Todo added:\n" + task);
        return "Todo added:\n" + task;
    }
}
