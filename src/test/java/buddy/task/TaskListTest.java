package buddy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskListTest {
    private TaskList taskList;
    private Task task1;
    private Task task2;
    private Task task3;

    @BeforeEach
    void setup() {
        taskList = new TaskList();
        task1 = new Todo("Buy books");
        task2 = new Deadline("Submit cs assignment",
                java.time.LocalDateTime.of(2024, 2, 15, 23, 59));
        task3 = new Event("Team discussion",
                java.time.LocalDateTime.of(2024, 2, 20, 10, 0),
                java.time.LocalDateTime.of(2024, 2, 20, 12, 0));

        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
    }

    @Test
    void constructor_initializesEmptyList() {
        TaskList emptyList = new TaskList();
        assertEquals(0, emptyList.getLength());
    }

    @Test
    void constructor_withTasks_initializesCorrectly() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        TaskList initializedList = new TaskList(tasks);
        assertEquals(2, initializedList.getLength());
    }

    @Test
    void addTask_taskAddedSuccessfully() {
        Task newTask = new Todo("Go to gym");
        taskList.addTask(newTask);

        assertEquals(4, taskList.getLength());
        assertEquals(newTask, taskList.getTask(3));
    }

    @Test
    void getTask_validIndex_returnsCorrectTask() {
        assertEquals(task1, taskList.getTask(0));
        assertEquals(task2, taskList.getTask(1));
        assertEquals(task3, taskList.getTask(2));
    }

    @Test
    void getTask_outOfBounds_throwsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(10));
    }

    @Test
    void deleteTask_taskRemovedSuccessfully() {
        taskList.deleteTask(task2);

        assertEquals(2, taskList.getLength());
        assertFalse(taskList.getTask(0).equals(task2)); // Task2 should be removed
    }

    @Test
    void deleteTask_nonExistentTask_listNoChange() {
        Task nonExistentTask = new Todo("Some random task");
        taskList.deleteTask(nonExistentTask);

        assertEquals(3, taskList.getLength()); // List remains unchanged
    }

    @Test
    void filter_keywordExists_returnsFilteredList() {
        TaskList filteredList = taskList.filter("books");

        assertEquals(1, filteredList.getLength());
        assertEquals(task1, filteredList.getTask(0));
    }

    @Test
    void filter_caseSensitive_noMatch() {
        TaskList filteredList = taskList.filter("Books"); // Case-sensitive

        assertEquals(0, filteredList.getLength());
    }

    @Test
    void filter_emptyKeyword_returnsAllTasks() {
        TaskList filteredList = taskList.filter("");

        assertEquals(3, filteredList.getLength());
    }

    @Test
    void filter_noMatch_returnsEmptyList() {
        TaskList filteredList = taskList.filter("gym");

        assertEquals(0, filteredList.getLength());
    }

    @Test
    void filter_nullKeyword_throwsException() {
        assertThrows(NullPointerException.class, () -> taskList.filter(null));
    }
}
