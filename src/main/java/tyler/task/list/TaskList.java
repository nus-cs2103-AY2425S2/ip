package tyler.task.list;

import java.util.ArrayList;

import tyler.task.Task;
import tyler.ui.Ui;

public class TaskList extends ArrayList<Task> {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.addAll(new ArrayList<>());
    }

    public TaskList(TaskList tasks) {
        this.addAll(tasks);
    }

    /**
     * Adds task to array passed in as argument at specified index.
     * Returns the index of the next position to add a task.
     *
     * @param task Task to be added.
     *
     */
    public void addToList(Task task, Ui ui) {
        try {
            if (task.getDescription().isBlank()) {
                throw new IllegalArgumentException("\t !!Please add a description for the task!!");
            }
            this.add(task);
            ui.showMessage("\t I've added: \n\t\t" + task + "\n"
                    + "\t There's now " + this.size() + " task(s) in the list.");
        } catch (IllegalArgumentException e) {
            ui.showMessage("\t !!Please add a description for the task!!");
        }
    }

    public boolean isDuplicate(Task task) {
        for (Task t : this) {
            if (task.equals(t)) {
                return true;
            }
        }
        return false;
    }
}
