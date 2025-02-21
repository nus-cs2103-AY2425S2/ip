package grass;

import java.util.ArrayList;


/**
* stores list of tasks
* 
* @author gn07
* 
*/
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * <p>add task to task list</p>
     * @param task task object to be added to task list
     * @since 1.0
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * <p>gest task in task list by index</p>
     * @param index list index of task object to retrieve
     * @return task object at index
     * @since 1.0
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * <p>delete task from task list</p>
     * @param index list index of task object to be deleted from task list
     * @since 1.0
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * <p>get number of tasks in last list</p>
     * @return number of task objects in task list
     * @since 1.0
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * <p>get full task list</p>
     * @return task list
     * @since 1.0
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * <p>updates task list to new state</p>
     * @param tasks new updated task list
     * @since 1.0
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * <p>check if task list is emptyt</p>
     * @return boolean value indicating if task list is empty
     * @since 1.0
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public boolean inList(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).equals(task)) {
                return true;
            }
        }
        return false;
    }

}