package phil.command;

import phil.model.NoteList;
import phil.model.TaskList;
import phil.storage.Storage;

/**
 * Represents a command to end the conversation.
 */
public class ByeCommand {
    private Storage storage;
    private NoteList noteList;
    private TaskList taskList;

    /**
     * Sets up the Bye command instance.
     *
     * @param taskList list of tasks to store.
     * @param noteList list of notes to store.
     * @param storage storage instance that handles loading and storing of data.
     */
    public ByeCommand(TaskList taskList, NoteList noteList, Storage storage) {
        this.taskList = taskList;
        this.noteList = noteList;
        this.storage = storage;
    }

    /**
     * Executes the command and closes the window.
     *
     * @return String representing the output of the execution.
     */
    public String execute() {
        String output = "";
        try {
            this.storage.save(this.taskList, this.noteList);
        } catch (Exception e) {
            output += "Data not saved: " + e.getMessage() + "\n";
        }
        return output + "Bye. Hope to see you again soon.";
    }

}
