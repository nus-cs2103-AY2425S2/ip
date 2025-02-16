package Acheron;

import Acheron.Storage.StorageManager;
import Acheron.Tasks.TaskList;
import Acheron.Utility.CommandParser;

/**
 * Represents the chatbot
 */
public class Acheron {
    private static CommandParser commandParser;
    private static String message;
    private StorageManager storageManager;
    private TaskList taskList;

    /**
     * Constructor of the class
     * @param storagePath Indicates where the file is being saved to
     */
    public Acheron(String storagePath) {
        try {
            taskList = new TaskList();
            storageManager = new StorageManager(storagePath, taskList);
            commandParser = new CommandParser(storageManager, taskList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String getResponse(String input) {
        commandParser.parseInput(input);
        return message;
    }

    public static void setMessage(String msg) {
        message = msg;
    }
}
