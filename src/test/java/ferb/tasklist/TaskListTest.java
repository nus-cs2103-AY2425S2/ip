package ferb.tasklist;

import ferb.exception.FerbException;
import ferb.task.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void sortDescription_sortsTasksByDescription() throws FerbException {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("Read a book"));
        taskList.add(new ToDo("Buy groceries"));
        taskList.add(new Deadline("Submit assignment", "2023-12-01"));
        taskList.add(new Event("Attend workshop", "2023-11-20", "2023-11-22"));
        taskList.add(new ToDo("Clean the house"));

        taskList.sortDescription();

        assertEquals("Attend workshop", taskList.get(0).taskDescription());
        assertEquals("Buy groceries", taskList.get(1).taskDescription());
        assertEquals("Clean the house", taskList.get(2).taskDescription());
        assertEquals("Read a book", taskList.get(3).taskDescription());
        assertEquals("Submit assignment", taskList.get(4).taskDescription());
    }

    @Test
    public void sortDate_sortsTasksByDate() throws FerbException {
        TaskList taskList = new TaskList();
        taskList.add(new Deadline("Submit assignment", "2023-12-01"));
        taskList.add(new Event("Conference", "2023-11-20", "2023-11-22"));
        taskList.add(new Deadline("Project deadline", "2023-11-15"));
        taskList.add(new Event("Team meeting", "2023-10-10", "2023-10-10"));
        taskList.add(new Deadline("Final exam", "2023-12-15"));

        taskList.sortDate();

        assertEquals("Team meeting", taskList.get(0).taskDescription());
        assertEquals("Project deadline", taskList.get(1).taskDescription());
        assertEquals("Conference", taskList.get(2).taskDescription());
        assertEquals("Submit assignment", taskList.get(3).taskDescription());
        assertEquals("Final exam", taskList.get(4).taskDescription());
    }
}