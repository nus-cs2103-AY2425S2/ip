package gptzerofive.notes;

/**
 * Represents a note with a string content.
 */
public class Note {
    private String note;

    /**
     * Constructs an empty Note.
     */
    public Note() {
        this.note = "";
    }

    /**
     * Constructs a Note with the specified content.
     *
     * @param note the content of the note
     */
    public Note(String note) {
        this.note = note;
    }

    /**
     * Returns the string representation of the note.
     *
     * @return the content of the note
     */
    @Override
    public String toString() {
        return this.note;
    }

}
