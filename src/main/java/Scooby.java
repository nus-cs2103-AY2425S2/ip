import java.util.Scanner;

public class Scooby {
    public static void start(Ui ui) {
        ui.greet();
        Scanner scanner = new java.util.Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("Bye")) {
                ui.exitDialogue();
                break;
            } else {
                ui.echo(userInput);
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Ui ui = new Ui("Scooby");
        start(ui);
    }
}
