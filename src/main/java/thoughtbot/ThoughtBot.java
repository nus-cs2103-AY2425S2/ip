package thoughtbot;

import exceptions.ThoughtBotException;
import usercommands.UserCommand;
import usercommands.UserCommandDeadline;
import usercommands.UserCommandDelete;
import usercommands.UserCommandEvent;
import usercommands.UserCommandFind;
import usercommands.UserCommandMarkUnmark;
import usercommands.UserCommandTodo;

/**
 * The ThoughtBot class is the main class for the chatbot that helps you
 * keep track of tasks.
 */
public class ThoughtBot {

    private String responseString = "";
    private TaskList myList = SaveLoad.load();

    public String getResponse(String userInput) {
        if (!userInput.equalsIgnoreCase("bye")) {
            try {
                UserCommand uc = Parser.parseInput(userInput);
                switch (uc.getCommandType()) {
                case LIST:
                    responseString = myList.getTaskList();
                    break;
                case TODO:
                    UserCommandTodo todo = (UserCommandTodo) uc;
                    responseString = myList.addTodo(todo.getTaskName());
                    responseString += myList.getTaskNumbersString();
                    break;
                case DEADLINE:
                    UserCommandDeadline deadline = (UserCommandDeadline) uc;
                    responseString = myList.addDeadline(deadline.getTaskName(), deadline.getDeadline());
                    responseString += myList.getTaskNumbersString();
                    break;
                case EVENT:
                    UserCommandEvent event = (UserCommandEvent) uc;
                    responseString = myList.addEvent(event.getTaskName(), event.getFromTime(), event.getToTime());
                    responseString += myList.getTaskNumbersString();
                    break;
                case DELETE:
                    UserCommandDelete delete = (UserCommandDelete) uc;
                    responseString = myList.deleteTask(delete.getDeleteNumber());
                    responseString += myList.getTaskNumbersString();
                    break;
                case FIND:
                    UserCommandFind find = (UserCommandFind) uc;
                    responseString = myList.findTasks(find.getFindString());
                    break;
                case MARK:
                    UserCommandMarkUnmark mark = (UserCommandMarkUnmark) uc;
                    responseString = myList.markEntry(mark.getMarkUnmarkNumber());
                    break;
                case UNMARK:
                    UserCommandMarkUnmark unmark = (UserCommandMarkUnmark) uc;
                    responseString = myList.unmarkEntry(unmark.getMarkUnmarkNumber());
                    break;
                case REMIND:
                    responseString = myList.remindTasks();
                    break;
                default:
                    break;
                }
            } catch (ThoughtBotException e) {
                responseString = e.getMessage();
            } finally {
                System.out.println(responseString);
            }
            assert !responseString.isBlank() : "Blank response was given by the ThoughtBot class";

            return responseString;
        } else {
            SaveLoad.save(myList);
            return "bye given";
        }
    }
}
