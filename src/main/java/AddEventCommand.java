import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws WrongFormatException {
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new WrongFormatException("Oops! Missing description or date/time!\n" +
                    "Correct format: event <description> /from <DD-MM-YYYY HHMM> /to <DD-MM-YYYY HHMM>");
        }

        try {
            Task event = new Event(description, from, to);
            tasks.addTask(event);
            storage.saveTasks(tasks.getAllTasks());
            ui.ackMessage(event, tasks.size());
        } catch (DateTimeParseException e) {
            throw new WrongFormatException("Oops! Wrong date format!\n" +
                    "Correct format: event <description> /from <DD-MM-YYYY HHMM> /to <DD-MM-YYYY HHMM>");
        }
    }
}
