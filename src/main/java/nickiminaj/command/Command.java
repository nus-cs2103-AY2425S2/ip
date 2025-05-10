package nickiminaj.command;

import nickiminaj.NickiMinajException;
import nickiminaj.NickiMinaj;
import nickiminaj.Storage;
import nickiminaj.TaskList;
import nickiminaj.Ui;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * An abstract class representing a command that can be executed.
 * Subclasses must implement the {@code execute} method to define
 * specific command behaviors.
 */
public abstract class Command {

    /**
     * Executes the command, performing the necessary actions.
     *
     * @param tasks   The task list that may be modified by the command.
     * @param ui      The user interface to display messages.
     * @param storage The storage system to persist any necessary data.
     * @throws Exception If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NickiMinajException;

    /**
     * Determines whether this command signals the program to exit.
     * By default, commands do not exit the program.
     *
     * @return {@code false} by default, overridden by exit commands.
     */
    public boolean isExit() {
        return false;
    }

    public String executeWithOutput(TaskList tasks, Ui ui, Storage storage) throws NickiMinajException {
        // Redirect System.out to capture output
        PrintStream originalOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        execute(tasks, ui, storage); // Call the original void method

        System.setOut(originalOut); // Restore standard output
        return buffer.toString().trim(); // Return captured output as a string
    }
}

