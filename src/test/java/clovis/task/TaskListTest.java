package clovis.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import clovis.ClovisException;

public class TaskListTest {

    @Test
    public void testAddTaskAndGetTask() throws ClovisException {
        TaskList tasks = new TaskList();
        ToDo todo = new ToDo("read book");
        tasks.addTask(todo);

        assertEquals(1, tasks.size());
        assertEquals(todo, tasks.getTask(1));
    }

    @Test
    public void testDeleteTask() throws ClovisException {
        TaskList tasks = new TaskList();
        ToDo todo1 = new ToDo("read book");
        ToDo todo2 = new ToDo("return book");
        tasks.addTask(todo1);
        tasks.addTask(todo2);
        Task deletedTask = tasks.deleteTask(1);

        assertEquals(todo1, deletedTask);
        assertEquals(1, tasks.size());
    }

    @Test
    public void testMarkTask() throws ClovisException {
        TaskList tasks = new TaskList();
        ToDo todo1 = new ToDo("read book");
        ToDo todo2 = new ToDo("return book");
        tasks.addTask(todo1);
        tasks.addTask(todo2);
        tasks.markTask(1, true);
        tasks.markTask(2, false);

        assertEquals(true, todo1.isDone);
        assertEquals(false, todo2.isDone);
    }


    @Test
    public void testFindTask_returnsMatchingTask() throws ClovisException {
        TaskList tasks = new TaskList();
        ToDo todo1 = new ToDo("read book");
        ToDo todo2 = new ToDo("return book");
        tasks.addTask(todo1);
        tasks.addTask(todo2);
        ArrayList<Task> matchingTasks = tasks.findTask("Read");

        assertEquals(1, matchingTasks.size());
        assertEquals(todo1, matchingTasks.get(0));
    }

    @Test
    public void testFindTask_returnsNoMatchingTask() throws ClovisException {
        TaskList tasks = new TaskList();
        ToDo todo1 = new ToDo("read book");
        ToDo todo2 = new ToDo("return book");
        tasks.addTask(todo1);
        tasks.addTask(todo2);
        ArrayList<Task> matchingTasks = tasks.findTask("sLeep");

        assertEquals(0, matchingTasks.size());
        assertTrue(matchingTasks.isEmpty());
    }

    @Test
    public void addTask_duplicateTask_throwsException() throws ClovisException {
        TaskList taskList = new TaskList();
        Task task1 = new ToDo("Read book");
        Task task2 = new ToDo("Read book");

        taskList.addTask(task1);

        assertThrows(ClovisException.class, () -> {
            taskList.addTask(task2);
        });
    }
}
