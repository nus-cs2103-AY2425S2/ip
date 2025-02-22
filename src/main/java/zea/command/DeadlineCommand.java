package zea.command;

import java.time.LocalDateTime;
import java.util.Objects;

import zea.Storage;
import zea.ZeaException;
import zea.task.Deadline;
import zea.task.Tasks;
import zea.ui.Ui;


/**
 * A command for creating Deadlines
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime by;

    /**
     * Constructor of a Deadline Command
     * @param description - A description of the deadline
     * @param by - The deadline date
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(Tasks tasks, Storage storage) throws ZeaException {
        Deadline d = new Deadline(this.description, this.by);
        tasks.addTask(d);
        storage.save(tasks);
        return Ui.formatItem("Great! I've created a new deadline for you!", d, "You now have "
                + tasks.getTotalTasks() + " tasks");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        DeadlineCommand that = (DeadlineCommand) other;
        return Objects.equals(description, ((DeadlineCommand) other).description) && Objects.equals(this.by, that.by);
    }
}
