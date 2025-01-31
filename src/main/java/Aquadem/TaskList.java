package Aquadem;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class to represent the list of tasks in the chatbot
 */
public class TaskList implements Serializable {
    protected ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Default constructor for tasklist - creates an empty tasklist
     */
    public TaskList() {

    }

    /**
     * Constructor for a tasklist that loads a given tasklist through an arrayList
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves task at given position
     * @param i
     * @return
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Returns the size of the tasklist
     * @return
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds task to list
     * @param t
     */

    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Remvoes task from list
     * @param i
     */
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
