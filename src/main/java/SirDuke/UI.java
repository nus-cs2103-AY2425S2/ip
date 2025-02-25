package SirDuke;

import SirDuke.backend.task.Task;

public class UI {

    public static String sayBye() {
        return "Godspeed.";
    }

    public static String markTaskAsDone(Task task) {
        return "Well done, I have marked " + task.getDescription() + " as done.";
    }

    public static String unmarkTaskAsDone() {
        return "Understood, I have unmarked this task as done.";
    }

    public static String informThatTaskDeleteIsSuccessful() {
        return "Very well, I have deleted this task.";
    }

    public static String sayHello() {
        return "It's a pleasure to meet you. My name is Sir Duke Ellington. What can I do you for?\"";
    }

    public static String informThatTaskHasBeenCreated(Task task) {
        StringBuilder temp = new StringBuilder();
        temp.append("I have added the following ");
        temp.append(task.getTaskType().toLowerCase());
        temp.append(" to your list: ");
        temp.append(task.getDescription());
        return temp.toString();
    }
    public static String informThatCommandIsInvalid() {
        return "I'm afraid I don't understand what you mean.";
    }

    public static String informThatCommandIsIncomplete() {
        return "Your command is incomplete.";
    }

    public static String informThatTaskDoesNotExist() {
        return "I do not have this task in my list.";
    }

    public static String informThatTaskIndexIsInvalid() {
        return "The task index you have provided me with is invalid. Try the same command with an integer instead.";
    }

    public static String informThatTaskIsNotFound() {
        return "Unfortunately, I do not see any tasks with that keyword in my list.";
    }

    public static String informThatDateTimeIsInvalid() {
        return "One or more of your dates do not follow a format I understand." +
                " Use the following format: dd/MM/yyyy HHmm";
    }

    public static String informThatTaskHasBeenEdited() {
        return "I have successfully updated the requested task";
    }

    public static String informThatCommandDoesNotWorkOnTask(Task task) {
        return "I'm afraid this command does not work on " + task.getTaskType().toLowerCase() + " tasks.";
    }
}
