public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws WrongFormatException {
        if (index < 1 || index > tasks.size()) {
            throw new WrongFormatException("Oops! That task number doesn't exist!\n" +
                    "Please enter a valid task number.");
        }
        Task removedTask = tasks.getTask(index - 1);
        tasks.deleteTask(index - 1);
        storage.saveTasks(tasks.getAllTasks());
        ui.deleteMessage(removedTask, tasks.size());
    }
}
