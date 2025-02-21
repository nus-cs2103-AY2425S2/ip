package ekko.notes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestNote {

    private Note note;

    @BeforeEach
    public void setUp() {
        // Create a Note instance for testing
        note = new Note("Sample Title", "This is a sample description.");
    }

    @Test
    public void testGetTitle() {
        // Verify that the title is returned correctly
        assertEquals("Sample Title", note.getTitle(), "Title should be 'Sample Title'");
    }

    @Test
    public void testGetDescription() {
        // Verify that the description is returned correctly
        assertEquals("This is a sample description.", note.getDescription(), "Description should match the provided description.");
    }

    @Test
    public void testToString() {
        // Verify that the toString method returns the correct format
        String expectedOutput = "Sample Title: This is a sample description.";
        assertEquals(expectedOutput, note.toString(), "The toString method should return the formatted string.");
    }

    @Test
    public void testNoteInitialization() {
        // Verify the initialization of Note with specific values
        assertNotNull(note, "Note object should be properly initialized.");
    }
}
