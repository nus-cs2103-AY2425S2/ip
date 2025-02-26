package Krypto.Utils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Krypto.Exceptions.KryptoExceptions;
import Krypto.Task.Task;
import Krypto.IO.GUI;
public class TaskList {
    private ArrayList<Task> taskList;
    private GUI gui;
    private int len;

    /**
     * Initializes a TaskList with an existing list of tasks and a GUI object.
     *
     * @param tasks    The list of tasks.
     * @param guiObject The UI object for user interaction.
     */
    public TaskList(ArrayList<Task> tasks, GUI guiObject) {
        taskList = tasks;
        gui = guiObject;
        len = tasks.size();
    }

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {}

    /**
     * Prints the list of tasks.
     */
    public void printList() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < len; i++) {
            sb.append(String.format("%d. %s\n", i + 1, taskList.get(i)));
        }
        gui.newResponse(sb.toString());
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The length of the task list.
     */
    public int getLength() {
        return len;
    }

    /**
     * Prints tasks that match the specified date.
     *
     * @param date The date to filter tasks by.
     */
    public void printShowList(String date) {
        StringBuilder sb = new StringBuilder();
        LocalDate queryDate = LocalDate.parse(date);
        boolean found = false;
        sb.append(String.format("Fetching your tasks on %s\n",
                queryDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))));
        for (int i = 0; i < len; i++) {
            Task t = taskList.get(i);
            if(t.onThisDay(date)) {
                sb.append(String.format("%d. %s\n", i + 1, t));
                found = true;
            }
        }
        if (!found) {
            sb.append(String.format("You don't have any tasks on %s", date));
        }
        gui.newResponse(sb.toString());
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        assert t != null : "Task should not be null";
        taskList.add(t);
        len++;
        gui.addTaskResponse(t, len);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     */
    public Task getTask(int index) throws KryptoExceptions {
        if(index >= len || index < 0) {
            throw new KryptoExceptions("Invalid id supplied.");
        }
        return taskList.get(index);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) throws KryptoExceptions {
        if(index >= len || index < 0) {
            throw new KryptoExceptions("Invalid deletion id supplied.");
        }
        Task t = taskList.remove(index);
        len--;
        gui.deleteTaskResponse(t, len);
    }

    /**
     * Displays tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for within task descriptions.
     */
    public void getTasksWithKeyword(String keyword) {
        StringBuilder sb = new StringBuilder();
        boolean isFound = false;
        sb.append(String.format("Looking for tasks with %s\n", keyword));
        for(int i = 0; i < len; i ++) {
            Task task = taskList.get(i);
            if(task.hasKeyword(keyword)) {
                sb.append(String.format("%d. %s\n", i + 1, task));
                isFound = true;
            }
        }
        if(!isFound) {
            sb.append(String.format("No matches found for " + keyword));
        }
        gui.newResponse(sb.toString());
    }
}
