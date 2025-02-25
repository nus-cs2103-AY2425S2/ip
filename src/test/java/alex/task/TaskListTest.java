package alex.task;

import alex.MockUi;
import alex.Storage;
import alex.Ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    private TaskList taskList = new TaskList();
    private Ui ui = new MockUi();
    private Storage storage = new Storage("data/test.txt");

    public void resetTaskList() {
        this.taskList = new TaskList();
    }

    @Test
    public void addItem_addTodo_success() {
        ToDo todo = new ToDo("Lecture 1");
        taskList.addItem(todo, ui, storage);
        assertEquals(todo, taskList.getTask(1));
    }

    @Test
    public void addItem_addDeadline_success() {
        resetTaskList();
        Deadline deadline = new Deadline("PS1", "2025-02-04");
        taskList.addItem(deadline, ui, storage);
        assertEquals(deadline, taskList.getTask(1));
    }

    @Test
    public void addItem_addEvent_success() {
        resetTaskList();
        Event event = new Event("meeting", "3pm", "5pm");
        taskList.addItem(event, ui, storage);
        assertEquals(event, taskList.getTask(1));
    }

    @Test
    public void mark_markTask_success() {
        resetTaskList();
        Deadline deadline = new Deadline("PS1", "2025-02-04");
        Event event = new Event("meeting", "3pm", "5pm");
        taskList.addItem(deadline, ui, storage);
        taskList.addItem(event, ui, storage);

        taskList.mark(1, true, ui, storage);
        assertEquals(true, deadline.isDone());
    }

    @Test
    public void mark_markOutOfBound_failure() {
        try {
            resetTaskList();
            Deadline deadline = new Deadline("PS1", "2025-02-04");
            Event event = new Event("meeting", "3pm", "5pm");
            taskList.addItem(deadline, ui, storage);
            taskList.addItem(event, ui, storage);

            taskList.mark(4, true, ui, storage);
            fail();
        } catch (Exception e) {
            assertEquals(true, true);
        }
    }
}
