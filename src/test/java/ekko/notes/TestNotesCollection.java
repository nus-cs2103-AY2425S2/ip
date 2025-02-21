package ekko.notes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestNotesCollection {

    private NotesCollection notesCollection;

    @BeforeEach
    public void setUp() {
        // Initialize a new NotesCollection for each test
        notesCollection = new NotesCollection();
    }

    @Test
    public void testAddNoteWithTitleAndContent() {
        // Add a note with title and content
        String title = "Meeting";
        String content = "Discuss project progress";
        String expectedMessage = "Ekko has helped you remember it!\nMeeting: Discuss project progress";

        String result = notesCollection.addNote(title, content);

        // Verify the response message and that the note is added
        assertEquals(expectedMessage, result, "The response message should confirm the note was added.");
        assertTrue(notesCollection.toString().contains("Meeting: Discuss project progress"), "The note should appear in the collection.");
    }

    @Test
    public void testAddNoteWithNoteObject() {
        // Add a note using the Note object
        Note note = new Note("Shopping", "Buy groceries");
        String expectedMessage = "Ekko has helped you remember it!\nShopping: Buy groceries";

        String result = notesCollection.addNote(note);

        // Verify the response message and that the note is added
        assertEquals(expectedMessage, result, "The response message should confirm the note was added.");
        assertTrue(notesCollection.toString().contains("Shopping: Buy groceries"), "The note should appear in the collection.");
    }

    @Test
    public void testRemoveNote() {
        // Add and remove a note
        notesCollection.addNote("Task", "Complete homework");

        String expectedRemoveMessage = "Ekko has helped you removed: Task";
        String result = notesCollection.removeNote("Task");

        // Verify the response message and that the note is removed
        assertEquals(expectedRemoveMessage, result, "The response message should confirm the note was removed.");
        assertFalse(notesCollection.toString().contains("Task: Complete homework"), "The note should not appear in the collection after removal.");
    }

    @Test
    public void testToString() {
        // Add multiple notes and verify the string representation of the collection
        notesCollection.addNote("Work", "Finish coding project");
        notesCollection.addNote("Exercise", "Go for a run");

        String expectedString = "Exercise: Go for a run\nWork: Finish coding project";
        assertEquals(expectedString, notesCollection.toString(), "The string representation of the collection should match the expected format.");
    }

    @Test
    public void testEmptyCollection() {
        // Verify that an empty collection returns an empty string
        assertEquals("", notesCollection.toString(), "The string representation of an empty collection should be empty.");
    }
}
