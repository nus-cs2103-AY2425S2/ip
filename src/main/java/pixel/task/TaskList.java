package pixel.task;

import java.util.ArrayList;

import pixel.util.PixelException;

/**
 * Represents a task list used to store and manage tasks from the user.
 */
public class TaskList {
    private final ArrayList<Task> contents = new ArrayList<>(100);

    /**
     * Returns the size of the list, which is the number of tasks in the list.
     *
     * @return Size of the list of tasks
     */
    public int getListSize() {
        return this.contents.size();
    }

    /**
     * Returns the String representation of the contents of the TaskList,
     * namely the type, completion status, and description of each task.
     *
     * @return String representation of tasks in TaskList
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= this.contents.size(); i++) {
            String curr = i + ". " + this.contents.get(i - 1) + "\n";
            out.append(curr);
        }
        return out.toString();
    }

    /**
     * Adds the provided task to the list of tasks, then returns the Task.
     *
     * @param task Task to be added
     * @return The Task which was added
     */
    public Task addTask(Task task) {
        int originalSize = this.contents.size();
        this.contents.add(task);
        assert this.contents.size() == originalSize + 1 : "Size of taskList should have incremented by 1";
        return task;
    }

    /**
     * Marks the Task at the provided index as complete, then returns the Task.
     *
     * @param idx The index at which the Task to be marked is stored in the list
     * @return The Task which was marked
     * @throws PixelException If the index provided does not correspond to a Task
     */
    public Task markTask(int idx) throws PixelException {
        boolean indexOutOfRange = idx < 0 || idx >= this.contents.size();
        if (indexOutOfRange || this.contents.get(idx) == null) {
            throw new PixelException("Please input a valid task number!");
        }
        this.contents.get(idx).markTask();
        assert this.contents.get(idx).isDone() : "Task should be marked as done";
        return this.contents.get(idx);
    }

    /**
     * Marks the Task at the provided index as incomplete, then returns the Task.
     *
     * @param idx The index at which the Task to be marked is stored in the list
     * @return The Task which was marked
     * @throws PixelException If the index provided does not correspond to a Task
     */
    public Task unmarkTask(int idx) throws PixelException {
        boolean indexOutOfRange = idx < 0 || idx >= this.contents.size();
        if (indexOutOfRange || this.contents.get(idx) == null) {
            throw new PixelException("Please input a valid task number!");
        }
        this.contents.get(idx).unmarkTask();
        assert !this.contents.get(idx).isDone() : "Task should be marked as not done";
        return this.contents.get(idx);
    }

    /**
     * Removes the Task at the provided index from the list, then returns the Task.
     *
     * @param idx The index at which the Task to be removed is stored in the list
     * @return The Task which was removed
     * @throws PixelException If the index provided does not correspond to a Task
     */
    public Task deleteTask(int idx) throws PixelException {
        boolean indexOutOfRange = idx < 0 || idx >= this.contents.size();
        if (indexOutOfRange || this.contents.get(idx) == null) {
            throw new PixelException("Please input a valid task number!");
        }
        return this.contents.remove(idx);
    }

    public ArrayList<Task> searchTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.contents) {
            if (task.desc.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public ArrayList<Task> clearMarked() {
        ArrayList<Task> clearedTasks = new ArrayList<>();
        for (int i = this.contents.size() - 1; i >= 0; i--) {
            if (this.contents.get(i).isDone()) {
                clearedTasks.add(this.contents.get(i));
                this.contents.remove(i);
            }
        }
        return clearedTasks;
    }

    public ArrayList<Task> markAll(int from, int to) {
        ArrayList<Task> markedTasks = new ArrayList<>();
        boolean indexOutOfRange = from < 0 || to >= this.contents.size();
        if (indexOutOfRange) {
            throw new PixelException("Please input a valid range of task numbers!");
        }
        for (int i = from; i <= to; i++) {
            this.contents.get(i).markTask();
            markedTasks.add(this.contents.get(i));
        }
        return markedTasks;
    }

    public ArrayList<Task> unmarkAll(int from, int to) {
        ArrayList<Task> unmarkedTasks = new ArrayList<>();
        boolean indexOutOfRange = from < 0 || to >= this.contents.size();
        if (indexOutOfRange) {
            throw new PixelException("Please input a valid range of task numbers!");
        }
        for (int i = from; i <= to; i++) {
            this.contents.get(i).unmarkTask();
            unmarkedTasks.add(this.contents.get(i));
        }
        return unmarkedTasks;
    }

    /**
     * Returns the contents of the TaskList in a format compatible for writing to a file in disk.
     *
     * @return Formatted contents of TaskList
     */
    public String toFileFormat() {
        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= this.contents.size(); i++) {
            out.append(this.contents.get(i - 1).toFileFormat());
            out.append("\n");
        }
        out.append("EOF");
        return out.toString();
    }
}
