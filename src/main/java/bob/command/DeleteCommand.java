package bob.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bob.task.Task;
import bob.task.TaskList;

/**
 * This class represents a command to delete a task (or more).
 */
public class DeleteCommand extends Command {

    private List<Integer> taskNumbers;

    public DeleteCommand(ArrayList<Integer> taskNumbers) {
        this.taskNumbers = taskNumbers;
    }

    @Override
    public String execute() throws IOException, IndexOutOfBoundsException {

        StringBuilder output = new StringBuilder();
        output.insert(0, "Bob is on it! Deleted the following task(s):");

        for (int number : taskNumbers) {
            Task task = TaskList.getTask(number);
            output.append("\n").append(task.toString());
        }

        TaskList.deleteTasks(taskNumbers);

        return output.toString();
    }
}
