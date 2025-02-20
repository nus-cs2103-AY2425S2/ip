package nana.logic;

import java.util.ArrayList;

/**
 * The Parser class provides methods to parse user input into commands and arguments.
 */
public class Parser {

    /**
     * Parses the mark task command.
     *
     * @param info the list of strings containing the command and arguments
     * @return the index of the task to be marked
     * @throws NanaException if the task index is not provided
     */
    public static int parseMarkTask(ArrayList<String> info) throws NanaException {
        if (info.size() == 1) {
            throw new NanaException("The marked task can't be empty.");
        }
        return Integer.parseInt(info.get(1));

    }

    /**
     * Parses the unmark task command.
     *
     * @param info the list of strings containing the command and arguments
     * @return the index of the task to be unmarked
     * @throws NanaException if the task index is not provided
     */
    public static int parseUnmarkTask(ArrayList<String> info) throws NanaException {
        if (info.size() == 1) {
            throw new NanaException("The unmarked task can't be empty.");
        }
        return Integer.parseInt(info.get(1));

    }

    /**
     * Parses the add todo command.
     *
     * @param info the list of strings containing the command and arguments
     * @return a list of strings containing the parsed task description
     * @throws NanaException if the task description is not provided
     */
    public static ArrayList<String> parseAddTodo(ArrayList<String> info) throws NanaException {

        if (info.size() == 1) {
            throw new NanaException("The description of a todo cannot be empty.");
        }

        ArrayList<String> parsed = new ArrayList<>();
        String taskName = "";
        info.remove(0);
        for (String element : info) {
            taskName += element + " ";
        }
        parsed.add(taskName);
        return parsed;

    }

    /**
     * Parses the add deadline command.
     *
     * @param info the list of strings containing the command and arguments
     * @return a list of strings containing the parsed task description and deadline
     * @throws NanaException if the task description or deadline is not provided
     */
    public static ArrayList<String> parseAddDeadline(ArrayList<String> info) throws NanaException {

        if (info.size() == 1) {
            throw new NanaException("The description of a deadline cannot be empty.");
        }

        ArrayList<String> parsed = new ArrayList<>();
        String taskName = "";
        info.remove(0);
        for (String element : info) {
            if (element.equals("/by")) {
                break;
            }
            taskName += element + " ";
        }

        String by = info.get(info.size() - 1);
        parsed.add(taskName);
        parsed.add(by);

        return parsed;
    }

    /**
     * Parses the add event command.
     *
     * @param info the list of strings containing the command and arguments
     * @return a list of strings containing the parsed task description, start time, and end time
     * @throws NanaException if the task description, start time, or end time is not provided
     */
    public static ArrayList<String> parseAddEvent(ArrayList<String> info) throws NanaException {
        if (info.size() == 1) {
            throw new NanaException("The description of an event cannot be empty.");
        }

        ArrayList<String> parsed = new ArrayList<>();
        String taskName = "";
        info.remove(0);
        for (String element : info) {
            if (element.equals("/from")) {
                break;
            }
            taskName += element + " ";
        }
        String startTime = info.get(info.size() - 4) + " " + info.get(info.size() - 3);
        String endTime = info.get(info.size() - 1);

        parsed.add(taskName);
        parsed.add(startTime);
        parsed.add(endTime);

        return parsed;
    }

    /**
     * Parses the delete task command.
     *
     * @param info the list of strings containing the command and arguments
     * @return the index of the task to be deleted
     * @throws NanaException if the task index is not provided
     */
    public static int parseDeleteTask(ArrayList<String> info) throws NanaException {
        if (info.size() == 1) {
            throw new NanaException("The deleted task can't be empty.");
        }
        return Integer.parseInt(info.get(1));

    }

    /**
     * Parses the find task command.
     *
     * @param info the list of strings containing the command and arguments
     * @return a list of strings containing the parsed keyword to find
     * @throws NanaException if the keyword is not provided
     */
    public static ArrayList<String> parseFindTask(ArrayList<String> info) throws NanaException {
        if (info.size() == 1) {
            throw new NanaException("The keyword to find cannot be empty.");
        }

        ArrayList<String> parsed = new ArrayList<>();
        String keyword = "";
        info.remove(0);
        for (String element : info) {
            keyword += element + " ";
        }
        parsed.add(keyword);
        return parsed;
    }

}

