package main.java;
import java.util.Scanner;

import Darartole.exception.EmptyBotException;

/**
 * The class handling the commands
 */
public class Command {
    /**
     * Handles commands including mark, unmark and delete
     *
     * @param scanInput the Scanner that user input.
     * @param firstWord the first word in the user input.
     * @param tasks the tasklist of the chatbot.
     * @param fileStored the storage of the chatbot.
     * @return reply to the user.
     */
    public static String handleCommands(Scanner scanInput, String firstWord, Tasklist tasks, Storage fileStored) {
        int taskNo = scanInput.nextInt();
        String res;
        if (firstWord.equalsIgnoreCase("mark")) {
            res = tasks.mark(taskNo - 1);
            fileStored.save(tasks);
        } else if (firstWord.equalsIgnoreCase("unmark")) {
            res = tasks.unmark(taskNo - 1);
            fileStored.save(tasks);
        } else if (firstWord.equalsIgnoreCase("delete")) {
            StringBuilder reply = new StringBuilder();
            try {
                tasks.removeTask(taskNo - 1);
            } catch (EmptyBotException e) {
                res = "ILLEGAL INPUT!" + e.getMessage();
                return res;
            }
            fileStored.save(tasks);
            reply.append("I have removed the task for you.").append("\n")
                    .append("Now you have ").append(tasks.size()).append(" tasks in the list. ");
            res = reply.toString();
        } else {
            res = "I am SOOOO sorry! I do not understand your command.";
        }
        return res;
    }
}
