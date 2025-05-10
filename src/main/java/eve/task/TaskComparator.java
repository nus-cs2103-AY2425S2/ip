package eve.task;

import java.util.Comparator;

/**
 * Comparator to compare between two tasks based on date and time.
 * Tasks without date and time specified will always be higher order.
 * (displayed behind)
 */
public class TaskComparator implements Comparator<Task> {
    /**
     * Method to compare between two tasks.
     */
    public int compare(Task t1, Task t2) {
        if (t1 instanceof ToDo todo && t2 instanceof ToDo todo2) {
            return 0;
        }
        if (t1 instanceof ToDo todo) {
            return 1;
        }
        if (t2 instanceof ToDo todo) {
            return -1;
        }
        return t1.getDateTime().compareTo(t2.getDateTime());
    }
}
