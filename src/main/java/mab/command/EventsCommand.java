package mab.command;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import mab.task.Task;
import mab.task.Events;
import mab.util.MabStorage;
import mab.MabException;

/**
 * Handles creation of event tasks with start and end times.
 * Requires command arguments in the format: "description /from YYYY-MM-DD HH:mm /to YYYY-MM-DD HH:mm".
 *
 * @param args The full command string following the "event" keyword
 */ 
public class EventsCommand extends Command {
    public EventsCommand(String args) {
        super(args);
    }

     /**
     * Parses event details and adds to task list.
     *
     * @param list The target task list for insertion
     * @throws MabException If arguments violate syntax rules or datetime format
     */
    @Override
    public String execute(ArrayList<Task> list) throws MabException {
        if (args.isBlank()) {
            throw new MabException("description cannot be empty, try using: \"event [description] /from [time] /to [time]\"");
        }
        String[] description = args.split("/from", 2);
        if (description.length < 2) {
            throw new MabException("time cannot be empty, try using: \"event [description] /from [time] /to [time]\"");
        }
        String[] fromTo = description[1].split("/to", 2);
        if (description.length < 2) {
            throw new MabException("time cannot be empty, try using: \"event [description] /from [time] /to [time]\"");
        }
        if(description[0].isBlank() || fromTo[0].isBlank() || fromTo[1].isBlank()) {
            throw new MabException("fields cannot be blank, try using: \"event [description] /from [time] /to [time]\"");
        }

        try{
            Events newTask = new Events(description[0], false, fromTo[0], fromTo[1]);
            list.add(newTask);
            new MabStorage().write(list);

            return String.format("I Added a new Event: %s. Have fun!", newTask.toString());
        } catch (DateTimeParseException e) {
            throw new MabException("Invalid date time format, try using: DD-MM-YYYY HH:MM");
        }
    }
}
