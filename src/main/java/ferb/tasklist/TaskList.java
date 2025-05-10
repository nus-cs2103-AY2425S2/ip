package ferb.tasklist;

import ferb.exception.FerbException;
import ferb.task.*;
import java.util.ArrayList;
import java.time.LocalDate;


/**
 * Represents a list of tasks.
 */
public class TaskList extends ArrayList<Task> {


    public TaskList() {
        super();
    }

    /**
     * Constructs a task list from the specified content.
     *
     * @param content the content of the task list
     */
    public TaskList(String content) throws FerbException {
        String[] lines = content.split("\\R");

        for (String line : lines) {
            String[] fields = line.split("\\|");

            if (fields.length == 3) {
                this.processTodo(fields);
            } else if (fields.length == 4) {
                this.processDeadline(fields);
            } else if (fields.length == 5) {
                this.processEvent(fields);
            } else {
                throw new FerbException("Invalid task format in file.");
            }
        }
    }

    private void processTodo(String[] fields) throws FerbException {
        if (!fields[0].equals("T")) {
            throw new FerbException("Invalid task format in file.");
        }
        this.add(new ToDo(fields[1].equals("1") ? true : false, fields[2]));
    }

    private void processDeadline(String[] fields) throws FerbException {
        if (!fields[0].equals("D")) {
            throw new FerbException("Invalid task format in file.");
        }
        this.add(new Deadline(fields[1].equals("1") ? true : false, fields[2], fields[3]));
    }

    private void processEvent(String[] fields) throws FerbException {
        if (!fields[0].equals("E")) {
            throw new FerbException("Invalid task format in file.");
        }
        this.add(new Event(fields[1].equals("1") ? true : false, fields[2], fields[3], fields[4]));
    }


    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword the keyword to search for
     * @return a task list containing tasks that contains the keyword
     */
    public TaskList find(String keyword) {
        TaskList result = new TaskList();
        for (Task task : this) {
            if (task.contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return a string representation of the task list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            result.append(i + 1).append(". ").append(this.get(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Sorts the tasks in the list by their descriptions in lexicographical order.
     */
    public void sortDescription() {
        this.sort((t1, t2) -> t1.taskDescription().compareTo(t2.taskDescription()));
    }

    /**
     * Sorts the tasks in the list chronologically by their deadline dates.
     */
    public void sortDate() {
        quickSort(0, this.size() - 1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        Task pivot = this.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (compareDates(this.get(j), pivot) <= 0) {
                i++;
                Task temp = this.get(i);
                this.set(i, this.get(j));
                this.set(j, temp);
            }
        }
        Task temp = this.get(i + 1);
        this.set(i + 1, this.get(high));
        this.set(high, temp);
        return i + 1;
    }

    private int compareDates(Task t1, Task t2) {
        if (t1.isTodo()) {
            return -1;
        } else if (t2.isTodo()) {
            return 1;
        }
        LocalDate d1, d2;
        if (t1 instanceof Deadline && t2 instanceof Deadline) {
            d1 = ((Deadline) t1).getDeadline();
            d2 = ((Deadline) t2).getDeadline();
        } else if (t1 instanceof Event && t2 instanceof Event) {
            d1 = ((Event) t1).getStartDate();
            d2 = ((Event) t2).getStartDate();
        } else if (t1 instanceof Deadline) {
            d1 = ((Deadline) t1).getDeadline();
            d2 = ((Event) t2).getStartDate();
        } else {
            d1 = ((Event) t1).getStartDate();
            d2 = ((Deadline) t2).getDeadline();
        }
        return d1.compareTo(d2);
    }
}
