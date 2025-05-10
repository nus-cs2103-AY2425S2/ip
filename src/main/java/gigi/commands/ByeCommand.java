package gigi.commands;

import java.io.IOException;

import gigi.exceptions.GigiException;
import gigi.storage.Storage;
import gigi.tasks.Tasklist;
import gigi.ui.Ui;

/**
 * Represents a command to exit the Gigi chatbot.
 * This command is triggered when the user inputs "bye".
 */

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Constructs a ByeCommand.
     */
    public ByeCommand() {}

    /**
     * Executes the exit command.
     * Saves the task list before displaying a farewell message.
     *
     * @param tasks   The task list whose state needs to be saved.
     * @param ui      The UI component for displaying messages.
     * @param storage The storage component for saving tasks before exit.
     * @throws GigiException If an error occurs while saving tasks.
     */
    @Override
    public String execute(Tasklist tasks, Ui ui, Storage storage) throws GigiException, IOException {
        tasks.saveTasks(storage);
        return ui.showByeMessage();
    }
}
