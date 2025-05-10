package phil.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import phil.model.NoteList;
import phil.model.TaskList;
import phil.model.Todo;
import phil.storage.Storage;


/**
 * Represents the Testing Class for Parser.
 */
public class ParserTest {

    /**
     * Tests the generateStringOutputFromInput method of the Parser class.
     */
    @Test
    public void generateStringOutputFromInput() {
        // Initialise with an empty TaskList and NoteList
        TaskList taskList = new TaskList();
        TaskList taskListCompare = new TaskList();
        NoteList noteList = new NoteList();
        Parser parser = new Parser(taskList, noteList, new Storage("data", "phil.txt", "philNotes.txt"));

        assertEquals(taskListCompare.addTask(new Todo("read a book")),
                     parser.generateStringOutputFromInput("todo read a book"));

        // Print list
        assertEquals(taskListCompare.toString(), parser.generateStringOutputFromInput("list"));
    }

}
