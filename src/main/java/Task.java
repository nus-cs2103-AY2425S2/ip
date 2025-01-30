class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {   //Marks a checkbox
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {  //marks isDone as true
        isDone = true;
    }

    public void markAsNotDone() { //marks isDone as false
        isDone = false;
    }

    @Override
    public String toString() {  //default task string
        return "[" + getStatusIcon() + "] " + description;
    }
}
