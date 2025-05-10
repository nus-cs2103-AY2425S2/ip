package phil.command;

import phil.exception.CommandType;
import phil.exception.InvalidArgumentException;
import phil.model.NoteList;

/**
 * Represents a command relating to notes.
 */
public abstract class NoteCommand {
    private NoteList noteList;
    private boolean isInputInvalid;

    /**
     * Sets up the Task Command instance.
     *
     * @param noteList list of notes.
     * @param isInputInvalid whether input to command is valid.
     */
    public NoteCommand(NoteList noteList, boolean isInputInvalid) {
        this.noteList = noteList;
        this.isInputInvalid = isInputInvalid;
    }

    /**
     * Returns a list of notes.
     *
     * @return noteList with a list of notes.
     */
    public NoteList getNoteList() {
        return this.noteList;
    }

    /**
     * Throws an InvalidArgumentException with a specified command type
     * if the input is invalid.
     *
     * @param commandType command type to create the InvalidArgumentException with.
     * @throws InvalidArgumentException exception to be thrown.
     */
    public void throwExceptionIfInvalid(CommandType commandType) throws InvalidArgumentException {
        if (!this.isInputInvalid) {
            return;
        }
        throw new InvalidArgumentException(commandType);
    }

    /**
     * Throws an InvalidArgumentException with a specified command type
     * if the input is invalid.
     *
     * @param commandType command type to create the InvalidArgumentException with.
     * @param numNotes number of notes in note list.
     * @throws InvalidArgumentException exception to be thrown.
     */
    public void throwExceptionIfInvalid(CommandType commandType, int numNotes) throws InvalidArgumentException {
        if (!this.isInputInvalid) {
            return;
        }
        throw new InvalidArgumentException(commandType, numNotes);
    }

    /**
     * Executes the command and returns the output string.
     *
     * @return String representing the output to be displayed by the chatbot
     *         in the form of a dialog.
     */
    public abstract String execute();
}
