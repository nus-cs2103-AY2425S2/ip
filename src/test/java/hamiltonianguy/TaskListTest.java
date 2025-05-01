package hamiltonianguy;

import hamiltonianguy.tasks.Task;
import hamiltonianguy.tasks.ToDoTask;
import hamiltonianguy.storage.Storage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void addTask_increasesListSize() {
        Storage storage = new Storage("test.txt");
        TaskList taskList = new TaskList(storage);
        int initialSize = taskList.getSize();

        taskList.addTask(new ToDoTask("Read book"));

        assertEquals(initialSize + 1, taskList.getSize());
    }

    @Test
    void deleteTask_decreasesListSize() {
        Storage storage = new Storage("test.txt");
        TaskList taskList = new TaskList(storage);
        taskList.addTask(new ToDoTask("Read book"));

        int initialSize = taskList.getSize();
        taskList.deleteTask(0);

        assertEquals(initialSize - 1, taskList.getSize());
    }
}
