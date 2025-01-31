package Aquadem;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
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

    public void remove(int i){
        this.tasks.remove(i);
    }

    public TaskList findTask(String s) {
        TaskList found = new TaskList();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.toString().contains(s)) {
                found.add(t);
            }
        }
        return found;
    }

    



}
