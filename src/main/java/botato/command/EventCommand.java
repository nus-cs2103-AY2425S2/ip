package botato.command;

import botato.TaskList;
import botato.Ui;
import botato.task.Event;

/**
 * This class encapsulates the actions to perform when an event command string is parsed by {@link botato.Parser}.
 * It creates a new Event task when executed and adds it to the task, then prints a completion message.
 */
public class EventCommand extends Command {
    private final String cmd;

    public EventCommand(String cmd) {
        this.cmd = cmd;
    }
    @Override
    public String execute(TaskList tasks, Ui ui) {
        Event task = new Event(cmd);
        tasks.addTask(task);
        System.out.println("Event added:\n" + task);
        return "Event added: \n" + task;
    }
}
