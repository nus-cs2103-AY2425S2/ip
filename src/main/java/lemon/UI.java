/**
 * This class deals with interaction with the user
 * It includes messages printed to the user on various occasions
 */

package lemon;

import java.util.Scanner;


public class UI {
    private Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public String displayWelcomeMessage() {
        StringBuilder sb = new StringBuilder();
        String logo =
                  "__     _____  ___      ___   ____   __    __\n"
                + "| |    |         ||\\\\    // || //    \\\\  ||\\\\    ||\n"
                + "| |    |____   || \\\\  //  || ||      ||  || \\\\   ||\n"
                + "| |__ |----   ||  \\\\//   || ||      ||  ||  \\\\  ||\n"
                + "|___| |_____ ||   \\\\//  || \\\\__//   ||   \\\\ ||\n";
        sb.append("Hello from\n" + logo).append("\n");
        sb.append("Hello! I'm Lemon, your task manager!").append("\n");
        sb.append("What can I do for you?").append("\n");
        sb.append("You can type 'help' to see the user guide!");
        return sb.toString();
    }

    public String displayExitMessage() {
        return "Bye! Have a nice day and hope to see you again soon! \uD83D\uDE0A";
    }

    public String displayConfusionMessage() {
        return "Ohh sorry I don't understand this...";
    }

    public String displayWrongFormatMessage() {
        return "Sorry, the format of the command is not correct!";
    }

    public String displayInvalidIndexMessage() {
        return "Sorry, the index you are out of range!";
    }

    public String getInput() {
        return scanner.nextLine();
    }

}