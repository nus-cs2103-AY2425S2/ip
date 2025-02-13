package zoe.task;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;

/**
 * Represents a list of tasks that extends the ArrayList class.
 * Provides additional constructors to initialize the list with
 * existing task data.
 */
@NoArgsConstructor
public class TaskList extends ArrayList<Task> {
    public TaskList(List<Task> list) {
        super(list);
    }
}
