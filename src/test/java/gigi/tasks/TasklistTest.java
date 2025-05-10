package gigi.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TasklistTest {
    private Tasklist tasklist;
    private Task todo;
    private Task deadline;
    private Task event;

    @BeforeEach
    void setUp() {
        tasklist = new Tasklist();
        todo = new ToDos("Buy groceries");
        deadline = new Deadlines("Submit report", java.time.LocalDateTime.now());
        event = new Events("Team meeting", java.time.LocalDateTime.now(), java.time.LocalDateTime.now().plusHours(2));

        tasklist.addTask(todo);
        tasklist.addTask(deadline);
        tasklist.addTask(event);
    }

    @Test
    void constructor_emptyTaskList_taskListInitialized() {
        Tasklist emptyTasklist = new Tasklist();
        assertTrue(emptyTasklist.isEmpty());
    }

    @Test
    void addTask_validTask_taskAdded() {
        assertEquals(3, tasklist.getSize());
    }

    @Test
    void deleteTask_validIndex_taskDeleted() {
        tasklist.deleteTask(1);
        assertEquals(2, tasklist.getSize());
    }

    @Test
    void markTaskAsDone_validIndex_taskMarkedDone() {
        tasklist.markTaskAsDone(0);
        assertTrue(tasklist.getTask(0).isComplete());
    }

    @Test
    void markTaskAsUndone_taskPreviouslyMarkedDone_statusReverted() {
        tasklist.markTaskAsDone(0);
        tasklist.markTaskAsUndone(0);
        assertFalse(tasklist.getTask(0).isComplete());
    }

    @Test
    void getMatchingTasks_validKeyword_matchingTasksReturned() {
        Tasklist matchingTasks = tasklist.getMatchingTasks("Buy");
        assertEquals(1, matchingTasks.getSize());
        assertEquals("Buy groceries", matchingTasks.getTask(0).getTaskName());
    }

    @Test
    void sortByType_tasksSorted_correctOrderMaintained() {
        tasklist.sortByType();
        assertTrue(tasklist.getTask(0) instanceof ToDos);
        assertTrue(tasklist.getTask(1) instanceof Deadlines);
        assertTrue(tasklist.getTask(2) instanceof Events);
    }
}
