package seedu.SirDuke.backend.command;

import seedu.SirDuke.UI;
import seedu.SirDuke.backend.Storage;
import seedu.SirDuke.backend.ToDoList;

import seedu.SirDuke.backend.task.DeadlineTask;
import seedu.SirDuke.backend.task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for DeadlineCommand.
 */
public class DeadlineCommandTest {

    @Test
    void executeTest() throws Exception {
        ToDoList toDoList = new ToDoList(new ArrayList<Task>());
        Storage storage = new Storage("./data/SirDuke.txt");
        String input = "deadline test /by 11/11/2011 11:11";
        DeadlineCommand deadlineCommand = new DeadlineCommand(input);


        DeadlineTask deadlineTask = new DeadlineTask("test", "11/11/2011 1111");
        DeadlineTask deadlineTaskToBeChecked = (DeadlineTask) toDoList.getTask(0);

        //Successful case
        Assertions.assertEquals(deadlineCommand.execute(toDoList, storage),
                UI.informThatTaskHasBeenCreated(deadlineTask),
                "Execution status should be the same (successful)"); //method to be tested
        assertEquals(deadlineTaskToBeChecked.getDescription(), deadlineTask.getDescription(),
                "Description of tasks should be the same (test)");
        assertEquals(deadlineTaskToBeChecked.getTaskType(), deadlineTask.getTaskType(),
                "Type of tasks should be the same (DEADLINE)");
        assertEquals(deadlineTaskToBeChecked.getToBeCompletedBy(), deadlineTask.getToBeCompletedBy(),
                "Time of tasks should be the same (11/11/2011 1111)");

        //Invalid time format
        input = "deadline test /by 11/11/200 1111";
        deadlineCommand = new DeadlineCommand(input);
        assertEquals(deadlineCommand.execute(toDoList, storage),
                UI.informThatDateTimeIsInvalid(),
                "Execution status should be the same (invalid time format)"); //method to be tested

        //Incomplete command
        input = "deadline test";
        deadlineCommand = new DeadlineCommand(input);
        assertEquals(deadlineCommand.execute(toDoList, storage),
                UI.informThatCommandIsIncomplete(),
                "Execution status should be the same (incomplete command)"); //method to be tested
    }
}
