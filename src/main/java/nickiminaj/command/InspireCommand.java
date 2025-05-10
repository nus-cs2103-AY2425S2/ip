package nickiminaj.command;

import nickiminaj.NickiMinajException;
import nickiminaj.Storage;
import nickiminaj.TaskList;
import nickiminaj.Ui;

public class InspireCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NickiMinajException {
        ui.showInspiration();
    }
}
