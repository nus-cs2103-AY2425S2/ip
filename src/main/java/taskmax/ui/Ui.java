package taskmax.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    public static final String LINE = "-".repeat(100) + "\n";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String intro = "Greetings! I'm Taskmax, Your personal tasking companion.\n"
                + "What can I schedule for you today?\n";
        System.out.println(LINE + intro + LINE + new Mascot() + LINE
                + "\nEnter \"hello!\" to begin\n" + LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(LINE + message + "\n" + LINE);
    }

    public void showLoadingError() {
        showMessage("Error loading tasks. Starting with an empty list.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void close() {
        scanner.close();
    }
}
