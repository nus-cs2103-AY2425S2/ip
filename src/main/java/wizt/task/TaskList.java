package wizt.task;

import java.util.ArrayList;
/**
 *  Represents a list of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {

    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return Arraylist of all tasks in the tasklist
     */
    public ArrayList<Task> getTasksList() {
        return tasks;
    }
}
