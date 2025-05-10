package phil.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Represents Testing Class for TaskList Class.
 */
public class TaskListTest {

    /**
     * Tests the addTask method of the TaskList class with a new TaskList.
     */
    @Test
    public void addTask_newTaskList_correctStringOutput() {
        TaskList taskList = new TaskList();
        assertEquals("Got it! I've added this task: \n[T][ ] read book\nNow you have 1 tasks in the list.",
                taskList.addTask(new Todo("read book")));
    }

    /**
     * Tests the deleteTask method of the TaskList class with a TaskList of one task.
     */
    @Test
    public void deleteTask_newTaskListWithOneTask_correctStringOutput() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("read book"));
        assertEquals("Noted. I've removed this task: \n[T][ ] read book\nNow you have 0 tasks in the list.",
                taskList.deleteTask(1));
    }

    /**
     * Tests the toString method of the TaskList class with a TaskList of two tasks.
     */
    @Test
    public void toString_newTaskListWithTwoTasks_correctStringOutput() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Todo("watch tv"));
        assertEquals("Here are the tasks in your list: \n1. [T][ ] read book\n2. [T][ ] watch tv\n",
                taskList.toString());
    }

}
