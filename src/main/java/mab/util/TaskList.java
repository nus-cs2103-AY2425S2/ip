package mab.util;

import java.util.ArrayList;

import mab.task.Task;

/**
 * Container for managing task collections.
 * Wraps underlying ArrayList implementation.
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Initializes with existing task collection.
     * 
     * @param t Initial tasks (direct reference, not copied)
     */
    public TaskList(ArrayList<Task> t){
        tasks = t; 
    }

     /**
     * @return Reference to underlying task collection
     */
    public ArrayList<Task> getTasks(){
        return tasks;
    }
}
