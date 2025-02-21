package avocado.command;

import avocado.task.TaskList;
import avocado.task.Todo;
import avocado.ui.Ui;
import avocado.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AddCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList(new ArrayList<>());
        ui = new Ui();
        storage = new Storage();
    }

    @Test
    void testExecute_AddTodo() throws Exception {
        AddCommand addCommand = new AddCommand(new Todo("Read a book"));
        addCommand.execute(tasks, ui, storage);

        assertEquals(1, tasks.getTasks().size(), "Task list should contain 1 task.");
        assertEquals("Read a book", tasks.getTasks().get(0).getDescription(), "Task description should match.");
    }
}
