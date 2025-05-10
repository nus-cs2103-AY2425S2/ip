package kyrie;

/**
 * Represents a task.
 */
public class Task {
    protected Boolean isDone;
    protected String name;
    protected TaskType type;
    protected TagList tags;

    /**
     * Constructor for Task.
     * 
     * @param name The name of the task.
     * @param type The type of the task.
     */
    public Task(String name, TaskType type) {
        assert name != null : "Name of task cannot be null";
        this.name = name;
        this.isDone = false;
        this.type = type;
        this.tags = new TagList();
    }

    /**
     * Constructor for Task.
     * 
     * @param name The name of the task.
     * @param type The type of the task.
     * @param tags The tags of the task.
     */
    public Task(String name, TaskType type, TagList tags) {
        assert name != null : "Name of task cannot be null";
        this.name = name;
        this.isDone = false;
        this.type = type;
        this.tags = tags;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Retrieves the name of the task.
     * 
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Checks if the task is done.
     * 
     * @return True if the task is done, false otherwise.
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Retrieves the type of the task.
     * 
     * @return The type of the task.
     */
    public void addTag(Tag tag) {
        this.tags.addTag(tag);
    }

    /**
     * Returns the string representation of the task, suitable for saving to file.
     * 
     * @return The string representation of the task, suitable for saving to file.
     */
    public String toFileString() {
        String fileString = this.type + " | " + (this.isDone ? "1" : "0") + " | " + this.name;
        if (this.tags.getSize() > 0) {
            fileString += " | " + this.tags;
        }
        return fileString;
    }

    /**
     * Returns the string representation of the task.
     * 
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.name + " " + this.tags;
    }
}