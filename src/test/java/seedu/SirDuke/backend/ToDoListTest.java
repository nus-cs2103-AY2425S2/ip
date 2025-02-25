package seedu.SirDuke.backend;

import seedu.SirDuke.backend.task.Task;
import seedu.SirDuke.backend.task.ToDoTask;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for ToDoList.
 */
public class ToDoListTest {

    /**
     * Test for creating a ToDoTask and adding it to the toDoList, <c>createToDoTask()</c>.
     * @throws Exception
     */
    @Test
    public void createToDoTaskTest() throws Exception {
        ToDoList toDoList = new ToDoList(new ArrayList<Task>());
        toDoList.createToDoTask("test"); //method to be tested
        Task toDo = new ToDoTask("test");
        assertEquals(toDoList.getTask(0).getDescription(), toDo.getDescription(),
                "Description of tasks should be the same (test)");
        assertEquals(toDoList.getTask(0).getTaskType(), toDo.getTaskType(),
                "Type of tasks should be the same (TODO)");
    }
}
