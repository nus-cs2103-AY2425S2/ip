package phil.command;

import phil.exception.InvalidArgumentException;
import phil.model.NoteList;

/**
 * Represents the command to list notes.
 */
public class ListNoteCommand extends NoteCommand {

    /**
     * Sets up the New Note Command instance.
     *
     * @param noteList list of notes.
     */
    public ListNoteCommand(NoteList noteList) {
        super(noteList, false);
    }

    /**
     * Executes the command and returns the output string.
     *
     * @return String representing the output to be displayed by the chatbot
     *         in the form of a dialog.
     */
    @Override
    public String execute() throws InvalidArgumentException {
        return super.getNoteList().toString();
    }
}
