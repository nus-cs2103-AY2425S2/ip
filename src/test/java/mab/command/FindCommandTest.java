package mab.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import mab.task.Task;
import mab.task.ToDos;
import mab.MabException;

class FindCommandTest {
    private ArrayList<Task> tasks;

    @BeforeEach
    void setUp() {
        tasks = new ArrayList<>();
        tasks.add(new ToDos("Read book", false));
        tasks.add(new ToDos("Watch movie", false));
        tasks.add(new ToDos("Reading assignment", false));
    }

    @Test
    void testFindExistingKeyword() throws MabException {
        FindCommand command = new FindCommand("read");
        String result = command.execute(tasks);
        assertTrue(result.contains("Read book"));
        assertTrue(result.contains("Reading assignment"));
    }

    @Test
    void testFindNonExistingKeyword() throws MabException {
        FindCommand command = new FindCommand("swimming");
        String result = command.execute(tasks);
        assertTrue(result.contains("I tried but there are no matching tasks found :P"));
    }

    @Test
    void testFindCaseInsensitive() throws MabException {
        FindCommand command = new FindCommand("BOOK");
        String result = command.execute(tasks);
        assertTrue(result.contains("Read book"));
    }

    @Test
    void testFindPartialMatch() throws MabException {
        FindCommand command = new FindCommand("wat");
        String result = command.execute(tasks);
        assertTrue(result.contains("Watch movie"));
    }

    @Test
    void testEmptyKeyword() {
        FindCommand command = new FindCommand("");
        assertThrows(
                MabException.class, 
                () -> command.execute(tasks),
                "Huh? Please provide a keyword to search for."
                );
    }

    @Test
    void testFindInEmptyList() throws MabException {
        ArrayList<Task> emptyTasks = new ArrayList<>();
        FindCommand command = new FindCommand("test");
        String result = command.execute(emptyTasks);
        assertTrue(result.contains("I tried but there are no matching tasks found :P"));
    }
}
