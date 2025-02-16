package Acheron.Tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskWriterTest {
    static final String TODO_EMPTY_SPACE_ERROR = "Check your to do input.\n" +
            "Make sure the text follows the format\n" +
            "todo [Some text]\n" +
            "(Note that the [ ] is not needed! E.g todo eat peach";
    static final String TO_DO_EMPTY_ERROR = "Check your to do input.\n" +
            "Make sure the text follows the format\n" +
            "todo [Some text]\n" +
            "(Note that the [ ] is not needed! E.g todo eat peach";
    static final String DEADLINE_WRONG_FORMAT_ERROR = "Date is wrongly"
            + " formatted or start and end date is inconsistent!"
            + " Make sure it follows the YYYY-MM-DD format and start date is"
            + " always less than or equal to end date";
    static final String DEADLINE_WRONG_LENGTH_BUT_RIGHT_FORMAT_ERROR = "Date is wrongly"
            + " formatted or start and end date is inconsistent!"
            + " Make sure it follows the YYYY-MM-DD format and start date is"
            + " always less than or equal to end date";
    static final String EVENT_WRONG_ERROR = "Date is wrongly"
            + " formatted or start and end date is inconsistent!"
            + " Make sure it follows the YYYY-MM-DD format and start date is"
            + " always less than or equal to end date";
    static final String CORRUPTED_FILE_ERROR = "Corrupted file. Cannot read data";

    /**
     * Check if task writer throws an error if to do task uses space as description
     */
    @Test
    public void TaskWriterTodoEmptySpace(){
        try {
            TaskWriter.createTask("todo ", new TaskList());
        } catch (Exception e) {
            assertEquals(TODO_EMPTY_SPACE_ERROR, e.toString());
        }
    }

    /**
     * Check if task writer throws an error if to do task has no description
     */
    @Test
    public void TaskWriterTodoEmpty(){
        try {
            TaskWriter.createTask("todo", new TaskList());
        } catch (Exception e) {
            assertEquals(TO_DO_EMPTY_ERROR, e.toString());
        }
    }

    /**
     * Check if task writer throws an error if deadline task uses wrong date format
     */
    @Test
    public void TaskWriterDeadlineWrongFormat(){
        try {
            TaskWriter.createTask("deadline b /by 2019/02/12", new TaskList());
        } catch (Exception e) {
            assertEquals(DEADLINE_WRONG_FORMAT_ERROR, e.toString());
        }
    }

    /**
     * Check if task writer throws an error if deadline task uses right format
     * but wrong number of characters
     */
    @Test
    public void TaskWriterDeadlineWrongLengthButRightFormat(){
        try {
            TaskWriter.createTask("deadline b /by 2019-02-1", new TaskList());
        } catch (Exception e) {
            assertEquals(DEADLINE_WRONG_LENGTH_BUT_RIGHT_FORMAT_ERROR, e.toString());
        }
    }

    /**
     * Check if task writer throws an error if the event is wrongly written
     */
    @Test
    public void TaskWriterEventWrong(){
        try {
            TaskWriter.createTask("deadline b /by 2019-02-1", new TaskList());
        } catch (Exception e) {
            assertEquals(EVENT_WRONG_ERROR, e.toString());
        }
    }

    /**
     * Checks if task writer throws an error when creating a
     * saved task from a corrupted file
     */
    @Test
    public void TaskWriterSavedFileTodoEmptySpace(){
        try {
            TaskWriter.createSavedTask("todo ", new TaskList());
        } catch (Exception e) {
            assertEquals(CORRUPTED_FILE_ERROR, e.toString());
        }
    }

    /**
     * Checks if the task writer throwns an error
     * when creating a saved task from a corrupted file
     */
    @Test
    public void TaskWriterSavedFileTodoEmpty(){
        try {
            TaskWriter.createSavedTask("todo", new TaskList());
        } catch (Exception e) {
            assertEquals(CORRUPTED_FILE_ERROR, e.toString());
        }
    }

    /**
     * Checks if the task writer throws an error
     * when creating a saved task from a corrupted file
     */
    @Test
    public void TaskWriterSavedFileDeadlineWrongFormat(){
        try {
            TaskWriter.createSavedTask("deadline b /by 2019/02/12", new TaskList());
        } catch (Exception e) {
            assertEquals(CORRUPTED_FILE_ERROR, e.toString());
        }
    }

    /**
     * Checks if the task writer throws an error
     * when creating a saved task from a corrupted file
     */
    @Test
    public void TaskWriterSavedFileDeadlineWrongLengthButRightFormat(){
        try {
            TaskWriter.createSavedTask("deadline b /by 2019-02-1", new TaskList());
        } catch (Exception e) {
            assertEquals(CORRUPTED_FILE_ERROR, e.toString());
        }
    }

    /**
     * Checks if the task writer throws an error
     * when creating a saved task from a corrupted file
     */
    @Test
    public void TaskWriterSavedFileEventWrong(){
        try {
            TaskWriter.createSavedTask("deadline b /by 2019-02-1", new TaskList());
        } catch (Exception e) {
            assertEquals(CORRUPTED_FILE_ERROR, e.toString());
        }
    }
}