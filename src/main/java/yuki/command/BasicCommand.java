package yuki.command;

import yuki.Storage;
import yuki.TaskList;
import yuki.Ui;
import yuki.YukiException;
import yuki.task.Task;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a command to add a task to the task list.
 */
public class BasicCommand extends Command{
    public BasicCommand(String[] commands, String description, boolean isExit) {
        super(commands, description, isExit);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to list, mark, unmark or delete a task.
     * @param tasks TaskList containing the tasks.
     * @throws YukiException if the task number does not exist or is not a number.
     */
    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) throws YukiException {
        return switch (this.getCommand(0)) {
            case "list" -> handleListCommand(tasks);
            case "find" -> handleFindCommand(tasks);
            default -> "Invalid command";
        };
    }

    private String handleListCommand(TaskList<Task> tasks) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append((i + 1)).append(". ").append(tasks.getDescription(i)).append("\n");
        }
        Command.lastCommand = this;
        return output.toString();
    }

    private String handleFindCommand(TaskList<Task> tasks) {
        try {
            String keyword = Arrays.stream(commands, 1, commands.length)
                    .collect(Collectors.joining(" "));
            StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.getTaskName(i).contains(keyword)) {
                    output.append((i + 1)).append(". ").append(tasks.getDescription(i)).append("\n");
                }
            }
            Command.lastCommand = this;
            return output.toString();
        } catch (IndexOutOfBoundsException e) {
            return "Please enter a keyword to search for";
        }
    }

    /**
     * This command cannot be undone.
     */
    @Override
    public String undo(TaskList<Task> tasks) throws YukiException {
        return "This command cannot be undone";
    }
}
