public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws WrongFormatException {
        if (index < 1 || index > tasks.size()) {
            throw new WrongFormatException("Oops! That task number doesn't exist!");
        }
        Task task = tasks.getTask(index - 1);
        task.markDone();
        storage.saveTasks(tasks.getAllTasks());
        ui.markMessage(task);
    }
}
