package taskmax.storage;

import taskmax.exception.TaskmaxException;

import taskmax.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) throws TaskmaxException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskmaxException("Please enter a number of an existing task so I can find it in the list!");
        }
        return tasks.remove(index);
    }

    public void markTask(int index, boolean isDone) throws TaskmaxException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskmaxException("Please enter a number of an existing task so I can find it in the list!");
        }
        if (isDone) {
            tasks.get(index).markAsDone();
        } else {
            tasks.get(index).markAsNotDone();
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task get(int index) throws TaskmaxException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskmaxException("Please enter a number of an existing task so I can find it in the list!");
        }
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
