package miki.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import miki.task.Task;
import miki.task.TaskList;
import miki.task.ToDo;

public class UiTest {
    @Test
    public void testUi_showLoadingError() {
        try {
            // Test showLoadingError
            Ui ui = new Ui();

            assertEquals(ui.showLoadingError(), "Error loading tasks from file. Starting with an empty task list.");
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testUi_showErrorMessage() {
        try {
            String errorMessage = "Error test message";
            Ui ui = new Ui();

            assertEquals("Error Occured Dawg.\n" + "Error test message", ui.showErrorMessage(errorMessage));
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testUi_showTaskAdded() {
        try {
            Task task = new ToDo("Test", new String[] {"coolios"});
            Ui ui = new Ui();

            assertEquals("ToDo task has been added Dawg.\n"
                + "[T] [ ]  Test" + "\n" + "#coolios" + "\n"
                + "You now have 2 tasks in the list Dawg.", ui.showTaskAdded(task, 2));
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testUi_showTaskDeleted() {
        try {
            Task task = new ToDo("Test", new String[] {"coolios"});
            Ui ui = new Ui();

            assertEquals("Task 1 has been deleted Dawg.\n"
                + "[T] [ ]  Test" + "\n" + "#coolios" + "\n",
                 ui.showTaskDeleted(task, 1));
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testUi_showListEmpty() {
        try {
            Ui ui = new Ui();

            assertEquals("No tasks in the list Dawg.", ui.showList(new TaskList()));
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testUi_showList() {
        try {
            Ui ui = new Ui();
            TaskList tasks = new TaskList();
            tasks.addTask(new ToDo("Test", new String[] {"coolios"}));

            assertEquals("Here are the tasks in your list Dawg:\n"
                + "1. [T] [ ]  Test" + "\n" + "#coolios" + "\n" + "\n",
                ui.showList(tasks));
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testUi_showTaskCompletion_completed() {
        try {
            Task task = new ToDo("Test", new String[] {"coolios"});
            Ui ui = new Ui();
            task.toggleCompletion();

            assertEquals("Task 1 completed.\n"
                + "[T] [X]  Test" + "\n" + "#coolios" + "\n",
                ui.showTaskCompletion(task, 1));
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testUi_showTaskCompletion_notCompleted() {
        try {
            Task task = new ToDo("Test", new String[] {"coolios"});
            Ui ui = new Ui();

            assertEquals("Task 1 is not completed.\n"
                + "[T] [ ]  Test" + "\n" + "#coolios" + "\n",
                ui.showTaskCompletion(task, 1));
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testUi_showExit() {
        try {
            Ui ui = new Ui();

            assertEquals("End of session Dawg. Goodbye Dawg.\n", ui.showExit());
        } catch (Error e) {
            fail();
        }
    }
}
