package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import exceptions.ElmachoException;

public class TasklistTest {

    /*
     * To test if tasklist properly deletes tasks
     */
    @Test
    public void deleteTask_success() {
        Tasklist tasklist = new Tasklist();
        tasklist.add(new ToDo("testing 1", false));
        tasklist.add(new ToDo("testing 2", false));
        assertEquals(2, tasklist.getNumberOfTasks());
        try {
            tasklist.delete(1);
            assertEquals(1, tasklist.getNumberOfTasks());
        } catch (ElmachoException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * To test the outer bounds of task numbers, to check that deleting this nonexistent task will fail
     */
    @Test
    public void deleteTask_fail() {
        Tasklist tasklist = new Tasklist();
        tasklist.add(new ToDo("testing 1", false));
        try {
            tasklist.delete(2);
            fail();
        } catch (ElmachoException e){
            assertEquals("Invalid task number.", e.getMessage());
        }
    }

    @Test
    public void markTask_success() {
        Tasklist tasklist = new Tasklist();
        tasklist.add(new ToDo("testing 1", false));
        tasklist.mark(0);
        boolean expected = true;
        assertEquals(expected, tasklist.getTasks().get(0).isDone);
    }

    @Test
    public void unmarkTask_success() {
        Tasklist tasklist = new Tasklist();
        tasklist.add(new ToDo("testing 1", true));
        tasklist.unmark(0);
        boolean expected = false;
        assertEquals(expected, tasklist.getTasks().get(0).isDone);
    }
}
