package SirDuke;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import SirDuke.backend.Storage;
import SirDuke.backend.ToDoList;
import SirDuke.backend.command.Command;
import SirDuke.backend.task.Task;


/**
 * Class that acts as the User Interface. This class is responsible for printing
 * all prompts and responses.
 */
public class SirDuke {

    public static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    private Storage storage;
    private ToDoList toDoList;

    private Parser parser;

    /**
     * Creates new SirDuke chatbot class
     */
    public SirDuke() {
        this.storage = new Storage("./data/SirDuke.txt");
        ArrayList<Task> temp;
        try {
            temp = this.storage.readDataFromDisk(); //read data from file into arraylist
        } catch (FileNotFoundException e) {
            temp = new ArrayList<Task>(); //if file not found, create new arraylist
        }
        this.toDoList = new ToDoList(temp); //assign arraylist to taskList
        this.parser = new Parser(this.toDoList);
    }


    /**
     * Prints welcome message and allows the SirDuke chatbot to start receiving commands.
     * Reads user input from console and interprets them as commands.
     * Executes commands accordingly.
     * Updates and saves to listFile only if a command is SUCCESSFULLY EXECUTED.
     */




    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command c = parser.parse(input);
        if (c.isExit()) {
            // @@author Jason C
            // Reused from https://stackoverflow.com/questions/21974415/
            // how-to-close-this-javafx-application-after-showing-a-message-in-a-text-area-elem
            new Timer().schedule(new TimerTask() {
                public void run() {
                    shutdown();
                }
            }, 2500);
        }
        return c.execute(toDoList, storage);
    }

    /**
     * Clear resources and Shut down Reminderebot
     */
    public void shutdown() {
        System.exit(0);
    }
}



