package tooth.task;

/**
 * Todo Task Object
 */
public class ToDo extends Task {
    private ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    public static ToDo of(String name) {
        return new ToDo(name, false);
    }

    public static ToDo of(String name, boolean isDone) {
        return new ToDo(name, isDone);
    }

    @Override
    public ToDo complete() {
        return new ToDo(this.name, true);
    }

    @Override
    public ToDo incomplete() {
        return new ToDo(this.name, false);
    }

    @Override
    public String serialize() {
        return "T|" + name + "|" + isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
