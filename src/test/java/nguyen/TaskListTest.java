package nguyen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TaskListTest {

    @Test
    void testAddTodo() throws NguyenException {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Meet Friends");
        taskList.add(todo);
        assertEquals(1, taskList.size());
        assertEquals(todo, taskList.get(0));
    }

    @Test
    void testAddDuplicateTask() throws NguyenException {
        TaskList taskList = new TaskList();
        Task todo1 = new Todo("Meet Friends");
        Task todo2 = new Todo("Meet Friends");
        taskList.add(todo1);
        taskList.add(todo2);
        assertEquals(2, taskList.size());
        assertEquals(todo1, taskList.get(0));
        assertEquals(todo2, taskList.get(1));
    }

    @Test
    void testAddDeadline() throws NguyenException {
        TaskList taskList = new TaskList();
        Task deadline = new Deadline("Do Competitive Programming", "Dec 2 2019");
        taskList.add(deadline);
        assertEquals(1, taskList.size());
        assertEquals(deadline, taskList.get(0));
        assertTrue(deadline.toString().contains("[D]"));
    }

    @Test
    void testAddEvent() throws NguyenException {
        TaskList taskList = new TaskList();
        Task event = new Event("Learn CS2103T", "Dec 2 2019", "Dec 2 2019");
        taskList.add(event);
        assertEquals(1, taskList.size());
        assertEquals(event, taskList.get(0));
        assertTrue(event.toString().contains("[E]"));
    }

    @Test
    void testDeleteTask() throws NguyenException {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Meet Friends");
        Task deadline = new Deadline("Do Competitive Programming", "Dec 2 2019");
        taskList.add(todo);
        taskList.add(deadline);
        taskList.delete(1);
        assertEquals(1, taskList.size());
        assertEquals(deadline, taskList.get(0));
    }

    @Test
    void testDeleteTaskOutOfRange() throws NguyenException {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Meet Friends");
        taskList.add(todo);
        NguyenException exception = assertThrows(NguyenException.class, () -> {
            taskList.delete(2);
        });
        assertEquals("Bro, that task does not even exist", exception.getMessage());
    }

    @Test
    void testDeleteFromEmptyList() throws NguyenException {
        TaskList taskList = new TaskList();
        NguyenException exception = assertThrows(NguyenException.class, () -> {
            taskList.delete(1);
        });
        assertEquals("Bro, that task does not even exist", exception.getMessage());
    }

    @Test
    void testMarkTask() throws NguyenException {
        TaskList taskList = new TaskList();
        Task event = new Event("Learn CS2103T", "Dec 2 2019", "Dec 2 2019");
        taskList.add(event);
        taskList.mark(1);
        assertTrue(event.isMarked());
    }

    @Test
    void testMarkTaskOutOfRange() throws NguyenException {
        TaskList taskList = new TaskList();
        Task deadline = new Deadline("Do Competitive Programming", "Dec 2 2019");
        taskList.add(deadline);
        NguyenException exception = assertThrows(NguyenException.class, () -> {
            taskList.mark(2);
        });
        assertEquals("Nah, that task is not on the list", exception.getMessage());
    }

    @Test
    void testUnMarkTask() throws NguyenException {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Meet Friends");
        taskList.add(todo);
        taskList.mark(1);
        taskList.unMark(1);
        assertFalse(todo.isMarked());
    }

    @Test
    void testUnMarkTaskOutOfRange() throws NguyenException {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Meet Friends");
        taskList.add(todo);
        NguyenException exception = assertThrows(NguyenException.class, () -> {
            taskList.unMark(2);
        });
        assertEquals("Bro, there is no such task to unmark.", exception.getMessage());
    }

    @Test
    void testFindTask() throws NguyenException {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("Meet Friends"));
        taskList.add(new Deadline("Do Competitive Programming", "Dec 2 2019"));
        taskList.add(new Event("Learn CS2103T", "Dec 2 2019", "Dec 2 2019"));
        taskList.find("Meet");
    }

    @Test
    void testSortTasks() throws NguyenException {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Buy groceries");
        Task deadline = new Deadline("Finish assignment", "Dec 5 2019");
        Task event = new Event("Attend meeting", "Dec 3 2019", "Dec 3 2019");
        taskList.add(todo, deadline, event);
        taskList.sort("deadline");
    }

    @Test
    void testSortEmptyList() throws NguyenException {
        TaskList taskList = new TaskList();
        NguyenException exception = assertThrows(NguyenException.class, () -> {
            taskList.sort("todo");
        });
        assertEquals("There is nothing to sort, bro. Try adding some tasks first.", exception.getMessage());
    }

    @Test
    void testSortInvalidType() throws NguyenException {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("Buy groceries"));
        NguyenException exception = assertThrows(NguyenException.class, () -> {
            taskList.sort("random");
        });
        assertEquals("Uh that is not a valid task type", exception.getMessage());
    }

    @Test
    void testPrintList() throws NguyenException {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("Meet Friends"));
        taskList.add(new Deadline("Do Competitive Programming", "Dec 2 2019"));
        taskList.add(new Event("Learn CS2103T", "Dec 2 2019", "Dec 2 2019"));
        taskList.printList();
    }

    @Test
    void testPrintEmptyList() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }
}
