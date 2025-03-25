package buddy.display;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buddy.task.Deadline;
import buddy.task.Task;
import buddy.task.TaskList;
import buddy.task.Todo;

class DisplayTest {
    private TaskList taskList;
    private Task task1;
    private Task task2;
    private Task task3;

    /**
     * Sets up before each test.
     */
    @BeforeEach
    void setup() {
        taskList = new TaskList();
        task1 = new Todo("Buy books");
        task2 = new Deadline("Submit cs assignment", LocalDateTime.of(2024, 2, 15, 23, 59));
        task3 = new Todo("Go to supermarket");

        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
    }

    /**
     * Greet returns correct message.
     */
    @Test
    void greet_returnsCorrectMessage() {
        String expected = "_________________________________________\n"
                + "Hello! I'm Buddy\n"
                + "What can I do for you?\n"
                + "_________________________________________";
        assertEquals(expected, Display.greet());
    }

    /**
     * Bye returns correct message.
     */
    @Test
    void bye_returnsCorrectMessage() {
        String expected = "_________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "_________________________________________";
        assertEquals(expected, Display.bye());
    }

    /**
     * Mark task returns correct message.
     */
    @Test
    void markTask_returnsCorrectMessage() {
        task1.markTaskAsDone();
        String expected = "_________________________________________\n"
                + " Nice! I've marked this task as done:\n"
                + " [T][X] Buy books\n"
                + "_________________________________________";
        assertEquals(expected, Display.markTask(task1));
    }

    /**
     * Unmark task returns correct message.
     */
    @Test
    void unmarkTask_returnsCorrectMessage() {
        task2.markTaskAsDone();
        task2.unmarkTaskAsDone();
        String expected = "_________________________________________\n"
                + " OK, I've marked this task as not done yet:\n"
                + " [D][ ] Submit cs assignment (by: Feb 15 2024 11:59 PM)\n"
                + "_________________________________________";
        assertEquals(expected, Display.unmarkTask(task2));
    }
}
