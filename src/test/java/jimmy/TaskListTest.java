package jimmy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jimmy.tasks.Task;
import jimmy.tasks.TaskList;
import jimmy.tasks.Todo;

class TaskListTest {

    @Test
    void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Complete assignment");
        taskList.addTask(task);
        Assertions.assertEquals(1, taskList.size(), "TaskList should contain 1 task after adding.");
        Assertions.assertEquals(task, taskList.getTask(0), "The added task should be retrievable.");
    }

    @Test
    void testDeleteTask() {
        TaskList taskList = new TaskList();
        Task task = new Todo("Complete assignment");
        taskList.addTask(task);
        Task deletedTask = taskList.deleteTask(0);
        Assertions.assertEquals(task, deletedTask, "The deleted task should be the same as the added one.");
        Assertions.assertEquals(0, taskList.size(), "TaskList should be empty after deletion.");
    }

    @Test
    void testDeleteFromEmptyList() {
        TaskList taskList = new TaskList();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(0),
                "Deleting from an empty list should throw IndexOutOfBoundsException.");
    }

    @Test
    void testGetInvalidIndex() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Complete assignment"));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(5),
                "Getting a task with an invalid index should throw IndexOutOfBoundsException.");
    }

    @Test
    void testMultipleTasks() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("Task 1");
        Task task2 = new Todo("Task 2");
        Task task3 = new Todo("Task 3");
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
        Assertions.assertEquals(3, taskList.size(), "TaskList should contain 3 tasks.");
        Assertions.assertEquals(task2, taskList.getTask(1), "The second task should be retrievable correctly.");
    }
}
