package alex;

import alex.task.TaskList;
import alex.task.ToDo;
import alex.task.Deadline;
import alex.task.Event;
import alex.command.*;
import alex.exceptions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PaserTest {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    private void loadTasks() {
        this.taskList = new TaskList();
        this.storage = new Storage("data/test.txt");
        this.ui = new MockUi();

        ToDo todo = new ToDo("Lecture 1");
        Deadline deadline = new Deadline("PS1", "2025-02-04");
        Event event = new Event("meeting", "3pm", "5pm");
        taskList.addItem(todo, ui, storage);
        taskList.addItem(deadline, ui, storage);
        taskList.addItem(event, ui, storage);
    }

    @Test
    public void parse_display_success() {
        try {
            Command command = Parser.parse("list ", taskList);
            assertEquals(true, command instanceof DisplayCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_exit_success() {
        try {
            Command command = Parser.parse("bye ", taskList);
            assertEquals(true, command.isExit());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_mark_success() {
        try {
            loadTasks();
            Command command = Parser.parse("mark 3", taskList);
            command.execute(taskList, ui, storage);
            assertEquals(true, taskList.getTask(3).isDone());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_markOutOfBound_failure() {
        try {
            loadTasks();
            Command command = Parser.parse("mark 8", taskList);
            command.execute(taskList, ui, storage);
            fail();
        } catch (ListOutOfBoundException e) {
            assertEquals(true, true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_markInvalidArgument_failure() {
        try {
            loadTasks();
            Command command = Parser.parse("mark dfsdf", taskList);
            command.execute(taskList, ui, storage);
            fail();
        } catch (CommandFormatException e) {
            assertEquals(true, true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_invalidCommand_failure() {
        try {
            Command command = Parser.parse("asdf", taskList);
            fail();
        } catch (InvalidCommandException e) {
            assertEquals(true, true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_missingArgumentCommand_failure() {
        try {
            Command command = Parser.parse("deadline add ", taskList);
            fail();
        } catch (CommandFormatException e) {
            assertEquals(true, true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_incorrectCommandFormat_failure() {
        try {
            Command command = Parser.parse("event /by asdf /by asdf ", taskList);
            fail();
        } catch (CommandFormatException e) {
            assertEquals(true, true);
        } catch (Exception e) {
            fail();
        }
    }

}
