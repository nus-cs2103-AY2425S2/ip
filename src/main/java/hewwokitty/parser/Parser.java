package hewwokitty.parser;

import hewwokitty.storage.Storage;
import hewwokitty.task.TaskList;
import hewwokitty.ui.Ui;

/**
 * Parser parses user input
 * Allows user to interact with the chatbot
 */
public class Parser {
    private String userInput = "";

    /**
     * Returns a Parser object that reads input from the user
     */
    public Parser() {

    }

    /**
     * Returns a boolean value indicating whether a command is valid and has been executed
     * @param userInput String containing input from the user
     * @param taskList TaskList from main that consists of Tasks that have been added by the user
     * @return false if command user input is "bye", else true
     */
    public boolean processUserCommands(String userInput, TaskList taskList) {
        assert userInput != null : "User input should not be null";
        assert taskList != null : "TaskList should not be null";
        this.userInput = userInput;
        String userString = this.userInput.toLowerCase();
        switch (userString.split(" ", 2)[0]) {
        case "bye":
            return false;
        case "list":
            System.out.print(taskList);
            break;
        case "mark":
            try {
                int markTaskNum = Integer.parseInt(userString.split(" ")[1]);
                taskList.markDone(markTaskNum);
                Storage.writeToFile(taskList);
            } catch (IllegalArgumentException e) {
                Ui.printInvalidTaskNumber();
            }
            break;
        case "unmark":
            try {
                int unmarkTaskNum = Integer.parseInt(userString.split(" ")[1]);
                taskList.unmarkDone(unmarkTaskNum);
                Storage.writeToFile(taskList);
            } catch (IllegalArgumentException e) {
                Ui.printInvalidTaskNumber();
            }
            break;
        case "todo":
        case "deadline":
        case "event":
            try {
                taskList.addTask(userString);
                Storage.writeToFile(taskList);
            } catch (IllegalArgumentException e) {
                Ui.printInvalidDate();
            }
            break;
        case "delete":
            int deleteTaskNum = Integer.parseInt(userString.split(" ")[1]);
            taskList.removeTask(deleteTaskNum);
            Storage.writeToFile(taskList);
            break;
        case "find":
            taskList.findTasks(userString);
            break;
        default:
            Ui.printInvalidCommand();
            break;
        }
        return true;
    }
}
