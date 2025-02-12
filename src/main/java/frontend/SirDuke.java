package frontend;

import java.time.format.DateTimeParseException;

import backend.exceptions.IllegalStartAndEndDateException;
import backend.ToDoList;
import backend.Storage;

import static frontend.Format.HORIZONTAL_LINE;
/**
 * Class that acts as the User Interface. This class is responsible for printing
 * all prompts and responses.
 */
public class SirDuke {

    public static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    private Storage storage;
    private ToDoList taskList;

    private Parser parser;

    /**
     * Creates new SirDuke chatbot class
     */
    public SirDuke() {
        this.storage = new Storage("./data/SirDuke.txt");
        this.taskList = new ToDoList();
        this.parser = new Parser(this);
    }

    /**
     * Prints exit message and exits.
     */
    public void sayBye() {
        System.out.println(HORIZONTAL_LINE + "\n"
                + "Godspeed.\n"
                + HORIZONTAL_LINE);
        System.exit(0);
    }

    public void markTaskAsDone(int index) {
        try {
            this.taskList.markTaskAsDone(index);
            System.out.println(HORIZONTAL_LINE + "\n");
            System.out.println("Well done, I have marked this task as done.");
            System.out.println(HORIZONTAL_LINE + "\n");
        } catch (NumberFormatException e) { //index provided after "mark" is not a number
            System.out.println(HORIZONTAL_LINE + "\n");
            System.out.println("You have not provided me with a valid task index. " +
                    "Try the same command with an integer instead.");
            System.out.println(HORIZONTAL_LINE + "\n");
        } catch (IndexOutOfBoundsException e) {
            this.informThatTaskDoesNotExist();
        }
    }

    public void unmarkTaskAsDone(int index) {
        try {
            taskList.unmarkTaskAsDone(index);
            System.out.println(HORIZONTAL_LINE + "\n");
            System.out.println("Understood, I have unmarked this task as done.");
            System.out.println(HORIZONTAL_LINE + "\n");
        } catch (NumberFormatException e) { //index provided after "mark" is not a number
            System.out.println(HORIZONTAL_LINE + "\n");
            System.out.println("You have not provided me with a valid task index. " +
                    "Try the same command with an integer instead.");
            System.out.println(HORIZONTAL_LINE + "\n");
        } catch (IndexOutOfBoundsException e) {
            this.informThatTaskDoesNotExist();
        }
    }

    public void deleteTask(int index) {
        try {
            taskList.deleteTask(index);
            System.out.println(HORIZONTAL_LINE + "\n");
            System.out.println("Very well, I have deleted this task .");
            System.out.println(HORIZONTAL_LINE + "\n");
        } catch (DateTimeParseException e) {
            System.out.println(HORIZONTAL_LINE + "\n");
            System.out.println("One or more of your dates do not follow a format I understand." +
                    " Use the following format: yyyy-mm-dd");
            System.out.println(HORIZONTAL_LINE + "\n");
        } catch (IllegalStartAndEndDateException e) {
            System.out.println(HORIZONTAL_LINE + "\n");
            System.out.println(e.toString());
            System.out.println(HORIZONTAL_LINE + "\n");
        } catch (IndexOutOfBoundsException e) {
            this.informThatTaskDoesNotExist();
        }
    }



    /**
     * Prints welcome message and allows the SirDuke chatbot to start receiving commands.
     * Reads user input from console and interprets them as commands.
     * Executes commands accordingly.
     * Updates and saves to listFile only if a command is SUCCESSFULLY EXECUTED.
     */
    public void start() {
        ToDoList taskList = new ToDoList();
        System.out.println(HORIZONTAL_LINE + "\n"
                + "It's a pleasure to meet you. My name is Sir Duke Ellington.\n"
                + "What can I do you for?\n"
                + HORIZONTAL_LINE);
        parser.start();
    }

    public void showList() {
        this.taskList.showList();
    }
    public static void main(String[] args) {
        SirDuke sirDuke = new SirDuke();
        sirDuke.start();
    }
    public void createToDoTask(String description) {
        taskList.createToDoTask(description);
        System.out.println(HORIZONTAL_LINE + "\n"
                + "I have added the following ToDo to your list: "+ description + "\n"
                + HORIZONTAL_LINE);
    }

    public void createDeadlineTask(String description, String toBeCompletedBy) {
        try {
            taskList.createDeadlineTask(description, toBeCompletedBy);
            System.out.println(HORIZONTAL_LINE + "\n"
                    + "I have added the following Deadline to your list: "+ description + "\n"
                    + HORIZONTAL_LINE);
        } catch (DateTimeParseException e) {
            System.out.println(HORIZONTAL_LINE + "\n");
            System.out.println("Your date does not follow a format I understand." +
                    "Use the following format: yyyy-mm-dd");
            System.out.println(HORIZONTAL_LINE + "\n");
        }
    }

    public void createEventTask(String description, String startTime, String endTime) {
        try {
            taskList.createEventTask(description, startTime, endTime);
        } catch (DateTimeParseException e) {
            System.out.println(HORIZONTAL_LINE + "\n");
            System.out.println("One or more of your dates do not follow a format I understand." +
                    " Use the following format: yyyy-mm-dd");
            System.out.println(HORIZONTAL_LINE + "\n");
        } catch (IllegalStartAndEndDateException e) {
            System.out.println(HORIZONTAL_LINE + "\n");
            System.out.println(e.toString());
            System.out.println(HORIZONTAL_LINE + "\n");
        }
    }

    public void informThatCommandIsInvalid() {
        System.out.println(HORIZONTAL_LINE + "\n");
        System.out.println("I'm afraid I don't understand what you mean.");
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    public void informThatCommandIsIncomplete() {
        System.out.println(HORIZONTAL_LINE + "\n");
        System.out.println("Your task description is incomplete. ");
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    public void informThatTaskDoesNotExist() {
        System.out.println(HORIZONTAL_LINE + "\n");
        System.out.println("I do not have this task in my list.");
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    public void informThatTaskIndexIsMissing() {
        System.out.println(HORIZONTAL_LINE + "\n");
        System.out.println("You have not provided me with a valid task index.");
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    public void informThatTaskIndexIsNotInteger() {
        System.out.println(HORIZONTAL_LINE + "\n");
        System.out.println("You have not provided me with a valid task index. " +
                "Try the same command with an integer instead.");
        System.out.println(HORIZONTAL_LINE + "\n");
    }
    public static void Main(String[] args) {
        SirDuke chatBot = new SirDuke();
        chatBot.start();
    }
}



