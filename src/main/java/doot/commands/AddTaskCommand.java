package doot.commands;

import doot.InvalidFormatException;
import doot.Storage;
import doot.TaskList;

import java.io.IOException;

public class AddTaskCommand implements Command{
    public TaskList list;
    public String userInput;

    public AddTaskCommand(TaskList list, String userInput) {
        this.list = list;
        this.userInput = userInput;
    }

    public String execute() throws InvalidFormatException, IOException {
        String temp = list.addTask(userInput);
        Storage.saveList(list);
        return temp;
    }

}
