package kunkka.command;
import kunkka.tasklist.Tasklist;

public abstract class Command {
    protected String type;

    public Command(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract String execute(Tasklist tasks);

}
