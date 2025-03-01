package bebop.task;
import java.util.ArrayList;

/**
 * TaskList to store all the tasks.
 */

public class TaskList {
    private final ArrayList<Task> tasks;
    private int size;

    /**
     * TaskList constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        size = 0;
    }

    /**
     * Prints all the task in TaskList.
     */
    public String printAllTask() {
        StringBuilder output = new StringBuilder();
        int i = 0;
        for (Task task : tasks) {
            output.append(i + 1).append(" ").append(task.printTask()).append("\n");
            i++;
        }
        return output.toString();
    }

    public void setTaskDone(int index) {
        this.tasks.get(index).markDone();
        assert this.tasks.get(index).isDone;
    }

    public void setTaskNotDone(int index) {
        this.tasks.get(index).unmarkDone();
        assert !this.tasks.get(index).isDone;
    }

    public void printTask(int index) {
        this.tasks.get(index).printTask();
    }

    /**
     * Adds Task to the taskList.
     *
     * @param t task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
        size++;
    }

    /**
     * Get Task of the specified index.
     *
     * @param index index of task.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes Task of the specified index.
     *
     * @param index index of task.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
        size--;
    }

    /**
     * Returns size of the TaskList.
     *
     * @return size of the TaskList.
     */
    public int size() {
        return this.size;
    }

    /**
     * Finds the Task
     *
     * @param taskName name of the Task.
     */
    public String findTask(String taskName) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(taskName)) {
                output.append(tasks.get(i).printTask()).append("\n");
            }
        }
        if (output.isEmpty()) {
            return "Task not found";
        }
        return output.toString();
    }
}
