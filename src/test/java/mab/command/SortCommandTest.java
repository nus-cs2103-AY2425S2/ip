package mab.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.time.LocalDateTime;
import mab.task.Task;
import mab.task.Events;
import mab.task.Deadlines;
import mab.task.ToDos;
import mab.MabException;

class SortCommandTest {
    private ArrayList<Task> tasks;
    private SortCommand command;

    @BeforeEach
    void setUp() {
        tasks = new ArrayList<>();
        command = new SortCommand("");
    }

    @Test
    void testSortEmptyList() throws MabException {
        String result = command.execute(tasks);
        assertTrue(tasks.isEmpty());
        assertEquals("I've sorted the list for you!", result);
    }

    @Test
    void testSortMixedTasks() throws MabException {
        // Add tasks with different dates
        tasks.add(new Events("Later event", false, "2025-03-01 14:00", "2025-03-01 15:00"));
        tasks.add(new Events("Earlier event", false, "2025-02-20 14:00", "2025-02-20 15:00"));
        tasks.add(new ToDos("Todo without date", false));
        tasks.add(new Deadlines("Deadline", false, "2025-02-25 14:00"));

        String result = command.execute(tasks);
        assertEquals("I've sorted the list for you!", result);
        
        // Verify sort order
        assertEquals("Earlier event", tasks.get(0).getText());
        assertEquals("Deadline", tasks.get(1).getText());
        assertEquals("Later event", tasks.get(2).getText());
        assertEquals("Todo without date", tasks.get(3).getText());
    }

    @Test
    void testSortSameDateTime() throws MabException {
        tasks.add(new Events("Event 1", false, "2025-02-20 14:00", "2025-02-20 15:00"));
        tasks.add(new Events("Event 2", false, "2025-02-20 14:00", "2025-02-20 15:00"));
        
        String result = command.execute(tasks);
        assertEquals("I've sorted the list for you!", result);
        assertEquals(2, tasks.size());
    }

    @Test
    void testSortOnlyToDos() throws MabException {
        tasks.add(new ToDos("Todo 1", false));
        tasks.add(new ToDos("Todo 2", false));
        tasks.add(new ToDos("Todo 3", false));

        String result = command.execute(tasks);
        assertEquals("I've sorted the list for you!", result);
        assertEquals(3, tasks.size());
    }
}
