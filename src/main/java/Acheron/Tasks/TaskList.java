package Acheron.Tasks;

import java.util.ArrayList;

import Acheron.Exceptions.InvalidDeleteExceptions;
import Acheron.Ui.Ui;

/**
 * Represents the an object that stores all tasks
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task
     * @param task A task to be added
     */
    public void addTask(Task task) {
        addTaskFromStorage(task);
        Ui.displayText("Got it. I've added this task:\n"
                + task
                + "\n"
                + "Now you have " + tasks.size()
                + " tasks in the list.");
    }

    /**
     * Adds a task based on data in the saved file. A distinction is needed so the text displayed
     * is different
     * @param task A task to be added
     */
    public void addTaskFromStorage(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the ith task
     * @param i The position of the task in the array list
     */
    public void removeTask(int i) throws Exception {
        try {
            Task removeTask = tasks.get(i);
            tasks.remove(i);
            Ui.displayText("Noted. I've removed this task:\n"
                    + removeTask
                    + "\n"
                    + "Now you have " + tasks.size()
                    + " tasks in the list.");
        } catch (Exception e) {
            throw new InvalidDeleteExceptions();
        }
    }

    /**
     * Marks the ith task
     * @param i The position of the task in the array list
     */
    public void markTask(int i) {
        tasks.get(i).mark();
        Ui.displayText("Nice! I've marked this task as done:\n" + tasks.get(i));
    }

    /**
     * Unmarks the ith task
     * @param i The position of the task in the array list
     */
    public void unmarkTask(int i) {
        tasks.get(i).unmark();
        Ui.displayText("OK, I've marked this task as not done yet:\n" + tasks.get(i));
    }

    /**
     * Overrides the to string method with a custom version
     * @return A string format of  the task list
     */
    @Override
    public String toString() {
        String listOfTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i < tasks.size() - 1) {
                listOfTasks += String.format("%d. %s\n", i + 1, tasks.get(i));
            } else {
                listOfTasks += String.format("%d. %s", i + 1, tasks.get(i));
            }
        }
        return "Here are the tasks in your list:\n" + listOfTasks;
    }

    /**
     * Extracts out all the contents of the existing tasks
     * @return All the contents of the existing tasks
     */
    public String getAllTasksContent() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String savedContent = tasks.get(i).saveTask(i == tasks.size() - 1);
            stringBuilder.append(savedContent);
        }
        return stringBuilder.toString();
    }

    /**
     * Finds all tasks with the same keyword
     * @param keyword The keyword we are interested
     */
    public void findAllTaskWithKeyword(String keyword) {
        ArrayList<Task> rightTask = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.containsKeyword(keyword)) {
                rightTask.add(task);
            }
        }
        String listOfTasks = "";
        for (int i = 0; i < rightTask.size(); i++) {
            if (i < rightTask.size() - 1) {
                listOfTasks += String.format("%d. %s\n", i + 1, rightTask.get(i));
            } else {
                listOfTasks += String.format("%d. %s", i + 1, rightTask.get(i));
            }
        }
        Ui.displayText("Here are the matching tasks in your list:\n" + listOfTasks);
    }
}
