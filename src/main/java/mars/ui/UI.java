package mars.ui;
import java.util.Scanner;

/**
 * The UI class handles all interactions with user, displaying messages and reading user input
 */
public class UI {
    private final Scanner sc;  // deprecated
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private String latestResponse;

    /**
     * Constructor for UI class. Instantiates UI object with a new Scanner for reading user input.
     */
    public UI() {
        this.sc = new Scanner(System.in);
    }
        /**
         * Displays the welcome message when the application starts.
         */
        public String welcomeMessage() {
            return "Hello! I'm Mars, your personalised TaskList Assistant\n" + "Please enter a User Command\n";
        }

        // deprecated
        public void showLine(){
            System.out.println(HORIZONTAL_LINE);
        }


        /**
         * Displays a given error message.
         *
         * @param message The error message to display.
         */
        public void showError(String message) {
                System.out.println(message);
            }

            // deprecated
            /**
             * Reads in command from the user.
             *
             * @return command entered by the user as a string.
             */
            public String readCommand() {
                return sc.nextLine();
            }

            // deprecated
            /**
             * Closes scanner object
             */
            public void close() {
                sc.close();
            }

    public String userGuide() {
        String guide = "QUICK GUIDE TO MARS:\n"
                + "\nTo add a ToDo:\n   'todo {task name}\n'"
                + "\nTo add a Deadline:\n   'deadline {task name} (by: MMM dd yyyy, h:mm a)\n'"
                + "\nTo add an Event:\n   'event {task name} (from: MMM dd yyyy, h:mm a to MMM dd yyyy, h:mm a)'\n"
                + "\nTo add an DoWithin:\n 'do within {task name} between: MMM dd yyyy, h:mm a and MMM dd yyyy, h:mm a'\n";

        String userCommands = "\nOTHER USER COMMANDS:\n"
                +"\n *** any other commands constitutes as an echo ***\n"
                + "\n1. list  <This shows the list of ACTIVE tasks>"
                + "\n2. delete  {task number}"
                + "\n3. mark  {task number}"
                + "\n4. unmark  {task number}"
                + "\n5. find  {keyword}";

        latestResponse = guide + userCommands;
        return latestResponse;
    }

    public void goodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    // getter
    public String getResponse(){ return latestResponse;}

    // setter
    public void setResponse (String message){ latestResponse = message;}

}


