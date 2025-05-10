package elmacho;

import command.ArchiveCommand;
import command.ArchiveListCommand;
import command.Command;
import command.UnarchiveCommand;
import exceptions.ElmachoException;
import parser.Parser;
import storage.Storage;
import task.Tasklist;
import ui.Ui;

public class Elmacho {

    private final Storage storage;
    private final Storage archivedStorage;
    private final Ui ui;
    private Tasklist tasklist;
    private Tasklist archivedTasklist;
    private Parser parser;

    public Elmacho() {
        this.ui = new Ui();
        this.storage = new Storage("files/ElMacho.txt");
        assert storage != null: "Storage should not be null.";

        this.tasklist = new Tasklist();
        this.archivedTasklist = new Tasklist();
        this.tasklist = storage.load(tasklist, archivedTasklist);

        this.archivedStorage = new Storage("files/ElMachoArchived.txt");
        this.archivedTasklist = archivedStorage.load(archivedTasklist, tasklist);
        this.parser = new Parser();
        ui.start();
    }

    public Tasklist getTasklist() {
        return tasklist;
    }

    public Ui getUi() {
        return ui;
    }


    public String getResponse(String input) {
        assert input != null : "Input should not be null.";
        try {
            Command command = parser.parse(input);
            assert command != null : "Command should not be null.";

            if (command instanceof ArchiveCommand || command instanceof UnarchiveCommand
                    || command instanceof ArchiveListCommand) {
                command.execute(tasklist, archivedTasklist, ui);
                archivedStorage.updateList(archivedTasklist);
            } else {
                command.execute(tasklist, archivedTasklist, ui);
                storage.updateList(tasklist);
            }

            return ui.getLatestResponse();

        } catch (ElmachoException e) {
            return e.getMessage();
        }
    }

    public String getWelcomeMessage() {
        return ui.start();
    }

    public String getUserGuide() {
        return ui.userGuide();
    }
}

