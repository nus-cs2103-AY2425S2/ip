package parakeet.command;

import parakeet.DuplicateTaskError;
import parakeet.Storage;
import parakeet.TaskList;

public class ExitCommand extends Command {

    public ExitCommand() {

    }

    /**
     * Executes the command to exit the application.
     * This method saves the current task list to the file, displays a message indicating
     * that the tasks have been saved, and then prints the exit message to the UI.
     * Finally, it closes the scanner used for user input.
     *
     * @param taskList the list of tasks to be saved.
     * @param storage  the storage used to save the tasks.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DuplicateTaskError {
        storage.writeToFile(taskList);
        String response = "Bye. Hope to see you again soon! \nTask list has been saved";
        return response;

    }
}
