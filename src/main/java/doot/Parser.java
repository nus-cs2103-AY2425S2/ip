package doot;

import doot.commands.*;

import java.io.IOException;

/**
 * This class is for interpreting what the user means. It contains a Tasklist so it can edit it
 */
public class Parser {
    public TaskList list;

    public Parser(TaskList list) {
        assert list != null: "primary tasklist shouldnt be null. Under Parser constructor";
        this.list = list;
    }



    /**
     * Interprets the input in the parameter as a user command, and delegates the task
     * @param userInput comes from what user enters into the terminal
     */
    //i asked chatgpt how to make this method cleaner, because the original was a bunch of if statements strung together
    //it was very ugly
    public String handleCommand(String userInput) throws IOException, InvalidFormatException {
        assert userInput != null: "userinput under Parser.handleCommand is null, something went wrong";
        if (userInput.isEmpty()) {
            return respond("say something I'm giving up on you");
        }

        return switch (userInput) {
            case "list" -> respond(list.returnList());
            case "listData" -> respond(list.listData());
            case "Yukkuri shiteitte ne!" -> respond("Take it easy!");
            case "bye" -> respond("Remember to take it easy!");
            case "help" -> respond(BasicCommands.help);
            default -> handleDynamicCommands(userInput);
        };
    }

    private String handleDynamicCommands(String userInput) throws InvalidFormatException, IOException {
        assert !userInput.isBlank() : "handleDynamicCommands userInput is blank";
        if (Parser.isMark(userInput)) {
            return new HandleMarkCommand(userInput, list).execute();
        }
        if (Parser.isUnMark(userInput)) {
            return new HandleUnmarkCommand(userInput, list).execute();
        }
        if (userInput.startsWith("delete ")) {
            return new HandleDeleteCommand(list, userInput).execute();
        }
        if (userInput.startsWith("find ")) {
            return new HandleFindCommand(list, userInput).execute();
        }
        if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
            return new AddTaskCommand(list, userInput).execute();
        }
        return "Task not understood!";
    }

    private String respond(String message) {
        Ui.showMessage(message);
        return message;
    }



    /**
     * determines if the index is valid for commands like delete or mark, like if there are only 3 tasks the command
     * unmark 4 will return a false
     * @param index is the task the user wants unmarked
     * @return whether that index is valid
     */
    public static boolean isValidIndex(int index, TaskList list) {
        return index >= 0 && index < list.size();
    }

    /**
     * determines if the command is a mark command, and if it is valid
     * @param str it takes in the entire userInput
     * @return whether it is a mark command
     */
    public static boolean isMark(String str) {
        if (!str.startsWith("mark ") && !str.startsWith("Mark ")) {
            return false;
        }
        String[] arr = str.split(" ");
        if (arr.length != 2) {
            return false;
        }
        try {
            Integer.parseInt(arr[1]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    /**
     * determines if this is an unmark command, and checks if it is valid
     * @param str entire userinput
     * @return whether it is a valid unmark command
     */
    public static boolean isUnMark(String str) {
        if (str.startsWith("unmark ") || str.startsWith("Unmark ")) {
            String[] arr = str.split(" ");
            if (arr.length != 2) {
                return false;
            }
            try {
                Integer.parseInt(arr[1]);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Used to parse the details to make the corresponding TodoTask
     * @param details the userInput string but without the todo at the front
     * @return the TodoTask to be created
     */
    public static TodoTask parseTodo(String details) {
        String tag = null;
        if (details.contains("/tag ")) {
            int index = details.indexOf("/tag ");
            tag = details.substring(index + 5).strip();
            details = details.substring(0, index);
        }
        TodoTask task = new TodoTask(details);
        task.setTag(tag);
        return task;
    }

    public static DeadlineTask parseDeadline(String details) throws InvalidFormatException {
        String tag = null;
        if (details.contains("/tag ")) {
            int index = details.indexOf("/tag ");
            tag = details.substring(index + 5).strip();
            details = details.substring(0, index);
        }

        String[] parts = details.split("/by", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new InvalidFormatException("Deadline tasks require /by.\n Do it again.");
        }

        String name = parts[0].trim();
        String deadline = parts[1].trim();

        if (name.isEmpty()) {
            throw new InvalidFormatException("You need something before '/by'.\n Do better.");
        }
        DeadlineTask task = new DeadlineTask(name, deadline);
        task.setTag(tag);
        return task;
    }

    public static EventTask parseEvent(String details) throws InvalidFormatException {
        String tag = null;
        if (details.contains("/tag ")) {
            int index = details.indexOf("/tag");
            tag = details.substring(index + 5).strip();
            details = details.substring(0, index);
        }

        String[] parts = details.split("/from|/to", 3);
        if (parts.length < 3) {
            throw new InvalidFormatException("Wrong.\n You need both '/from' and '/to' keywords for event.");
        }

        String name = parts[0].trim();
        String start = parts[1].trim();
        String end = parts[2].trim();

        if (name.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new InvalidFormatException("Make sure there's text between 'event', '/from', and '/to'.\n Do not make me repeat myself");
        }
        EventTask task = new EventTask(name, start, end);
        task.setTag(tag);

        return task;
    }

    /**
     * Counts the number of times a substring appears in the superstring. Used for determining if there's multiple tags
     * where there shouldn't be. If there is more than 1 returns true, else returns false
     * @param text the superstring, the userInput
     * @param substring the keyword
     * @return if the substring appears in the superstring multiple times
     */
    //Thanks chatgpt. I was looking for a cleaner way but apparently this is the best
    public static boolean keywordChecker(String text, String substring) {
        if (text == null || substring == null || substring.isEmpty()) {
            return false;
        }

        int count = 0;
        int index = 0;

        while ((index = text.indexOf(substring, index)) != -1) {
            count++;
            index += substring.length();
        }

        return count > 1;
    }
}
