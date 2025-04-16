package pluto;

import javafx.application.Platform;

/**
 * Represents the main Pluto class. This class
 * runs the main function that starts the chatbot
 */
public class Pluto {
    private TaskList taskList;
    private Message message;
    private Storage storage;
    private Parser parser;

    /**
     * Creates the chatbot Pluto
     */
    public Pluto() {
        this.message = new Message();
        this.storage = new Storage("tasks.txt");
        this.taskList = new TaskList(storage.loadTasks());
        this.parser = new Parser(taskList);
    }

    /**
     * Runs the chatbot's main program
     */
    public String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                Platform.exit();
                return message.showGoodbyeMessage();
            }
            String response = parser.parse(input);
            storage.saveTasks(taskList.getTaskList());
            return response;
        } catch (PlutoException e) {
            return "OOPS! " + e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "OOPS! Task number is out of range";
        } catch (NumberFormatException e) {
            return "OOPS! Task number must be a valid number" ;
        }
    }

    public String displayWelcomeMessage() {
        return message.showWelcomeMessage();
    }
}
