package tooth.stuff;

import java.util.ArrayList;
import java.util.function.Consumer;

import tooth.exception.InvalidParamException;
import tooth.task.Task;

/**
 * The list storing all the main tasks
 */
public class TaskList {

    private final ArrayList<Task> memory = new ArrayList<>();

    public TaskList() {}

    /**
     * Adds a task to the list
     *
     * @param item the task to be added to the list
     */
    public void add(Task item) {
        memory.add(item);
    }

    /**
     * DeLetes a task from the list
     *
     * @param i the index of the task to delete
     * @throws InvalidParamException thrownm if the index specified does not exist
     */
    public void delete(int i) throws InvalidParamException {
        if (i > memory.size() - 1) {
            throw new InvalidParamException("Index " + i + " is out of range");
        } else {
            memory.remove(i);
        }
    }

    /**
     * Clears list
     */
    public void clear() {
        memory.clear();
    }

    /**
     * Marks a task from the list as completed
     *
     * @param i the index of the task to mark
     * @throws InvalidParamException thrown if the index specified does not exist
     */
    public void mark(int i) throws InvalidParamException {
        if (i > memory.size() - 1) {
            throw new InvalidParamException("Index " + i + " is out of range");
        } else {
            Task item = memory.get(i).complete();
            memory.set(i, item);
        }
    }

    /**
     * Unmarks a task from the list as incompleted
     *
     * @param i the index of the task to unmark
     * @throws InvalidParamException thrown if the index specified does not exist
     */
    public void unmark(int i) throws InvalidParamException {
        if (i > memory.size() - 1) {
            throw new InvalidParamException("Index " + i + " is out of range");
        } else {
            Task item = memory.get(i).incomplete();
            memory.set(i, item);
        }
    }

    /**
     * Applies function to all item in the list
     *
     * @param action the function to map the task
     */
    public void forEach(Consumer<? super Task> action) {
        memory.forEach(action);
    }

    /**
     * Counts number of tasks
     */
    public int numTask() {
        return memory.size();
    }
}
