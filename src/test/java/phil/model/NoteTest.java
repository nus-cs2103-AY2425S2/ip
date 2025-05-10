package phil.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Represents Testing Class for Note Class.
 */
public class NoteTest {

    /**
     * Tests the toString method of the Note class with a new Note.
     */
    @Test
    public void toString_newNote_correctStringOutput() {
        Note note = new Note("the sky is blue");
        assertEquals("the sky is blue", note.toString());
    }
}
