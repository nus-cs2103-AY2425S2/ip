package phil.command;

import java.util.List;

import phil.exception.CommandType;
import phil.exception.InvalidArgumentException;
import phil.model.Note;
import phil.model.NoteList;


/**
 * Represents the command to create a new note.
 */
public class NewNoteCommand extends NoteCommand {
    private List<String> inputArgs;

    /**
     * Sets up the New Note Command instance.
     *
     * @param inputArgs input arguments.
     * @param noteList list of notes.
     * @param isInputInvalid whether input to command is valid.
     */
    public NewNoteCommand(List<String> inputArgs, NoteList noteList, boolean isInputInvalid) {
        super(noteList, isInputInvalid);
        this.inputArgs = inputArgs;
    }

    /**
     * Executes the command and returns the output string.
     *
     * @return String representing the output to be displayed by the chatbot
     *         in the form of a dialog.
     */
    @Override
    public String execute() throws InvalidArgumentException {
        super.throwExceptionIfInvalid(CommandType.CREATE_NOTE);
        String note = String.join(" ", inputArgs.subList(1, inputArgs.size()));
        return super.getNoteList().addNote(new Note(note));
    }
}
