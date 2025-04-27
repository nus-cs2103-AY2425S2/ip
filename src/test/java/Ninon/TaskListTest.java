package Ninon;

import static org.junit.jupiter.api.Assertions.*;

import Ninon.Task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddList() {
        Task task = new Task("Sample Task");
        String response = taskList.add_List(task);
        assertEquals("Got it. I've added this task: \n[ ] Sample Task\nNow you have 1 tasks in the list.", response);
        assertEquals(1, taskList.getList().size());
    }

    @Test
    void testDelete() {
        Task task = new Task("Sample Task");
        taskList.add_List(task);
        String response = taskList.delete(1);
        assertEquals("Noted. I've removed this task:\n[ ] Sample Task\nNow you have 0 tasks in the list.", response);
        assertEquals(0, taskList.getList().size());
    }

    @Test
    void testMark() {
        Task task = new Task("Sample Task");
        taskList.add_List(task);
        String response = taskList.mark(1);
        assertEquals("Nice! I've marked this task as done:\n[X] Sample Task\n", response);
    }

    @Test
    void testUnmark() {
        Task task = new Task("Sample Task");
        taskList.add_List(task);
        taskList.mark(1);
        String response = taskList.unmark(1);
        assertEquals("Nice! I've marked this task as not done yet:\n[ ] Sample Task\n", response);
    }

    @Test
    void testToString() {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        taskList.add_List(task1);
        taskList.add_List(task2);
        assertEquals("Here are the tasks in your list:\n1.[ ] Task 1\n2.[ ] Task 2", taskList.toString());
    }
}
