package viktor.commands;

import viktor.exceptions.ViktorException;
import viktor.tasks.Task;
import viktor.tasks.TaskList;

/**
 * Represents a command to find tasks by their description.
 */
public class FindCommand implements Commandable {
    private TaskList tasks;
    private String nameInput;

    /**
     * Constructs a FindCommand with the specified task list and name input.
     *
     * @param tasks The list of tasks to search through.
     * @param nameInput The input string to search for in task descriptions.
     */
    public FindCommand(TaskList tasks, String nameInput) {
        this.tasks = tasks;
        this.nameInput = nameInput;
    }

    @Override
    public String execute() throws ViktorException {
        StringBuilder response = new StringBuilder("Here are your tasks matching \"" + nameInput + "\":\n");

        boolean taskFound = false;
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(nameInput)) {
                response.append(task).append("\n");
                taskFound = true;
            }
        }

        if (!taskFound) {
            response.append("Vast emptiness is all there is. \"").append(nameInput)
                    .append("\"? I haven't heard of that in a long, long time. ")
                    .append("There are no tasks matching \"").append(nameInput).append("\"!");
        }

        response.append("\n");
        return response.toString();
    }
}
