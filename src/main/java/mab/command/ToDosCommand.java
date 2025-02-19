package mab.command;

import java.util.ArrayList;

import mab.MabException;
import mab.task.Task;
import mab.task.ToDos;
import mab.util.MabStorage;

/**
 * Handles creation and storage of todo tasks.
 */
public class ToDosCommand extends Command {
    /**
     * Creates a new todo command processor.
     * 
     * @param args The description text for the todo
     */
    public ToDosCommand(String args) {
        super(args);
    }

    /**
     * Adds a new todo task to the list and persists storage.
     * 
     * @param list The task list to modify
     * @throws MabException If description is empty
     */
    @Override
    public String execute(ArrayList<Task> list) throws MabException {
        if (args.isBlank()) throw new MabException("argument cannot be empty");
        
        ToDos newTask = new ToDos(args, false);
        list.add(newTask);
        new MabStorage().write(list);

        return String.format("Added new todo: %s\n", newTask.toString());
    }
}
