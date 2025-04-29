/**
 * Represents the list that contains all tasks the user has put
 * The tasklist is represented by an arraylist
 * and various operations can be done to modify the tasklist
 */

package lemon;

import java.util.ArrayList;

public class Tasklist {
    private final ArrayList<Task> tasks;

    /**
     * Construct a tasklist with a specified arraylist of tasks
     * @param tasks The arraylist of tasks
     */
    public Tasklist(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Add a task to the tasklist
     * @param task The task to be added
     */
    public String addTask(Task task) {
        tasks.add(task);
        StringBuilder sb = new StringBuilder();
        sb.append("\uD83D\uDC4C Got it. I've added this task:").append("\n");
        sb.append(" " + task.toString()).append("\n");
        if (tasks.size() == 1) {
            sb.append("Now you have 1 task in the list.");
        } else {
            sb.append("Now you have " + tasks.size() + " tasks in the list.");
        }
        return sb.toString();
    }


    /**
     * Delete a task from the tasklist
     * @param index The index of the task in the tasklist to be deleted
     */
    public String deleteTask(int index) {
        assert index > 0 && index <= tasks.size(): "index of the task should be valid";
        StringBuilder sb = new StringBuilder();
        sb.append("\uD83D\uDC4C Noted. I've removed this task:").append("\n");
        sb.append(tasks.get(index - 1).toString()).append("\n");
        tasks.remove(index - 1);
        sb.append("Now you have " + tasks.size() + " tasks in the list.");
        return sb.toString();
    }


    /**
     * Mark a task as done
     * @param index The index of the task in the tasklist that is to be marked as done
     */
    public String markTask(int index) {
        assert index > 0 && index <= tasks.size(): "index of the task should be valid";
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:").append("\n");
        tasks.get(index - 1).markDone();
        sb.append(tasks.get(index - 1).toString());
        return sb.toString();

    }


    /**
     * Mark a task as not done
     * @param index The index of the task in the tasklist that is to be marked as not done
     */
    public String unmarkTask(int index) {
        assert index > 0 && index <= tasks.size(): "index of the task should be valid";
        StringBuilder sb = new StringBuilder();
        sb.append("\uD83D\uDC4C OK, I've marked this task as not done yet").append("\n");
        tasks.get(index - 1).markUndone();
        sb.append(tasks.get(index - 1).toString());
        return sb.toString();
    }


    /**
     * Find all tasks with the given keyword
     * @param keyword The keyword which the tasks you want to find contains
     * @return The tasks that contain the given keyword
     */
    public String findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        if(foundTasks.size() == 0) {
            return "\uD83E\uDD14 Oh no! There is no task with this keyword.";
        } else {
            StringBuilder sb = new StringBuilder();
            int index = 1;
            for(Task task : foundTasks) {
                sb.append(index + ". " + task.toString()).append("\n");
                index++;
            }
            return sb.toString();
        }
    }

    public String listTask() {
        if(tasks.isEmpty()) {
            return "There are no tasks in the list yet!";
        } else {
            StringBuilder sb = new StringBuilder();
            for (Task task : tasks) {
                sb.append(task.toString()).append("\n");
            }
            return sb.toString();
        }

    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

}