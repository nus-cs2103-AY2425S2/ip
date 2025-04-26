package sunpter;

import command.Command;
import exception.SunpterException;
import parser.Parser;
import storage.Storage;
import task.PriorityRoster;
import ui.Ui;
import java.util.ArrayList;

public class Sunpter {
    private Storage storage;
    private PriorityRoster roster;
    private Ui ui;
    private Parser parser;

    public Sunpter() {
        ui = new Ui();
        storage = new Storage();
        try {
            roster = new PriorityRoster(storage.loadTasks());
        } catch (SunpterException e) {
            roster = new PriorityRoster(new ArrayList<>());
        }
        parser = new Parser();
    }

    public String getResponse(String input) {
        String response = "";
        try {
            Command c = parser.parseCommand(input);
            if (c == null) {
                throw new SunpterException("Invalid command");
            }
            return c.execute(roster, ui, storage);
        }
        catch (SunpterException | IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }
}