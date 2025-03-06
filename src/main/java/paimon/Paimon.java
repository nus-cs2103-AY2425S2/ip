package paimon;

import paimon.commands.Command;
import paimon.parser.Parser;
import paimon.storage.Storage;
import paimon.tasklist.TaskList;
import paimon.ui.Ui;

/** 
 * Paimon is a chatbot that keeps track of tasks for users. 
 * It has a TaskList that stores the tasks and storage that saves the files
 * and UI that interacts with the user.
 */
public class Paimon {
    private TaskList items;
    private Storage storage;
    private Ui ui;

    /** 
     * Constructor for Paimon chatbot.
     */
    public Paimon() {
        this.items = new TaskList();
        this.storage = new Storage();
        this.ui = new Ui();

        Parser.setTasklist(this.items); // set the items for the parser

        // load the items
        this.storage.populateTaskList(this.items);
    }

    /**
     * Runs the main logic loop of paimon.
     *
     */
    public void run() {
        this.ui.greet();
        boolean isRunnning = true;
        
        while (isRunnning) {
            String str = this.ui.readCommand();
            Command c = Parser.parse(str);
            isRunnning = c.execute(items, ui);
            this.storage.save(this.items);
        }
    }

    public static void main(String[] args) {
        new Paimon().run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        String res = c.executeToString(this.items, this.ui);
        this.storage.save(this.items);
        return res;
    }

    /** 
     * Returns the welcome message from Paimon :D.
     */
    public String getWelcomeMessage() {
        return this.ui.getWelcomeMessage();
    }
}
