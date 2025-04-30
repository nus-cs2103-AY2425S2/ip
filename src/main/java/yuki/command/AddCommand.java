package yuki.command;

import java.util.Arrays;
import java.util.stream.Collectors;

import yuki.Storage;
import yuki.TaskList;
import yuki.Ui;
import yuki.YukiException;
import yuki.task.Deadline;
import yuki.task.Event;
import yuki.task.Task;
import yuki.task.Todo;


/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    public AddCommand(String[] commands, String description, boolean isExit) {
        super(commands, description, isExit);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to add a task to the task list.
     * @param tasks
     * @param ui
     * @param storage
     * @return
     * @throws YukiException
     */
    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException {

        return switch (this.getCommand(0)) {
            case "todo" -> handleTodoCommand(tasks);
            case "deadline" -> handleDeadlineCommand(tasks);
            case "event" -> handleEventCommand(tasks);
            default -> "";
        };
    }

    private String handleTodoCommand(TaskList<Task> tasks) throws YukiException {
        String result = Arrays.stream(commands, 1, commands.length)
                .collect(Collectors.joining(" "));
        if (result.isEmpty()) {
            throw new YukiException("The description of a todo cannot be empty.");
        }
        Task newTodo = new Todo("0", result);
        tasks.add(newTodo);
        return constructOutput(newTodo, tasks);
    }

    private String handleDeadlineCommand(TaskList<Task> tasks) throws YukiException {
        String result = Arrays.stream(commands, 1, commands.length)
                .collect(Collectors.joining(" "));
        String[] c = result.split("/by");
        c = Arrays.stream(c).map(String::trim).toArray(String[]::new);
        if (c.length != 2) {
            throw new YukiException(
                    "Invalid format for deadline command. Please enter in the format: deadline <description> /by <date>");
        }
        Task newDeadline = new Deadline("0", c[0], c[1]);
        tasks.add(newDeadline);
        Storage.save(tasks);
        return constructOutput(newDeadline, tasks);
    }

    private String handleEventCommand(TaskList<Task> tasks) throws YukiException {
        String result = Arrays.stream(commands, 1, commands.length)
                .collect(Collectors.joining(" "));
        String[] c = result.replace("/to", "/from ").split("/from");
        c = Arrays.stream(c).map(String::trim).toArray(String[]::new);
        if (c.length != 3) {
            throw new YukiException(
                    "Invalid format for event command. Please enter in the format: event <description> "
                            + "/from <start date> /to <end date>");
        }
        Task newEvent = new Event("0", c[0], c[1], c[2]);
        tasks.add(newEvent);
        Storage.save(tasks);
        return constructOutput(newEvent, tasks);
    }

    private String constructOutput(Task task, TaskList<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append("Got it. I've added this task:\n");
        output.append(task).append("\n");
        output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
        Command.lastCommand = this;
        Storage.save(tasks);
        return output.toString();
    }

    /**
     * Undoes the last add command.
     * @param tasks
     * @return
     * @throws YukiException
     */
    @Override
    public String undo(TaskList<Task> tasks) throws YukiException {
        tasks.remove(tasks.size() - 1);
        Storage.save(tasks);
        return "Last task removed.";
    }
}
