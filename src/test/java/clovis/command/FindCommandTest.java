package clovis.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clovis.ClovisException;
import clovis.Storage;
import clovis.Ui;
import clovis.task.TaskList;
import clovis.task.ToDo;

public class FindCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void setUp() throws ClovisException {
        taskList = new TaskList(new ArrayList<>());
        ui = new Ui();
        storage = new Storage("data/test_tasks.txt");
        taskList.addTask(new ToDo("Read book"));
        taskList.addTask(new ToDo("Write code"));
    }

    @Test
    public void testExecute_returnsMatchingTasks() throws ClovisException {
        Command command = new FindCommand("book");

        String result = command.execute(taskList, ui, storage);

        assertTrue(result.contains("Read book"));
        assertFalse(result.contains("Write code"));
    }

    @Test
    public void testExecute_returnsNoMatches() throws ClovisException {
        Command command = new FindCommand("sleep");

        String result = command.execute(taskList, ui, storage);

        assertEquals("No tasks matching sleep", result);
    }
}
