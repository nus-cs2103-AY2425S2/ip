package laffy.tasklist.tasks;

import java.util.ArrayList;

/**
 * Represents a todo task in the task list.
 */
public class ToDo extends Task {
    private static final String TYPE = "T";

    public ToDo(String desc) {
        super(desc);
    }

    public ToDo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString();
    }

    @Override
    public ArrayList<String> toTaskData() {
        ArrayList<String> taskData = super.toTaskData();
        taskData.set(0, TYPE);
        return taskData;
    }

}
