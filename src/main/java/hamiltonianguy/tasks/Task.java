package hamiltonianguy.tasks;

public abstract class Task {
    private String desc;
    private boolean isDone;

    public Task(String desc) {
        assert desc != null && !desc.trim().isEmpty() : "Task description cannot be null or empty";
        this.desc = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    public void mark() {
        if (isDone) {
            throw new IllegalStateException("OOPS!!! This task is already marked as done.");
        }
        this.isDone = true;
    }

    public void unmark() {
        if (!isDone) {
            throw new IllegalStateException("OOPS!!! This task is not marked as done yet.");
        }
        this.isDone = false;
    }

    public abstract String getType();

    public abstract String toFileString();

    public boolean isDone() {
        return isDone;
    }

    public String getDesc() {
        return desc;
    }


    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return status + " " + desc;
    }
}

