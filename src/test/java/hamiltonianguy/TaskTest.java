package hamiltonianguy.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void mark_setsTaskAsDone() {
        Task task = new ToDoTask("Read book");
        task.mark();

        assertTrue(task.isDone());
    }

    @Test
    void unmark_setsTaskAsNotDone() {
        Task task = new ToDoTask("Read book");
        task.mark();
        task.unmark();

        assertFalse(task.isDone());
    }
}

