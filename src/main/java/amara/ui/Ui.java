package amara.ui;

import java.util.Scanner;

/**
 * User interface class that handles all of the interactions
 * between the user and {@link Amara} chatbot. All of the methods
 * and interactions are implemented with their corresponding {@code enum}
 * in {@link CommandEnum}
 */
public class Ui {
    private Scanner scanner;

    public Ui(String filePath) {
        this.scanner = new Scanner(System.in);
    }

    public void display(String string) {
        System.out.println(string);
    }

    public void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public String readLine() {
        return this.scanner.nextLine();
    }
}
