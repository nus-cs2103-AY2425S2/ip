package paimon.tasklist;

import java.util.ArrayList;

import paimon.items.Todo;

/**
 * TaskList class that stores the Todo tasks. 
 * Aslo can store Deadline and Event tasks. 
 */
public class TaskList implements Iterable<Todo> {
    private ArrayList<Todo> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    public Todo get(int index) {
        return this.items.get(index);
    }

    /**
     * Adds an item to the list
     * 
     * @param task
     */
    public void add(Todo task) {
        this.items.add(task);
    }

    /**
     * Removes an item from the list
     * 
     * @param index index of the item to be removed, 0 indexed
     * @return the item that was removed
     */
    public Todo remove(int index) {
        return this.items.remove(index);
    }

    /**
     * Gets the number of items in the list
     * 
     * @return number of items in the list
     */
    public int size() {
        return this.items.size();
    }

    /**
     * Marks an item as done
     * 
     * @param index index of the item to be marked as done, 0 indexed
     */
    public void mark(int index) {
        this.items.get(index).mark();
    }

    /**
     * Unmarks an item as done
     * 
     * @param index index of the item to be unmarked, 0 indexed
     */
    public void unmark(int index) {
        this.items.get(index).unmark();
    }

    /**
     * Finds tasks that contain the keyword
     * 
     * @param keyword the keyword to search for
     * @return a TaskList only containing the tasks that contain the keyword
     */
    public TaskList find(String keyword) {
        TaskList found = new TaskList();
        for (Todo task : this.items) {
            if (task.getDescription().contains(keyword)) {
                found.add(task);
            }
        }
        return found;
    }

    /**
     * Lists all the current items in the list and outputs to screen. 
     */
    public void list_items() {
        if (this.items.size() == 0) {
            System.out.println("Empty list!");
        } else {
            for (int i = 0; i < this.items.size(); i++) {
                Todo task = this.items.get(i);
                System.out.printf("%d. %s%n", i + 1, task);
            }
        }
        System.out.println();
    }

    /**
     * Lists all the current items in the list and outputs to screen.
     */
    public String list_items_toString() {
        StringBuilder sb = new StringBuilder();
        if (this.items.size() == 0) {
            sb.append("Empty list!\n");
        } else {
            for (int i = 0; i < this.items.size(); i++) {
                Todo task = this.items.get(i);
                sb.append(String.format("%d. %s%n", i + 1, task));
            }
        }
        return sb.toString();
    }

    /**
     * Inserts a task at the given index
     * 
     * @param task the task to be inserted
     * @param index the index to insert the task at
     */
    public void insert(Todo task, int index) {
        this.items.add(index, task);
    }

    @Override
    public java.util.Iterator<Todo> iterator() {
        return this.items.iterator();
    }
}
