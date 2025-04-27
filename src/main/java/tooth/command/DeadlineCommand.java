package tooth.command;

import java.time.LocalDate;

import tooth.stuff.Storage;
import tooth.stuff.TaskList;
import tooth.stuff.UI;
import tooth.task.Deadline;

/**
 * Command that creates a new deadline
 */
public class DeadlineCommand implements Command {
    private String description;
    private LocalDate by;

    /**
     * Constructor of DeadlineCommand
     * @param description the description of the Deadline
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes task
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        int prevNumTask = tasks.numTask();
        Deadline deadline = Deadline.of(description, by);
        tasks.add(deadline);
        ui.say("Added new deadline");
        assert prevNumTask < tasks.numTask();
    }
}
