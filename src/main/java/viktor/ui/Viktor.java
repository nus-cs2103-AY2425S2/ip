package viktor.ui;

import java.io.IOException;
import java.util.ArrayList;

import viktor.commands.Commandable;
import viktor.exceptions.ViktorException;
import viktor.parser.Parser;
import viktor.storage.Storage;
import viktor.tasks.Task;
import viktor.tasks.TaskList;

/**
 * The Viktor class represents the main class for the Viktor application.
 * It initializes the UI, task list, and parser, and handles the loading of tasks from storage.
 */
public class Viktor {

    private UI ui;
    private TaskList taskList;
    private Parser parser;

    /**
     * Constructs a new Viktor instance, initializing the UI, task list, and parser.
     * Loads tasks from storage and sets them in the task list.
     */
    public Viktor() {
        ui = new UI();
        taskList = new TaskList();
        parser = new Parser();

        try {
            ArrayList<Task> loadedTasks = Storage.load();
            taskList.setTasks(loadedTasks);
        } catch (IOException e) {
            System.out.println("Welcome to Viktor Chatbot!");
        }
    }

    public void reset() {
        taskList.clear(); // Clear the current task list
    }

    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    public String getStartMessage() {
        return ui.getStartMessage();
    }


    public String getResponse(String input) {
        try {
            Commandable command = parser.parse(input, taskList, false);
            return command.execute();
        } catch (ViktorException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Something went wrong! Very wrong! Hextech is alive!";
        }
    }
}
