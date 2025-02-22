package marshmallow.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addTask_todo_success() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("test"));
        assertEquals(1, tasks.count());
    }

    @Test
    public void count_noTasks_success() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.count());
    }
}
