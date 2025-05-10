package kunkka.tasklist;

import java.util.List;

import kunkka.components.Task;
import kunkka.components.KunkkaException;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class Tasklist {
    protected List<Task> tasks;
    
    /**
     * Constructs a Tasklist object.
     */
    public Tasklist() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a Tasklist object with a list of tasks.
     * 
     * @param tasks List of tasks.
     */
    public Tasklist(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the size of the list of tasks.
     * 
     * @return Size of the list of tasks.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the list of tasks.
     * 
     * @return Size of the list of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     * 
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the list of tasks.
     * 
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list of tasks.
     * 
     * @param index Index of the task to be deleted.
     */
    public String deleteTask(int index) {
        System.out.println(index);
        try {
            if (index > tasks.size()) {
                throw new KunkkaException("Error: Invalid task number (Out of range)");
            }
            else if (index <= 0) {
                throw new KunkkaException("Error: Invalid task number (Zero or Negative)");
            }
            else {
                Task task = tasks.remove(index - 1);
                String response = "Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.";
                System.out.println(response);
                return response;

            }
        }
        catch (KunkkaException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Prints the list of tasks.
     */
    public String printTasks() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
            output += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return output;
    }

    /**
     * Finds tasks that contain the keyword.
     * 
     * @param keyword Keyword to search for.
     */
    public String markTaskAsDone(int index) {
        try {
            if (index > tasks.size()) {
                throw new KunkkaException("Error: Invalid task number (Out of range)");
            }
            else if (index <= 0) {
                throw new KunkkaException("Error: Invalid task number (Zero or negative)");
            }
            else {
                tasks.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(index - 1));
                return "Nice! I've marked this task as done:\n  " + tasks.get(index - 1);
            }

        }
        catch (KunkkaException e) {
            System.out.println(e.getMessage());

        }
        return "";
    }

    /**
     * Unmarks a task as done.
     * 
     * @param index Index of the task to be unmarked.
     */
    public String unmarkTaskAsDone(int index) {
        try {
            if (index > tasks.size()) {
                throw new KunkkaException("Error: Invalid task number (Out of range)");
            }
            else if (index <= 0) {
                throw new KunkkaException("Error: Invalid task number (Negative)");
            }
            else {
                tasks.get(index - 1).unmarkAsDone();
                System.out.println("Nice! I've unmarked this task:");
                System.out.println("  " + tasks.get(index - 1));
                return "Nice! I've unmarked this task:\n  " + tasks.get(index - 1);
            }
        }
        catch (KunkkaException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

}
