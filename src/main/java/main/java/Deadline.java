package main.java;

import Darartole.exception.EmptyBotException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    /* The datetime of the deadline */
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        this.by = LocalDateTime.parse(by, formatter);
    }
    /**
     * Returns the string format of deadline
     * 
     * @return the string that is printed
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    /**
     * Converts the deadline task to the string that can be written to the file
     * 
     * @return the string that is going to be written on txt file
     */
    public String toFileForm() {
        return "D" + " | " + super.toFileForm() + " | " + this.by;
    }

    /**
     * Converts the string recorded in the txt to a deadline object
     *
     * @param isDone whether the recorded task has been finished or not.
     * @param description string in the txt file
     * @param by string of time in the txt file
     * @return deadline object from the string in the txt file
     */
    public static Deadline fromFileForm(boolean isDone, String description, String by) {
        Deadline ddl = new Deadline(description, by);
        if (isDone) {
            ddl.setDone();
        }
        return ddl;
    }

    /**
     * Adds the deadline to the tasklist after the user input the deadline command
     *
     * @param input the input string command.
     * @param tasks the tasklist in the chatbot.
     * @param fileStored the storage in the chatbot.
     * @return string reply to the user input command.
     */
    public static String addDeadline(String input, Tasklist tasks, Storage fileStored) {
        try {
            String following = input.substring(8).trim();
            if (following.isEmpty()) {
                throw new EmptyBotException("Cannot be empty task.");
            }
            if (!following.contains("/by")) {
                throw new EmptyBotException("You need to use /by to indicate the due time.");
            }

            String[] parts = following.split("/by");
            String taskDescription = parts[0].trim();
            String deadline = parts[1].trim();

            if (deadline.isEmpty()) {
                throw new EmptyBotException("Please give me the date and time for deadline.");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime parsedDeadline;
            try {
                parsedDeadline = LocalDateTime.parse(deadline, formatter);
            } catch (DateTimeParseException e) {
                return "ILLEGAL INPUT! You need to input the data and time as following format: yyyy-MM-ddTHH:mm. ";
            }
            Deadline ddl = new Deadline(taskDescription, deadline);
            tasks.addTask(ddl);
            fileStored.save(tasks);
            return ddl.toString() + "\n" + "Now you have " + tasks.size() + " tasks in the list.";
        } catch (EmptyBotException e) {
            return "ILLEGAL INPUT!" + "\n" + e.getMessage();
        }
    }

}
