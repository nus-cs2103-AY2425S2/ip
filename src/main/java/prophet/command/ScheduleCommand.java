package prophet.command;

import java.time.LocalDate;

import prophet.gui.Ui;
import prophet.storage.Storage;

/**
 * The ScheduleCommand class represents the command to schedule a task.
 */
public class ScheduleCommand extends Command {
    private final LocalDate taskDescription;

    /**
     * Initialises a newly created ScheduleCommand object.
     * @param type the type of command
     * @param taskDescription the description of the task
     */
    public ScheduleCommand(CommandType type, LocalDate taskDescription) {
        super(type);
        this.taskDescription = taskDescription;
    }

    /**
     * Queries for tasks that match the {@link LocalDate} given.
     * @param ui the user interface of the chatbot
     * @param storage the storage of tasks
     * @return the message to be printed
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        return storage.findTasksByDate(this.taskDescription);
    }
}
