package phil.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Represents Testing Class for NoteList Class.
 */
public class NoteListTest {

    /**
     * Tests the addNote method of the NoteList class with a new NoteList.
     */
    @Test
    public void addNote_newNoteList_correctStringOutput() {
        NoteList noteList = new NoteList();
        assertEquals("Got it! I've added this note: \nthe sky is blue\nNow you have 1 notes in the list.",
                noteList.addNote(new Note("the sky is blue")));
    }

    /**
     * Tests the deleteNote method of the NoteList class with a new NoteList.
     */
    @Test
    public void deleteNote_newNoteListWithOneNote_correctStringOutput() {
        NoteList noteList = new NoteList();
        noteList.addNote(new Note("the sky is blue"));
        assertEquals("Noted. I've removed this note: \nthe sky is blue\nNow you have 0 notes in the list.",
                noteList.deleteNote(1));
    }

    /**
     * Tests the toString method of the NoteList class with a new NoteList.
     */
    @Test
    public void toString_newNoteListWithTwoNotes_correctStringOutput() {
        NoteList noteList = new NoteList();
        noteList.addNote(new Note("the sky is blue"));
        noteList.addNote(new Note("the sun is red"));
        assertEquals("Here are the Notes in your list: \n1. the sky is blue\n2. the sun is red\n",
                noteList.toString());
    }

    /**
     * Tests the toLoadString method of the NoteList class with a new NoteList.
     */
    @Test
    public void toLoadString_newNoteListWithTwoNotes_correctStringOutput() {
        NoteList noteList = new NoteList();
        noteList.addNote(new Note("the sky is blue"));
        noteList.addNote(new Note("the sun is red"));
        assertEquals("the sky is blue\nthe sun is red\n", noteList.toLoadString());
    }

}
