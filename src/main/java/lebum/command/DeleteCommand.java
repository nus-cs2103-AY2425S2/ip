package lebum.command;
import java.util.ArrayList;
import java.util.Collections;

import lebum.exception.DukeException;
import lebum.main.Storage;
import lebum.main.TaskList;
import lebum.main.Ui;
import lebum.task.Task;


/**
 * Command for deleting one or multiple tasks.
 */
public class DeleteCommand extends Command {
    private String[] parts;
    private String response = "";

    public DeleteCommand(String[] parts) {
        this.parts = parts;
    }

    public String getResponse() {
        return this.response;
    }

    public boolean isMassCommand(String[] parts) {
        return parts.length > 1 && parts[1].contains(",");
    }

    /**
     * Executes the deletion of tasks
     * @param tasks to be edited
     * @param storage to load the files
     * @param ui to interact
     * @throws DukeException when index is out of bound or not a number
     */
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        try {
            if (parts.length < 2) {
                throw new DukeException("OOPS!!! You did not specify an index to delete, please specify an index!");
            }

            Task t = null;
            if (isMassCommand(parts)) {
                response = massDelete(tasks, storage);
            } else {
                try {
                    int index = Integer.parseInt(parts[1]);
                    validateIndex(index, tasks);

                    t = tasks.array().get(index - 1);
                    tasks.delete(index - 1);
                    response += "You have deleted " + t.print();
                    storage.saveToFile(tasks);
                } catch (NumberFormatException e) {
                    throw new DukeException("OOPS!!! '" + parts[1]
                            + "' is not a valid number. Please provide a valid task index!!!");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! You did not specify an index to delete, SPECIFY AN INDEX!!!");
        }
    }

    /**
     * Validates if the provided index is within the valid range for the task list.
     *
     * @param index The index to validate (1-based indexing)
     * @param tasks The task list to check against
     * @throws DukeException If the index is out of bounds
     */
    private void validateIndex(int index, TaskList tasks) throws DukeException {
        if (index <= 0 || index > tasks.getNum_of_tasks()) {
            throw new DukeException("OOPS!!! The specified index " + index
                    + " is out of bounds. Please provide an index between 1 and " + tasks.getNum_of_tasks() + ".");
        }
    }

    private String massDelete(TaskList tasks, Storage storage) throws DukeException {
        String content = parts[1];
        String[] indexArray = content.split(",");
        String response = "";
        ArrayList<Integer> indices = new ArrayList<>();

        // Validate and parse all indices first
        for (int i = 0; i < indexArray.length; i++) {
            try {
                int index = Integer.parseInt(indexArray[i].trim());
                validateIndex(index, tasks);
                indices.add(index);
            } catch (NumberFormatException e) {
                throw new DukeException("OOPS!!! '" + indexArray[i].trim()
                        + "' is not a valid number. Please provide valid task indices.");
            }
        }

        // Sort indices in descending order to avoid shifting issues when deleting
        Collections.sort(indices, Collections.reverseOrder());

        response += "Noted. I've removed these tasks:\n";
        for (int i = 0; i < indices.size(); i++) {
            int index = indices.get(i) - 1;
            Task deletedTask = tasks.array().get(index);
            response += deletedTask.print() + "\n";

            try {
                tasks.delete(index);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        storage.saveToFile(tasks);
        return response;
    }
}
