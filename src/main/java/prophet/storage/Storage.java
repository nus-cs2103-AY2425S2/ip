package prophet.storage;

import java.time.LocalDate;

import prophet.task.Task;
import prophet.todolist.TodoList;

/**
 * The Storage class represents the storage of tasks accumulated as the chatbot runs.
 */
public class Storage {

    private final TodoList tasks;

    /**
     * Initialises a newly created Storage object that has a TodoList object.
     */
    public Storage() {
        this.tasks = new TodoList();
    }

    /**
     * Enumerates the list of tasks.
     * @return the enumerated list of tasks
     */
    public String enumerateList() {
        return tasks.enumerateList();
    }

    /**
     * Enumerates the list of tasks for saving purposes.
     * @return the enumerated list of tasks for saving purposes
     */
    public String enumerateSaveList() {
        return tasks.enumerateSaveList();
    }

    /**
     * Marks a task as done.
     * @param taskNumber the task number to be marked as done
     * @return the message that the task has been marked as done or the error message
     */
    public String setDone(int taskNumber) {
        try {
            return tasks.setDone(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Marks a task as not done.
     * @param taskNumber the task number to be marked as not done
     * @return the message that the task has been marked as not done or the error message
     */
    public String setNotDone(int taskNumber) {
        try {
            return tasks.setNotDone(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Adds a to-do task to the list of tasks.
     * @param task the description of the task
     * @return the message that the task has been added to the list
     */
    public String addToList(Task task) {
        return tasks.addToList(task);
    }

    /**
     * Deletes a task from the list of tasks.
     * @param taskNumber the task number to be deleted
     * @return the message that the task has been deleted or the error message
     */
    public String deleteTask(int taskNumber) {
        try {
            return tasks.deleteTask(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Finds tasks that contain the keyword.
     * @param keyword the keyword to search for
     * @return the list of tasks that contain the keyword
     */
    public String findTasks(String keyword) {
        return tasks.findTasks(keyword);
    }

    /**
     * Returns the length of the list of tasks.
     * @return the length of the list of tasks
     */
    public int getListSize() {
        return tasks.getListSize();
    }

    /**
     * Finds tasks that match the {@link LocalDate} given.
     * @param date the date to search for
     * @return the tasks that match the date
     */
    public String findTasksByDate(LocalDate date) {
        return tasks.findTasksByDate(date);
    }
}
