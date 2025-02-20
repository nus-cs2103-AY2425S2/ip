package pixel.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import pixel.util.PixelException;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();

        // successful add task
        taskList.addTask(new ToDo("cleanup"));
        assertEquals(1, taskList.getListSize());
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("cleanup"));

        // invalid task index
        try {
            taskList.deleteTask(5);
            fail();
        } catch (PixelException e) {
            assertEquals("Please input a valid task number!", e.getMessage());
        }
        try {
            taskList.deleteTask(-2);
            fail();
        } catch (PixelException e) {
            assertEquals("Please input a valid task number!", e.getMessage());
        }

        // valid task index
        taskList.deleteTask(0);
        assertEquals(0, taskList.getListSize());
    }

    @Test
    public void testFileFormatConversion() {
        TaskList taskList = new TaskList();

        // empty taskList
        assertEquals("EOF", taskList.toFileFormat());

        // populated taskList
        taskList.addTask(new ToDo("cleanup", false));
        taskList.addTask(new Deadline("assignment", true, LocalDateTime.parse("2024-10-21T19:00")));
        assertEquals("todo\ncleanup\nfalse\ndeadline\nassignment\ntrue\n2024-10-21T19:00\nEOF",
                taskList.toFileFormat());
    }

}
