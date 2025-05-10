package parakeet;

import parakeet.command.Command;


import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * The Parakeet class represents the main application for managing tasks.
 * It interacts with the storage system, user interface, and task list to allow the user
 * to perform actions such as adding, removing, and viewing tasks.
 *
 * <p>This class initializes the necessary components, reads from storage, and listens
 * for user input. It processes the input using a parser and executes corresponding
 * commands until the user chooses to exit the program.</p>
 *
 * @author Your Name
 * @version 1.0
 * @since 2025-01-29
 */
public class Parakeet {
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    public Parakeet() {
        this.taskList = new TaskList();
        Path path = Paths.get("data", "parakeet.txt");
        this.storage = new Storage(path);
        storage.readFromFile(taskList);
        this.parser = new Parser();

    }

    /**
     * Runs the Parakeet application. This method:
     * - Reads tasks from storage
     * - Displays a welcome message
     * - Processes commands input by the user in an infinite loop
     * - Executes the corresponding command based on user input
     * - Terminates when the ExitCommand is issued
     */
    public String run(String input) {

           String command = input;
            try {
                Command parsedCommand = parser.parse(command);
                return parsedCommand.execute(taskList, storage);

            } catch (Exception error) {
               return error.getMessage();
            }
            
    }

    public String getResponse(String input) {
        return this.run(input);
    }

}

