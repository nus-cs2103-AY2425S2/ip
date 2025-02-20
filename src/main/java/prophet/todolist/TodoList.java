package prophet.todolist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import prophet.task.DeadlineTask;
import prophet.task.EventTask;
import prophet.task.Task;

/**
 * The TodoList class represents a list of tasks that the user has to do.
 */
public class TodoList {

    private Stream<Task> taskStream;

    /**
     * Initialises a newly created TodoList object with an empty list.
     */
    public TodoList() {
        this.taskStream = Stream.empty();
    }

    /**
     * Adds the input task to the list of things to do.
     * @param newTask the task description
     * @return the confirmation string that indicates successful addition
     */
    public String addToList(Task newTask) {
        if (newTask == null) {
            return "Please.. don't make me work harder than I have to!\n";
        }
        this.taskStream = Stream.concat(this.taskStream, Stream.of(newTask));
        // assert this.taskStream.findAny().isPresent() : "List should never be empty";
        return "Task added to list: " + newTask;
    }

    /**
     * Returns a string of the whole to-do list and its status.
     * @return the string that represents the to-do list
     */
    public String enumerateList() {
        StringBuilder result = new StringBuilder("Let's see what you have on your plate:\n");
        List<Task> taskList = this.taskStream.toList();
        for (int i = 0; i < taskList.size(); i++) {
            result.append(i + 1).append(". ").append(taskList.get(i).toString());
        }
        if (taskList.isEmpty()) {
            result.append("So gewd ah.. nothing to do!\n");
        }
        if (taskList.size() > 4) {
            result.append("Seems like you have quite a few things to do.. go easy on yourself!\n");
        }
        this.taskStream = taskList.stream();
        return result.toString();
    }

    /**
     * Returns a string of the whole to-do list and its status for saving purposes.
     * @return the string that represents the to-do list
     */
    public String enumerateSaveList() {
        List<Task> taskList = this.taskStream.toList();
        StringBuilder result = new StringBuilder();
        for (Task task : taskList) {
            result.append(task.toString());
        }
        this.taskStream = taskList.stream();
        return result.toString();
    }

    /**
     * Marks a specified task done. The specified task number cannot be larger than the list size or negative.
     * @param taskNumber the task to be marked done
     * @return the string that confirms successful marking of the task
     * @throws IndexOutOfBoundsException if the task number is invalid
     */
    public String setDone(int taskNumber) throws IndexOutOfBoundsException {
        try {
            List<Task> taskList = this.taskStream.toList();
            taskList.get(taskNumber).setDone();
            StringBuilder result = new StringBuilder("Marked done! Good job.\n");
            result.append(taskList.get(taskNumber).getStatusIcon())
                    .append(taskList.get(taskNumber).getTaskDescription()).append("\n");
            this.taskStream = taskList.stream();
            return result.toString();
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Marks a specified task not done. The specified task number cannot be larger than the list size or negative.
     * @param taskNumber the task to be marked not done
     * @return the string that confirms successful marking of the task
     * @throws IndexOutOfBoundsException if the task number is invalid
     */
    public String setNotDone(int taskNumber) throws IndexOutOfBoundsException {
        try {
            List<Task> taskList = this.taskStream.toList();
            taskList.get(taskNumber).setNotDone();
            StringBuilder result = new StringBuilder("Marked not done! Jiayous...\n");
            result.append(taskList.get(taskNumber).getStatusIcon())
                    .append(taskList.get(taskNumber).getTaskDescription()).append("\n");
            this.taskStream = taskList.stream();
            return result.toString();
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Removes the input task from the list of things to do.
     * @param  taskNumber the task description
     * @return the confirmation string that indicates successful removal
     * @throws IndexOutOfBoundsException if the task number is invalid
     */
    public String deleteTask(int taskNumber) throws IndexOutOfBoundsException {
        try {
            List<Task> taskList = new ArrayList<>(this.taskStream.toList());
            Task task = taskList.get(taskNumber);
            taskList.remove(taskNumber);
            StringBuilder result = new StringBuilder("The following task was deleted: \n");
            result.append(task.getStatusIcon())
                    .append(task.getTaskDescription()).append("\n");
            this.taskStream = taskList.stream();
            return result.toString();
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
        StringBuilder result = new StringBuilder("Here are the tasks that match: " + keyword + "\n");
        List<Task> taskList = this.taskStream.toList();
        for (Task task : taskList) {
            if (task.getTaskDescription().contains(keyword)) {
                result.append(task.toString());
            }
        }
        this.taskStream = taskList.stream();
        return result.toString();
    }

    /**
     * Returns the size of the list.
     * @return the size of the list
     */
    public int getListSize() {
        List<Task> taskList = this.taskStream.toList();
        int listSize = taskList.size();
        this.taskStream = taskList.stream();
        return listSize;
    }

    /**
     * Searches through all existing tasks that match the {@link LocalDate} provided
     * @param date
     * @return
     */
    public String findTasksByDate(LocalDate date) {
        StringBuilder result = new StringBuilder("Here are the tasks that are on : " + date + "\n");
        List<Task> taskList = this.taskStream.toList();
        taskList.stream().filter(
                c -> c.getClass() == DeadlineTask.class || c.getClass() == EventTask.class)
                         .filter(c -> c.isDueOn(date))
                         .sorted((a, b) -> a.getDueDateTime().compareTo(b.getDueDateTime()))
                         .forEach(c -> result.append(c.toString()));
        this.taskStream = taskList.stream();
        return result.toString();
    }
}
