package miki.command;

import java.time.LocalDateTime;

import miki.exception.MikiException;
import miki.storage.Storage;
import miki.task.Deadline;
import miki.task.Task;
import miki.task.TaskList;
import miki.ui.Ui;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime deadline;
    private String[] tags;

    /**
     * Constructor for AddDeadlineCommand.
     *
     * @param description Description of the deadline task.
     * @param deadline    Deadline of the deadline task.
     * @param tags        Tags of the deadline task.
     */
    public AddDeadlineCommand(String description, LocalDateTime deadline, String... tags) {
        super(false);
        this.description = description;
        this.deadline = deadline;
        this.tags = tags;
    }

    /**
     * Executes the command to add a deadline task.
     *
     * @param tasks   List of tasks.
     * @param ui      Ui object.
     * @param storage Storage object.
     *
     * @return The output of the execution
     *
     * @throws MikiException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        Task task = new Deadline(description, deadline, tags);
        assert task != null : "Task should not be null";

        tasks.addTask(task);
        storage.save(tasks.getTaskList());
        String output = ui.showTaskAdded(task, tasks.getSize());
        return output;
    }
}
