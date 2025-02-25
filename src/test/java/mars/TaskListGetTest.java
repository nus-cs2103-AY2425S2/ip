package mars;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import mars.task.TaskList;
import mars.task.Task;
import mars.task.Todo;

import java.util.ArrayList;

public class TaskListGetTest {
    @Test
    public void get_success() {
        Task task = new Todo("buy a book", true);
        String line = "[T][X] buy a book";
        ArrayList<String> lst = new ArrayList<>();
        lst.add(line);
        assertEquals(task.toString(), new TaskList(lst).get(0).toString());
    }
}

