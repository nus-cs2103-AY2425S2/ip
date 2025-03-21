package tyler.command;

import java.time.format.DateTimeParseException;

import tyler.storage.Storage;
import tyler.task.Event;
import tyler.task.list.TaskList;
import tyler.ui.Ui;

/**
 * Represents a command to add a new event to the list.
 */
public class EventCommand extends Command {
    private final String[] tokens;

    public EventCommand(String[] tokens) {
        super();
        this.tokens = tokens;
    }

    /**
     * Adds specified event to the list of tasks and returns the modified list.
     *
     * @param tasks   The list of tasks to which the event should be added.
     * @param ui      The UI object for any required printing.
     * @param storage The storage for I/O involving storing the tasks on the disk (unused).
     * @return The list with the task added.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (!tokens[1].contains("/from") || !tokens[1].contains("/to")
                    || tokens[1].split("/from ")[1].split(" /to")[0].isBlank()
                    || tokens[1].split("/to ")[1].isBlank()
            ) {
                throw new IllegalArgumentException(
                        "\t !!Event must include a 'from' time and 'to' time!!");
            }
            Event event = new Event(tokens[1].split(" /from")[0],
                    tokens[1].split("/from ")[1].split(" /to")[0],
                    tokens[1].split("/to ")[1]);
            assert event.getCategory().equals("E");
            boolean isDuplicate = tasks.isDuplicate(event);
            if (!isDuplicate) {
                tasks.addToList(event, ui);
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
