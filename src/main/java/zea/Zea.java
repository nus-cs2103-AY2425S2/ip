package zea;

import java.util.Timer;
import java.util.TimerTask;

import zea.command.Command;
import zea.command.ExitCommand;
import zea.task.Tasks;
import zea.ui.Ui;


/**
 * Zea
 */
public class Zea {

    protected Tasks tasks;
    protected Storage storage;

    /**
     * Creates a new instance of Zea
     *
     * @param fp - The filepath of where existing todos are stored
     */
    public Zea(String fp) {
        this.storage = new Storage(fp);
        try {
            this.tasks = new Tasks(storage.load());
        } catch (ZeaException e) {
            Ui.showLoadingError(e);
            tasks = new Tasks();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c instanceof ExitCommand) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                }, 2000);
            }
            assert c != null;
            return c.execute(tasks, storage);
        } catch (ZeaException | ParseException e) {
            return e.getMessage();
        }
    }
}
