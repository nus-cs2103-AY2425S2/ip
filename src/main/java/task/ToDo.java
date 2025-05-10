package task;

public class ToDo extends Task {


    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String getInfo() {
        if (super.isDone) {
            return "T " + 1 + "/" + description;
        } else {
            return "T " + 0 + "/" + description;
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
