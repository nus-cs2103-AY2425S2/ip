package doot.commands;

import doot.InvalidFormatException;
import doot.Task;
import doot.TaskList;
import doot.Ui;

import java.io.IOException;

import static doot.Parser.isValidIndex;
import static doot.Storage.saveList;

public class HandleDeleteCommand implements Command {
    public TaskList list;
    public String userInput;

    /**
     * The constructor for a command that deletes a task from a tasklist
     * @param list the tasklist the command will search through
     * @param userInput the entire userInput
     */
    public HandleDeleteCommand(TaskList list, String userInput) {
        this.list = list;
        this.userInput = userInput;

    }


    /**
     * Executes the command that is prepared in the constructor
     * @return What CirnoBot should respond with in a string format
     * @throws IOException in case saving of the file doesnt work
     * @throws InvalidFormatException will be thrown if the userInput is not in the correct format
     */
    public String execute() throws IOException, InvalidFormatException {
        int index;
        try {
            index = Integer.parseInt(userInput.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new InvalidFormatException("The value you have provided is not valid");
        }
        if (isValidIndex(index, list)) {
            Task removed = list.getTask(index);
            list.removeTask(index);
            Ui.showMessage("Good job!\n   removed " + removed.getDetails() + "\n" + list.size()
                    + " more to do\n");
            saveList(list);
            return "Good job!\n   removed " + removed.getDetails() + "\n" + list.size()
                    + " more to do\n";
        } else {
            Ui.showMessage("too big/too small number");
            return "I cannot count that high/low!";
        }
    }
}
