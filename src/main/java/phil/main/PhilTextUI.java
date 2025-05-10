package phil.main;

import phil.model.NoteList;
import phil.model.TaskList;
import phil.storage.Storage;
import phil.ui.Ui;

/**
 * Represents Main Class of Phil (TextUI version) to be executed for running the bot.
 */
public class PhilTextUI {

    /**
     * Initialises and executes the chatbot's TextUi version.
     */
    public static void main(String[] args) {

        TaskList taskList;
        NoteList noteList;
        Storage storage = new Storage("data", "phil.txt", "philNotes.txt");
        String loadMessage = "";
        try {
            taskList = storage.loadTasks();
            noteList = storage.loadNotes();
            loadMessage += "Successfully loaded previous notes.";
        } catch (Exception e) {
            taskList = new TaskList();
            noteList = new NoteList();
            loadMessage += "Error when loading notes. Not loaded. \n" + e.getMessage();
        }
        Parser parser = new Parser(taskList, noteList, storage);
        Ui ui = new Ui(parser);
        ui.printOutput(loadMessage);

        ui.runBot();
    }
}
