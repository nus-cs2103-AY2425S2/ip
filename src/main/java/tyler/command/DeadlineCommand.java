package tyler.command;

import java.time.format.DateTimeParseException;

import tyler.storage.Storage;
import tyler.task.Deadline;
import tyler.task.list.TaskList;
import tyler.ui.Ui;

/**
 * Represents a command to add a new deadline to the list.
 */
public class DeadlineCommand extends Command {
    private final String[] tokens;

    public DeadlineCommand(String[] tokens) {
        super();
        this.tokens = tokens;
    }

    /**
     * Adds specified deadline to the list of tasks and returns the modified list.
     *
     * @param tasks   The list of tasks to which the deadline should be added.
     * @param ui      The UI object for any required printing.
     * @param storage The storage for I/O involving storing the tasks on the disk (unused).
     * @return The list with the task added.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (!tokens[1].contains("/by") || tokens[1].split("/by ")[1].isBlank()) {
                throw new IllegalArgumentException("\t !!Deadline must include a 'by' time!!");
            }
            Deadline deadline = new Deadline(
                    tokens[1].split(" /by")[0], tokens[1].split("/by ")[1]);
            assert deadline.getCategory().equals("D");
            boolean isDuplicate = tasks.isDuplicate(deadline);
            if (!isDuplicate) {
                tasks.addToList(deadline, ui);
            } else {
                ui.showMessage("\t !!This task already exists!!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("\t !!Please provide the correct number of arguments!!");
        } catch (IllegalArgumentException e) {
            ui.showMessage(e.getMessage());
        } catch (DateTimeParseException e) {
            ui.showMessage("\t !!Please enter the date in YYYY-MM-DD format!!");
        }
        return tasks;
    }
}
