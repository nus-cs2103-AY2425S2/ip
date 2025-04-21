package nyanko.command;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import nyanko.storage.Storage;
import nyanko.task.Deadline;
import nyanko.task.TaskList;
import nyanko.ui.Ui;

/**
 * Represents a command to create and add a {@link Deadline} task to the task list.
 * The command prompts the user to enter a due date in the format "yyyy-MM-dd HHmm".
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs a {@code DeadlineCommand} with the given task description.
     *
     * @param argument The description of the deadline task.
     */
    public DeadlineCommand(String argument) {
        String[] parts = argument.split("\\\\", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid deadline format! Use: deadline description\\due_date");
        }
        if (parts[0].trim().isEmpty()) {
            throw new IllegalArgumentException("Your description cannot be empty dumbdumb!!");
        }
        this.description = parts[0].trim();
        this.by = parts[1].trim();
    }

    /**
     * Executes the command by prompting the user for a due date,
     * creating a {@link Deadline} task, and adding it to the task list.
     * If an invalid date format is entered, the user is asked to re-enter it.
     *
     * @param tasks   The task list to add the deadline task.
     * @param ui      The UI instance for user interaction.
     * @param storage The storage instance to save the updated task list.
     * @throws IOException If an error occurs while saving the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            Deadline deadline = new Deadline(description, by);
            tasks.addTask(deadline);
            String message = "WOW you're so hardworking\n"
                    + "ok fine... your task has been added!\nadded: "
                    + deadline.toString()
                    + "\nOh my! You have " + tasks.size() + " tasks!";
            ui.showMessage(message);
            storage.save(tasks.getTasks());
        } catch (DateTimeParseException e) {
            ui.showError("Your date/time format is incorrect! Don't be dumb! Use yyyy-MM-dd HHmm!");
        }
    }
}
