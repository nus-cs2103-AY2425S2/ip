package phil.command;

import java.util.List;

import phil.exception.CommandType;
import phil.exception.InvalidArgumentException;
import phil.model.NoteList;

/**
 * Represents the command to delete a note.
 */
public class DeleteNoteCommand extends NoteCommand {
    private List<String> inputArgs;
    private int numTasks;

    /**
     * Sets up the New Note Command instance.
     *
     * @param inputArgs input arguments.
     * @param noteList list of notes.
     * @param isInputInvalid whether input to command is valid.
     * @param numTasks number of tasks in task list.
     */
    public DeleteNoteCommand(List<String> inputArgs, NoteList noteList, boolean isInputInvalid, int numTasks) {
        super(noteList, isInputInvalid);
        this.inputArgs = inputArgs;
        this.numTasks = numTasks;
    }

    /**
     * Executes the command and returns the output string.
     *
     * @return String representing the output to be displayed by the chatbot
     *         in the form of a dialog.
     */
    @Override
    public String execute() throws InvalidArgumentException {
        super.throwExceptionIfInvalid(CommandType.DELETE_NOTE, this.numTasks);
        int noteToRemove = Integer.parseInt(this.inputArgs.get(1));
        return super.getNoteList().deleteNote(noteToRemove);
    }
}
