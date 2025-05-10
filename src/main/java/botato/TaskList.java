package botato;

import java.util.ArrayList;
import java.util.stream.IntStream;

import botato.exception.KeywordNotFoundException;
import botato.task.Task;

/**
 * Manages a list of tasks, providing functionalities to add, delete, update, and display tasks.
 * Interacts with {@link Storage} to load tasks from and save tasks to a file. The class also allows marking
 * tasks as complete or incomplete and provides a method to display all tasks in a formatted manner.
 */
public class TaskList {
    private static ArrayList<Task> taskArrayList = new ArrayList<>();
    public TaskList() {
        taskArrayList = Storage.load();
    }

    /**
     * Return number of tasks.
     *
     * @return number of elements in taskArrayList.
     */
    public static int size() {
        return taskArrayList.size();
    }

    /**
     * Use for automated testing.
     *
     * @param size to initialize ArrayList.
     */
    public static void initialize(int size) {
        taskArrayList.clear();
        for (int i = 0; i < size; i++) {
            taskArrayList.add(new Task());
        }
    }

    /**
     * Adds a specified task to the task list.
     *
     * @param task to add to taskArrayList.
     */
    public void addTask(Task task) {
        taskArrayList.add(task);
    }

    /**
     * Deletes a specified task index from taskArrayList.
     *
     * @param taskNum to delete from task list.
     * @return Task deleted.
     */
    public Task deleteTask(int taskNum) {
        return taskArrayList.remove(taskNum - 1);
    }

    /**
     * Sets a task to completed or incomplete status.
     *
     * @param taskNum to set status.
     * @param status to set task to.
     */
    public String setTaskStatus(int taskNum, boolean status) {
        Task task = taskArrayList.get(taskNum - 1);
        task.complete(status);
        System.out.println((status ? "Good job! You finished this:\n" : "Aww.. Guess you didn't do this yet:\n")
                + task);
        return (status ? "Good job! You finished this:\n" : "Aww.. Guess you didn't do this yet:\n") + task;
    }

    /**
     * Shows the current task list.
     */
    public String show() {
        StringBuilder response = new StringBuilder();
        response.append(taskArrayList.isEmpty() ? "You have no tasks!" : "Here are your current tasks:");
        IntStream.range(0, taskArrayList.size())
                .forEach(i -> response.append("\n").append(i + 1).append(".").append(taskArrayList.get(i)));
        System.out.println(response);
        return response.toString();
    }

    /**
     * Finds a specified keyword among all the tasks in taskArrayList.
     *
     * @param keyword String to search among current tasks.
     */
    public String find(String keyword) {
        ArrayList<Task> temp = new ArrayList<>();
        taskArrayList.forEach(task -> {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                temp.add(task);
            }
        });
        if (temp.isEmpty()) {
            throw new KeywordNotFoundException();
        }
        StringBuilder response = new StringBuilder();
        response.append("Here are your tasks containing the keyword:");
        IntStream.range(0, temp.size())
                .forEach(i -> response.append("\n").append(i + 1).append(".").append(temp.get(i)));
        System.out.println(response);
        return response.toString();
    }

    /**
     * Interacts with {@link Storage} to save taskArrayList to local memory.
     */
    public void save() {
        Storage.save(taskArrayList);
    }
}
