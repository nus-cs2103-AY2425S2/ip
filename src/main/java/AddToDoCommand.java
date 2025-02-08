public class AddToDoCommand extends Command {
    private final String description;

    public AddToDoCommand(String description){
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws WrongFormatException {
        if (description.isEmpty()) {
            throw new WrongFormatException("Oops! The correct format for a todo is:\ntodo <description>");
        }
        Task todo = new ToDo(description);
        tasks.addTask(todo);
        storage.saveTasks(tasks.getAllTasks());
        ui.ackMessage(todo, tasks.size());
    }
}
