package doot;

/**
 * the subclass of task, specifically for the todo tasks
 */
public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
        type = Type.T;
    }

    /**
     * returns details for this object
     * @return details for object, to be printed
     */
    @Override
    public String getDetails() {
        return this.getType() + this.getStatusIcon() + " " + this.getDescription() +
                (this.getTag() != null ? " #" + getTag() : "");
    }

    /**
     * returns the string necessary to make this object again
     * @return a string, if fed back into the bot will recreate the command needed to create this task
     */
    @Override
    public String creationString() {
        StringBuilder list = new StringBuilder();
        if (this.isDone) {
            list.append("d ");
        }
        list.append("todo ").append(this.getDescription());
        if (getTag() != null) {
            list.append(" /tag ").append(getTag());
        }

        return list.toString();
    }
}
