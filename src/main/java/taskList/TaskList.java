package taskList;

import task.Deadlines;
import task.Events;
import task.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import command.Section;
import java.time.format.DateTimeParseException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    /**
     * Prints all tasks in the task list with their respective indices.
     */
    public void printList(){
        for(int i = 0; i < tasks.size(); i++){
            int s = i + 1;
            System.out.println(s + "." + tasks.get(i).toString());
        }
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param i The index of the task to be marked as done.
     */
    public void mark(int i){
        Task c = getTask(i);
        c.markAsDone();
    }

    /**
     * Marks a task at the specified index as undone.
     *
     * @param i The index of the task to be marked as undone.
     */
    public void unmark(int i){
        Task c = getTask(i);
        c.markAsIncomplete();
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param i The index of the task to be deleted.
     */
    public void delete(int i){
        tasks.remove(i);
    }

    /**
     * Adds a task to the ArrayList.
     *
     * @param t The task to add to the list.
     */
    public void addTask(Task t){
        tasks.add(t);
    }

    /**
     * Gets a task at the specified index.
     *
     * @param i The index of the task to be obtain.
     */
    public Task getTask(int i){
        return tasks.get(i);
    }

    /**
     * Returns the length of the list
     *
     * @return The length of the list.
     */
    public int size(){
        return tasks.size();
    }

    /**
     * Returns the string of the tasks to be stored in the text file.
     *
     * @return String of the tasks.
     */
    public String printListForStorage(){
        String s = "";
        for (Task task : tasks) {
            s = s + task.toString() + "\n";
        }
        return s;
    }

    /** Return all Task with the word given by the user
     *
     * @param des The word given by the user.
     * @return ArrayList with the tasks that contain that des.
     */
    public ArrayList<Task> getTasks(String des){
        ArrayList<Task> aptTask = new ArrayList<>();
        for (Task task : tasks) {
            if(task.getDescription().contains(des)){
                aptTask.add(task);
            }
        }
        return aptTask;
    }

    public void updateTask(Section section, int i, String update) {
        try {
            Task t = getTask(i);
            assert t != null : "Task at index " + i + " should not be null";

            if (section == Section.DES) {
                t.updateDes(update);

            } else if (section == Section.BY) {
                if (t instanceof Deadlines) {
                    try {
                        LocalDateTime by = LocalDateTime.parse(update.replace(" ", "T") + ":00");
                        ((Deadlines) t).updateBy(by);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format for deadline. Use YYYY-MM-DD HH:MM.");
                    }
                } else {
                    System.out.println("Error: Cannot update 'BY' for non-deadline task.");
                }

            } else if (section == Section.TO) {
                if (t instanceof Events) {
                    try {
                        LocalDateTime to = LocalDateTime.parse(update.replace(" ", "T") + ":00");
                        ((Events) t).updateTo(to);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format for event 'TO'. Use YYYY-MM-DD HH:MM.");
                    }
                } else {
                    System.out.println("Error: Cannot update 'TO' for non-event task.");
                }

            } else if (section == Section.FROM) {
                if (t instanceof Events) {
                    try {
                        LocalDateTime from = LocalDateTime.parse(update.replace(" ", "T") + ":00");
                        ((Events) t).updateFrom(from);
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date format for event 'FROM'. Use YYYY-MM-DD HH:MM.");
                    }
                } else {
                    System.out.println("Error: Cannot update 'FROM' for non-event task.");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: Invalid task index.");
        } catch (NullPointerException e) {
            System.out.println("Error: Task not found.");
        }
    }

}
