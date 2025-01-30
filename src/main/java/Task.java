public class Task {

    protected String description;
    protected boolean isDone;

    protected String statusIcon = " ";
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public void markAsDone() {
        this.isDone = true;
        this.statusIcon = "X";
    }

    public void markAsUndone() {
        this.isDone = false;
        this.statusIcon = " ";
    }
    @Override
    public String toString() {
        String str = String.format("[%s] %s", this.statusIcon, this.description);
        return str;
    }

    //...
}