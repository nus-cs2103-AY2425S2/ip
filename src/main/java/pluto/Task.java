package pluto;

/**
 * Represents a Task. This class encapsulates the
 * description, index and completion status of a task
 * Common functionalities include marking a task as
 * done/undone, and changing the tasks from file format
 */
public abstract class Task {
    protected static int i = 0;
    protected int index;
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task, where each task is given
     * an index, description, and is not marked as completed
     * @param description a String that describes the task
     */
    public Task(String description) {
        this.index = i++;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the specified description and
     * completion status
     * @param description a String that is the description of the task
     * @param isDone a boolean value that represents the completion status
     *               of a task
     */
    public Task(String description, boolean isDone) {
        this.index = i++;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Displays the completion status of a task
     * @return a String that shows the completion status of a task
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Marks a task as completed
     */
    public void markAsDone() {
        System.out.println();
        this.isDone = true;
    }

    /**
     * Marks a task as not completed
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Displays the status of a task, including the completion
     * status and the description of the task
     * @return a String that shows the status of the task
     */
    public String taskStatusMessage() {
        return " " + this.getStatusIcon() + " " + this.description;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public abstract String toFileFormat();

    public abstract boolean isScheduledFor(String dateInput);

    /**
     * Converts the task list from file format
     * to the display format on the chatbot
     * @param line the String from the storage file
     * @return a Task to be displayed on the chatbot output
     */
    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        boolean isDone = parts[1].equals("1");
        switch (parts[0]) {
        case "T":
            return new ToDo(parts[2], isDone);
        case "D":
            return new Deadline(parts[2], parts[3], isDone);
        case "E":
            return new Event(parts[2], parts[3], parts[4], isDone);
        default:
            return null;
        }
    }

    public boolean isDescriptionMatching(String keyword) {
        return this.description.contains(keyword);
    }
}
