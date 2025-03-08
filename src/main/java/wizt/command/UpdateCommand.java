package wizt.command;

import java.util.ArrayList;

import wizt.storage.Storage;
import wizt.task.Task;
import wizt.task.TaskList;
import wizt.ui.Ui;


/**
 *  Represents commands that update tasks status into TasksList
 */

public class UpdateCommand extends Command {
    private String input;
    private String assertMessage = "\n Invalid task number! Please choose a valid number from the task list.";
    public UpdateCommand() {
        super();
    }

    public UpdateCommand(String input1) {
        this.input = input1;
    }

    /**
     * Identify which update command user has inputted and mark accordingly
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder();
        try {
            ArrayList<Task> tasklists = tasks.getTasksList();
            if (tasklists.isEmpty()) {
                response.append("\n You have no tasks in your list to update.");
                return response.toString();
            }
            if (input.contains("unmark")) {
                boolean isError = parseUnMark(input, response, tasklists);
                if (isError) {
                    return response.toString();
                }
            } else {
                if (input.contains("mark")) {
                    boolean isError = parseMark(input, response, tasklists);
                    if (isError) {
                        return response.toString();
                    }
                } else if (input.contains("update")) {
                    boolean isError = parseUpdate(input, response, tasklists);
                    if (isError) {
                        return response.toString();
                    }
                }
            }
        } catch (AssertionError e) {
            response.append(assertMessage);
            return response.toString();
        }
        return response.toString();
    }

    /**
     * Represents a method to parse unmark
     * @param input
     * @param response
     */
    public boolean parseUnMark(String input, StringBuilder response, ArrayList<Task> tasklists) {
        String[] split = input.split(" ");
        assert split.length == 2 : assertMessage;
        if (split.length != 2) {
            response.append("\n Please enter a valid task number!");
            return true;
        }
        int no = Integer.parseInt(split[1]);
        assert no > 0 && no <= tasklists.size() : assertMessage;
        if (!(no > 0 && no <= tasklists.size())) {
            throw new AssertionError(assertMessage);
        }
        tasklists.get(no - 1).unmarkAsDone();
        response.append("Roger Boss, I've marked this task as not done yet:")
                .append(tasklists.get(no - 1).toString());
        return false;
    }

    /**
     * Represent a method to parse mark
     * @param input
     * @param response
     * @param tasklists
     * @return
     */
    public boolean parseMark(String input, StringBuilder response, ArrayList<Task> tasklists) {
        String[] split = input.split(" ");
        assert split.length == 2 : assertMessage;
        if (split.length != 2) {
            response.append("\n Please enter a valid task number!");
            return true;
        }
        int no = Integer.parseInt(split[1]);
        assert no > 0 && no <= tasklists.size() : assertMessage;
        if (!(no > 0 && no <= tasklists.size())) {
            throw new AssertionError(assertMessage);
        }
        tasklists.get(no - 1).markAsDone();
        response.append("\n Nice! I've marked this task as done:")
                .append(tasklists.get(no - 1).toString());
        return false;
    }

    /**
     * Represents a method to parse updates
     * @param input
     * @param response
     * @param tasklists
     * @return
     */
    public boolean parseUpdate(String input, StringBuilder response, ArrayList<Task> tasklists) {
        String[] split = input.split(" ");
        if (split.length != 3) {
            response.append("\n Please enter a valid task number and description!");
            return true;
        }
        int no = Integer.parseInt(split[1]);
        String newDescription = split[2];
        assert no > 0 && no <= tasklists.size() : assertMessage;
        if (!(no > 0 && no <= tasklists.size())) {
            throw new AssertionError(assertMessage);
        }
        tasklists.get(no - 1).update(newDescription);
        response.append("\n Nice! I've updated this task")
                .append(tasklists.get(no - 1).toString());
        return false;
    }
}
