package laffy.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import laffy.tasklist.exceptions.IndexOutOfRange;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void taskList_emptyTaskList_emptyTaskList() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }

    @Test
    public void taskList_addTask_taskListWithTask() {
        TaskList taskList = new TaskList();
        taskList.addTodo("task desc");
        assertEquals(1, taskList.size());
    }

    @Test
    public void taskList_addTask_taskListWithTask2() {
        TaskList taskList = new TaskList();
        taskList.addTodo("task desc");
        assertEquals(1, taskList.size());
        assertEquals("task desc", taskList.toTasksData().get(0).get(2));
    }

    @Test
    public void taskList_deleteTask_taskListWithoutTask() throws IndexOutOfRange {
        TaskList taskList = new TaskList();
        taskList.addTodo("task desc");
        assertEquals(1, taskList.size());
        taskList.delete(0);
        assertEquals(0, taskList.size());
    }

    @Test
    public void taskList_deleteTask_taskListWithoutTask2() throws IndexOutOfRange {
        TaskList taskList = new TaskList();
        taskList.addTodo("task desc1");
        taskList.addTodo("task desc2");
        taskList.addTodo("task desc3");
        assertEquals(3, taskList.size());
        taskList.delete(0);
        assertEquals("task desc2", taskList.toTasksData().get(0).get(2));
        taskList.delete(1);
        assertEquals(1, taskList.size());
        assertEquals("task desc2", taskList.toTasksData().get(0).get(2));
    }

}
