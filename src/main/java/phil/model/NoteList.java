package phil.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of notes.
 */
public class NoteList {
    private List<Note> notes;

    public NoteList() {
        this.notes = new ArrayList<Note>();
    }

    /**
     * Returns number of notes.
     *
     * @return number of notes stored.
     */
    public int getNumberOfNotes() {
        return this.notes.size();
    }

    /**
     * Adds note to note list, and returns String output for success.
     *
     * @param note Note to be added into list.
     * @return String output upon adding to list.
     */
    public String addNote(Note note) {
        this.notes.add(note);
        return "Got it! I've added this note: \n" + note.toString()
                + "\nNow you have " + this.getNumberOfNotes() + " notes in the list.";
    }

    /**
     * Removes note from note list, and returns String output for success.
     *
     * @param index index of note (1-indexed) to be removed from list.
     * @return String output upon removing note from note list.
     */
    public String deleteNote(int index) {
        String resultStr = "Noted. I've removed this note: \n" + this.notes.get(index - 1).toString();
        this.notes.remove(index - 1);
        return resultStr + "\nNow you have " + this.getNumberOfNotes() + " notes in the list.";
    }

    /**
     * Returns string representation of Note list, used when list is called.
     *
     * @return String representing the Note list and each Note.
     */
    @Override
    public String toString() {
        StringBuilder listToPrint = new StringBuilder();
        listToPrint.append("Here are the Notes in your list: \n");
        for (int i = 0; i < this.getNumberOfNotes(); i++) {
            listToPrint.append((i + 1)).append(". ").append(this.notes.get(i).toString()).append("\n");
        }
        if (this.getNumberOfNotes() > 10) {
            listToPrint.append("That's a lot of notes! Remember to discard what "
                    + "you don't need using the `delete-note <index-of-note>` command.");
        }
        return listToPrint.toString();
    }

    /**
     * Returns list of Notes stored in the Note list,
     * stored as a String for loading.
     *
     * @return list of Notes stored.
     */
    public String toLoadString() {
        StringBuilder listToPrint = new StringBuilder();
        for (int i = 0; i < this.getNumberOfNotes(); i++) {
            listToPrint.append(this.notes.get(i).toString()).append("\n");
        }
        return listToPrint.toString();
    }

}
