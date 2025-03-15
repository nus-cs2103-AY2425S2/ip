package lebum.command;

import lebum.exception.DukeException;
import lebum.main.Storage;
import lebum.main.TaskList;
import lebum.main.Ui;

/**
 * Greets users.
 */
public class GreetCommand extends Command {

    private String response = "";
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        this.response += "Wassup man what do you want?";
    }

    public String getResponse() {
        return response;
    }
}
