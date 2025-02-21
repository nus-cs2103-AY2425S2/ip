package grass;

import java.util.Scanner;

/**
* handles input and output of the program
* 
* @author gn07
* 
*/
public class Ui {
    protected static final String LINE = "____________________________________________________________";
    protected static final String LOGO = "  ___  ____   __   ____  ____ \n"
                    + " / __)(  _ \\ / _\\ / ___)/ ___) \n"
                    + "( (_ \\ )   //    \\\\___ \\\\___ \\ \n"
                    + " \\___/(__\\_)\\_/\\_/(____/(____/ \n";
    protected static final String INVALID = "What do you want...?";

    protected Scanner sc;
    protected String command;
    protected Parser parser;
    
    public Ui(TaskList tasks) {
        this.sc = new Scanner(System.in);
        this.command = "";
        this.parser = new Parser(tasks);
    }

    /**
     * <p>reads new user input</p>
     * @return string containing user input
     * @since 1.0
     */
    public String readCommand() {
        this.command = sc.nextLine();
        return command;
    }

    /**
     * <p>prints command recieved and a sperator</p>
     * @param command string containing user input
     * @since 1.0
     */
    public void commandSeparator(String command) {
        System.out.println(LINE + command);
        return;
    }

    /**
     * <p>get user input</p>
     * @return string containing user input
     * @since 1.0
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * <p>prints seperation line</p>
     * @since 1.0
     */
    public void printLine() {
        System.out.println(LINE);
        return;
    }

    /** 
     * <p>returns application startup message</p>
     * @return string containing startup message
     * @since 1.0
     */
    public String startup() {
        return "Hello! I'm" + LOGO + "What can I do for you?" ;
    }

    /** 
     * <p>returns application shutdown message</p>
     * @return string containing goodbye message
     * @since 1.0
     */
    public String shutdown() {
        return "Bye. Hope to see you again soon!" ;
    }

    /** 
     * <p>returns error message</p>
     * @param message error message to print
     * @return string containing error message
     * @since 1.0
     */
    public String errorMessage(String message) {
        return message;
    }

    /** 
     * <p>close scanner</p>
     * @since 1.0
     */
    public void closeScanner() {
        sc.close();
    }

    /** 
     * <p>returns outputs for 'list' command</p>
     * @return string containing list of tasks
     * @since 1.0
     */
    public String printList() {
        try {
            return parser.parseList();
        }catch (GrassException e) {
            return errorMessage(e.getMessage());
        }
    }

    /** 
     * <p>returns outputs for 'mark' command</p>
     * @param input string user input
     * @return string containing confirmation message
     * @since 1.0
     */
    public String markTask(String input) {
        try {
            return parser.parseMark(input);
        } catch (GrassException e) {
            return errorMessage(e.getMessage());
        }
    }

    /** 
     * <p>returns outputs for 'unmark' command</p>
     * @param input string user input
     * @return string containing confirmation message
     * @since 1.0
     */
    public String unmarkTask(String input) {
        try {
            return parser.parseUnmark(input);
        } catch (GrassException e) {
            return errorMessage(e.getMessage());
        }
    }
    
    /** 
     * <p>returns outputs for 'delete' command</p>
     * @param input string user input
     * @return string containing confirmation message
     * @since 1.0
     */
    public String deleteTask(String input) {
        try {
            return parser.parseDelete(input);
        } catch (GrassException e) {
            return errorMessage(e.getMessage());
        }
    }

    /** 
     * <p>returns outputs for 'todo' command</p>
     * @param input string user input
     * @return string containing confirmation message
     * @since 1.0
     */
    public String todoTask(String input) {
        try {
            return parser.todoTask(input);
        } catch (GrassException e) {
            return errorMessage(e.getMessage());
        }
    }

    /** 
     * <p>returns outputs for 'deadline' command</p>
     * @param input string user input
     * @return string containing confirmation message
     * @since 1.0
     */
    public String deadlineTask(String input) {
        try {
            return parser.deadlineTask(input);
        } catch (GrassException e) {
            return errorMessage(e.getMessage());
        }
    }

    /** 
     * <p>returns outputs for 'event' command</p>
     * @param input string user input
     * @return string containing confirmation message
     * @since 1.0
     */
    public String eventTask(String input) {
        try {
            return parser.eventTask(input);
        } catch (GrassException e) {
            return errorMessage(e.getMessage());
        }
    }

    /** 
     * <p>returns outputs for 'find' command</p>
     * @param input string user input
     * @return string containing list of tasks found
     * @since 1.0
     */
    public String findTask(String input) {
        return parser.findTask(input);
    }
    
    /** 
     * <p>returns invalid command message</p>
     * @return string containing invalid command message
     * @since 1.0
     */
    public String invalidCommand() {
        return INVALID;
    }


}