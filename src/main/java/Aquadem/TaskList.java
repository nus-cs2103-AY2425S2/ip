package Aquadem;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<Task>();
    public TaskList() {

    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public int size() {
        return this.tasks.size();
    }

    public void add(Task t) {
        this.tasks.add(t);
    }





}
