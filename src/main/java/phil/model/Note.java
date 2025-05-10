package phil.model;

/**
 * Represents a note.
 */
public class Note {
    private String note;

    /**
     * Sets up a note instance.
     *
     * @param note note to be taken down, as a string.
     */
    public Note(String note) {
        this.note = note;
    }

    /**
     * Returns a string showing the note's content.
     *
     * @return String representing the note.
     */
    @Override
    public String toString() {
        return this.note;
    }
}
