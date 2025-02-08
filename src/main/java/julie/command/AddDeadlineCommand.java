package julie.command;

import julie.*;
import julie.exception.WrongFormatException;
import julie.task.Deadline;
import julie.task.Task;

import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String dateTime;

    public AddDeadlineCommand(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws WrongFormatException {
        if (description.isEmpty() || dateTime.isEmpty()) {
            throw new WrongFormatException("Oops! Missing description or date/time!\n" +
                    "Correct format: deadline <description> /by <DD-MM-YYYY HHMM>");
        }

        try {
            Task deadline = new Deadline(description, dateTime);
            tasks.addTask(deadline);
            storage.saveTasks(tasks.getAllTasks());
            ui.ackMessage(deadline, tasks.size());
        } catch (DateTimeParseException e) {
            throw new WrongFormatException("Oops! Wrong date format!\n" +
                    "Correct format: deadline <description> /by <DD-MM-YYYY HHMM>");
        }
    }
}
