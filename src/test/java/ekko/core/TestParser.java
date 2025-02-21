package ekko.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ekko.notes.NotesCollection;
import ekko.storage.Storage;
import ekko.task.Todo;
import ekko.task.Todolist;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// used AI to improve and generate JUnit test cases
public class TestParser {

    private Todolist todolist;
    private NotesCollection notesCollection;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        // Initialize the objects for each test
        todolist = new Todolist();
        notesCollection = new NotesCollection();
        storage = new Storage();
    }

    // Test for parsing commands
    @Test
    public void testParseCommand() {
        assertEquals(Commands.TODO, Parser.parseCommand("todo do something"));
        assertEquals(Commands.EVENT, Parser.parseCommand("event meeting /from 2025-02-20 10:00 /to 2025-02-20 12:00"));
        assertEquals(Commands.DEADLINE, Parser.parseCommand("deadline submit report /by 2025-02-21 18:00"));
        assertEquals(Commands.MARK, Parser.parseCommand("mark 1"));
        assertEquals(Commands.UNMARK, Parser.parseCommand("unmark 1"));
    }

    // Test for date and time parsing
    @Test
    public void testParseDateTime() {
        String dateStr1 = "2025-02-20 10:00";
        String dateStr2 = "20/02/2025 10:00";
        String dateStr3 = "20 02 2025 10:00";

        LocalDateTime date1 = Parser.parseDateTime(dateStr1);
        LocalDateTime date2 = Parser.parseDateTime(dateStr2);
        LocalDateTime date3 = Parser.parseDateTime(dateStr3);

        assertEquals(LocalDateTime.of(2025, 2, 20, 10, 0), date1);
        assertEquals(LocalDateTime.of(2025, 2, 20, 10, 0), date2);
        assertEquals(LocalDateTime.of(2025, 2, 20, 10, 0), date3);
    }

    // Test for parsing event command
    @Test
    public void testParseEvent() {
        String input = "event meeting /from 2025-02-20 10:00 /to 2025-02-20 12:00";
        String response = Parser.parseEvent(todolist, storage, input);
        assertTrue(response.contains("meeting"), "Event description should be included.");
        assertTrue(response.contains("2025-02-20T10:00"), "Start time should be included.");
        assertTrue(response.contains("2025-02-20T12:00"), "End time should be included.");
    }

    // Test for parsing todo command
    @Test
    public void testParseTodo() {
        String input = "todo finish homework";
        String response = Parser.parseTodo(todolist, storage, input);
        assertTrue(response.contains("finish homework"), "Todo description should be included.");
    }

    // Test for parsing deadline command
    @Test
    public void testParseDeadline() {
        String input = "deadline submit report /by 2025-02-21 18:00";
        String response = Parser.parseDeadline(todolist, storage, input);
        assertTrue(response.contains("submit report"), "Deadline description should be included.");
        assertTrue(response.contains("2025-02-21T18:00"), "Deadline date should be included.");
    }

    // Test for parsing meow command (Easter egg)
    @Test
    public void testParseMeow() {
        String response = Parser.parseMeow();
        assertEquals("Meow, I love you too.", response, "Meow response should be correct.");
    }

    // Test for parseFind command
    @Test
    public void testParseFind() {
        todolist.add(new Todo("Finish homework"));
        todolist.add(new Todo("Buy groceries"));

        String input = "find homework";
        String response = Parser.parseFind(todolist, storage, input);
        assertTrue(response.contains("homework"), "Find command should return relevant tasks.");
    }

    // Test for parseMark command
    @Test
    public void testParseMark() {
        todolist.add(new Todo("Finish homework"));
        String input = "mark 1";
        String response = Parser.parseMark(todolist, storage, input);
        assertTrue(response.contains("off the list"), "Mark command should return success message.");
    }

    // Test for parseUnmark command
    @Test
    public void testParseUnmark() {
        todolist.add(new Todo("Finish homework"));
        todolist.mark(1);
        String input = "unmark 1";
        String response = Parser.parseUnmark(todolist, storage, input);
        assertTrue(response.contains("Meow, please remember to do it still..."), "Unmark command should return success message.");
    }

    // Test for parseDelete command
    @Test
    public void testParseDelete() {
        todolist.add(new Todo("Finish homework"));
        String input = "delete 1";
        String response = Parser.parseDelete(todolist, storage, input);
        assertTrue(response.contains("eaten your task"), "Delete command should return success message.");
    }

    // Test for parseNote command
    @Test
    public void testParseNote() {
        String input = "note /t Meeting /d Discuss project progress";
        String response = Parser.parseNote(notesCollection, storage, input);
        assertTrue(response.contains("Meeting"), "Note title should be included.");
        assertTrue(response.contains("Discuss project progress"), "Note description should be included.");
    }

    // Test for parseRMnote command
    @Test
    public void testParseRMnote() {
        notesCollection.addNote("Meeting", "Discuss project progress");
        String input = "rmnote Meeting";
        String response = Parser.parseRMnote(notesCollection, storage, input);
        assertTrue(response.contains("removed"), "Remove note command should return success message.");
    }

    // Test for parseList command
    @Test
    public void testParseList() {
        todolist.add(new Todo("Finish homework"));
        notesCollection.addNote("Meeting", "Discuss project progress");

        String response = Parser.parseList(notesCollection, todolist);
        assertTrue(response.contains("Finish homework"), "Todo list should be included in list command.");
        assertTrue(response.contains("Meeting"), "Note list should be included in list command.");
    }
}
