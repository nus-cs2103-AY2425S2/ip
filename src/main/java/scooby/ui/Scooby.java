package scooby.ui;

import java.util.Scanner;

import scooby.tasks.TaskList;

public class Scooby {
    private TaskList taskList; // TaskList instance to manage tasks
    private Ui ui;

    public Scooby() {
        this.ui = new Ui("Scooby");
        this.taskList = new TaskList();
    }

    /**
     * Starts the chatbot application and continuously processes user input.
     *
     * This method initializes the chatbot, greets the user, and enters a loop
     * that reads user input from the console. The input is passed to the `Parser`
     * for processing. The chatbot continues running until the user enters an
     * exit command (e.g., "bye"), at which point the loop terminates.
     *
     * The method ensures that the scanner is properly closed when the chatbot
     * session ends to prevent resource leaks.
     */
    public void run() {
        this.ui.greet();
        Scanner scanner = new java.util.Scanner(System.in);

        while (true) {
            Parser parser = new Parser(this.taskList, this.ui);
            String userInput = scanner.nextLine().trim();
            if (!parser.parseCommand(userInput)) {
                break; // Exit the loop if the parser returns false
            }
        }
        scanner.close();
    }

    /**
     * Processes the user input and returns the chatbot's response.
     *
     * This method initializes a `Parser` instance and passes the given user input
     * to `parseCommandReturnString()`. The parsed response is then returned to be
     * displayed in the GUI.
     *
     * @param input The user input as a string.
     * @return The chatbot's response as a string.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(taskList, ui); // Pass the Scooby instance to the parser
        return parser.parseCommandReturnString(input); // Return the response from parseCommand
    }

    public static void main(String[] args) {
        new Scooby().run();
    }
}
