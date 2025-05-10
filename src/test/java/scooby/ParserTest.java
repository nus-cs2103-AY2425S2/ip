package scooby;

import scooby.tasks.TaskList;
import scooby.tasks.ToDo;
import scooby.ui.Ui;
import scooby.ui.Parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private Parser parser;
    private TaskList taskList;
    private Ui ui;

    @BeforeEach
    void setUp() {
        taskList = new TaskList(); // Using a real TaskList or simple stub
        ui = new Ui("Scooby");
        parser = new Parser(taskList, ui);
    }

    @Test
    void testParseCommand_AddTask_EmptyDescription() {
        // Test that an empty description results in an EmptyException
        String command = "todo";
        boolean result = parser.parseCommand(command);

        assertTrue(result, "Command should continue running after printing error.");
    }

    @Test
    void testParseCommand_Exit_Success() {
        // Test that "bye" command exits the dialogue
        String command = "bye";
        boolean result = parser.parseCommand(command);

        assertFalse(result, "Command should return false indicating the chatbot should exit.");
    }

    @Test
    void testParseCommand_MarkTask_Success() {
        // Add a task first
        taskList.addTask("todo Task 1");

        // Test that a "mark" command marks a task
        String command = "mark 1";
        boolean result = parser.parseCommand(command);

        assertTrue(result, "Command should continue running.");
        assertTrue(taskList.getTask(0).isChecked(), "The task should be marked as done.");
    }

    @Test
    void testParseCommand_UnmarkTask_Success() {
        // Add a task first
        taskList.addTask("todo Task 1");

        // Test that an "unmark" command unmarks a task
        String command = "unmark 1";
        boolean result = parser.parseCommand(command);

        assertTrue(result, "Command should continue running.");
        assertFalse(taskList.getTask(0).isChecked(), "The task should be unmarked.");
    }

}
