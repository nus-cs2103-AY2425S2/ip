package nikingoda.Command;

public abstract class UpdateCommand extends Command {
    protected final int id;

    /**
     * update details of task with id
     *
     * @param id id of task
     */
    protected UpdateCommand(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
