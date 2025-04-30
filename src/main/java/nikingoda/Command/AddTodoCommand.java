package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.Task.Task;
import nikingoda.Task.Todo;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public class AddTodoCommand extends AddCommand {
    private final String command;

    /**
     * command to add TodoTask
     *
     * @param command command
     */
    public AddTodoCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException {
        try {
            String description = command.substring(5).trim();
            if (description.isBlank()) {
                throw new NikingodaException("Description must not be blank!!!");
            }
            Task task = new Todo(description);
            tasks.add(task);
            this.setResponse("Got it, I've added this task: \n" + task);
            storage.saveTask(tasks);
        } catch (NikingodaException e) {
            throw e;
        } catch (Exception e) {
            throw new NikingodaException("Invalid command. \nShould be: todo <description>");
        }
    }
}
