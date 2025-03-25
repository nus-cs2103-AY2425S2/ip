package buddy;

import buddy.command.Command;
import buddy.display.Display;
import buddy.exception.BuddyException;
import buddy.parser.Parser;
import buddy.storage.DataStorage;
import buddy.task.TaskList;


/**
 * Represents Buddy chatbot
 */
public class Buddy {
    private TaskList taskList;
    private Display display;
    private DataStorage dataStorage;

    /**
     * Constructor for Buddy class.
     */
    public Buddy() {
        this.display = new Display();
        this.dataStorage = new DataStorage("./data/buddy.txt");
        try {
            this.taskList = new TaskList(this.dataStorage.loadData());
        } catch (BuddyException e) {
            System.out.println(Display.showError(e));
            this.taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Buddy().start();
    }

    /**
     * Gets response from executing command.
     *
     * @param input the input
     * @return the command response
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            String response = command.execute(taskList, dataStorage);
            assert response != null : "Response should not be empty";
            return response;
        } catch (BuddyException error) {
            return Display.showError(error);
        }
    }

    /**
     * Starts the Buddy chatbot.
     */
    private void start() {
        boolean isBye = false;
        while (!isBye) {
            String userInput = this.display.readInput();
            try {
                Command command = Parser.parseCommand(userInput);
                isBye = command.getExitStatus();
                System.out.println(command.execute(taskList, dataStorage));
            } catch (BuddyException error) {
                System.out.println(Display.showError(error));
            }
        }
        this.display.closeInput();
    }
}
