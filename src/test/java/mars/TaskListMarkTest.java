package mars;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import mars.task.TaskList;
import mars.task.Task;
import mars.task.Todo;

import java.util.ArrayList;

public class TaskListMarkTest {
    @Test
    public void mark_success(){
        Task task = new Todo("run" , true);
        String line = "[T][ ] run";
        ArrayList<String> lst = new ArrayList<>();
        lst.add(line);
        TaskList taskList = new TaskList(lst);
        taskList.mark(0);
        Task markedTask = taskList.get(0);
        assertEquals(task.toString(), markedTask.toString());
    }
}
