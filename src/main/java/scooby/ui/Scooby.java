package scooby.ui;

import java.util.Scanner;

public class Scooby {
    private TaskList taskList; // TaskList instance to manage tasks
    private Ui ui;

    public Scooby() {
        this.ui = new Ui("Scooby");
        this.taskList = new TaskList();
    }

    /**
     * run() runs the bot
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

    public static void main(String[] args) {
        new Scooby().run();
    }
}
