package main.java;

import Darartole.exception.EmptyBotException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    /* datetime of the from timestamp */
    protected LocalDateTime from;
    /* datetime of the to timestamp */
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    /**
     * Returns the string format of the event
     * 
     * @return the string that is going to be printed
     */
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
    
    /**
     * Converts the event object to the string that can be written on the txt file.
     * 
     * @return the string that is going to be written on the txt file. 
     */
    public String toFileForm() {
        return "E" + " | " + super.toFileForm() + " | " + this.from + " | " + this.to;
    }

    /**
     * Converts the string in the txt file to the event object
     *
     * @param isDone whether the event has been finished.
     * @param description the string description of the event in txt file.
     * @param from the string timing in txt file.
     * @param to the string timing in txt file.
     * @return event object.
     */
    public static Event fromFileForm(boolean isDone, String description, String from, String to) {
        Event event = new Event(description, from, to);
        if (isDone) {
            event.setDone();
        }
        return event;
    }

    /**
     * Adds event after the user input command
     *
     * @param input the input command by the user to add event.
     * @param tasks the tasklist in the chatbot.
     * @param fileStored the storage in the chatbot.
     * @return the reply message to the user.
     */
    public static String addEvent(String input, Tasklist tasks, Storage fileStored) {
        try {
            String following = input.substring(5).trim();
            if (following.isEmpty()) {
                throw new EmptyBotException("Cannot be empty task.");
            }

            if (!following.contains("/from") || !following.contains("/to")) {
                throw new EmptyBotException("You need to use /from and /to.");
            }
            String[] partsFrom = following.split("/from");
            String[] partsTo = partsFrom[1].split("/to");
            String taskDescription = partsFrom[0].trim(); // Task description before "/from"
            String fromTime = partsTo[0].trim(); // Time after "/from"
            String toTime = partsTo[1].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime fromDateTime;
            LocalDateTime toDateTime;

            try {
                fromDateTime = LocalDateTime.parse(fromTime, formatter);
                toDateTime = LocalDateTime.parse(toTime, formatter);
            } catch (DateTimeParseException e) {
                return "ILLEGAL INPUT! You need to input the data and time as following format: yyyy-MM-ddTHH:mm. "
                        + "if the format is correct, please check the validity of the date and time. ";
            }

            // Validate that the "from" time is not after the "to" time
            if (fromDateTime.isAfter(toDateTime)) {
                return "ILLEGAL INPUT! The 'from' date cannot be after the 'to' date.";
            }

            Event event = new Event(taskDescription, fromTime, toTime);
            tasks.addTask(event);
            fileStored.save(tasks);
            return event.toString() + "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        } catch (EmptyBotException e) {
            return "ILLEGAL INPUT!" + "\n" + e.getMessage();
        }
    }
 
}
