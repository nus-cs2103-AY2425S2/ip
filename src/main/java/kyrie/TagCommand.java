package kyrie;

/**
 * Represents a tag command.
 */
public class TagCommand extends Command {
    private String tagName;
    private Integer taskIndex;
    public static final String COMMAND_WORD = "tag";
   
    /**
     * Constructor for TagCommand.
     * 
     * @param tagName The name of the tag.
     */
    public TagCommand(Integer taskIndex, String tagName) {
        this.tagName = tagName;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to tag a task.
     * 
     * @param task The task to tag.
     */
    @Override
    public void execute(Storage storage, Ui ui, TaskList tasks) throws KyrieException {
        if (tagName.isEmpty()) {
            throw new KyrieException("Tag name cannot be empty!");
        }
        if (taskIndex > tasks.getTaskCount() || taskIndex < 1) {
            throw new KyrieException("Task does not exist in your list");
        }
        Task task = tasks.getTask(taskIndex);
        task.addTag(new Tag(tagName));
        ui.showTaggedTask(task);
        storage.saveData(tasks);
    }


}
