package mars;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import mars.storage.Storage;
import mars.task.Deadline;
import mars.task.Event;
import mars.task.Task;
import mars.task.Todo;
import mars.task.TaskList;
import mars.parser.Parser;
import mars.command.Command;


import mars.ui.UI;

/**
 * Mars class is the entry point of the task bot's execution
 */

public class Mars {
    private TaskList tasks;
    private Storage storage;
    private UI ui;

    /**
     * Constructor to instantiate Mars instance with
     * the file path of marsBot.txt
     *
     * @param filePath The file path of marsBot.txt storing the tasks
     */
    public Mars(String filePath) throws IOException{
        this.ui = new UI();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }


    public String getResponse(String input) {
        assert input != null: "input must not be null";
        try {
            Command c = Parser.parse(input);
            assert c != null : "Command cannot not be null.";
            c.execute(tasks, ui, storage);
            return ui.getResponse();
        } catch (marsException e) {
            return e.getMessage();
        }
    }

    public String getUserGuide() {
        return ui.userGuide();
    }

    public String getWelcomeMessage() {
        return ui.welcomeMessage();
    }

}
