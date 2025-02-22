package marshmallow.ui;

import java.util.Scanner;

import marshmallow.Marshmallow;
import marshmallow.MarshmallowDelegate;
import marshmallow.commands.Command;

/**
 * Command line interface for Marshmallow.
 */
public class Cli implements MarshmallowDelegate {
    private static String logo = "⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⡀⠀⠀⠀⠀\n"
                + "⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠀\n"
                + "⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁\n"
                + "⡂⠀⠀⠀⠀⠀⠀⠀⠀⢠⣶⡄⠀⠀⠀⠀⠀⠀⣤⣄⠀⠀⠀⠀⠀⠀⠀⠀⡀\n"
                + "⠂⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠁⠀⠀⠀⠀⠀⠀⠙⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠦⠤⠶⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⢨⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠀⠀⠀⠆\n"
                + "⠈⠀⣀⢴⣛⢽⣱⢮⣦⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣔⡎⠷⣇⣏⠯⣆⠀⠁\n"
                + "⠀⢤⣿⣾⢯⣟⡳⣟⣾⣻⡧⠀⠀⠀⠀⢀⠀⠀⢀⣾⣵⡞⣯⢷⡺⡽⣏⠁⠀\n"
                + "⠀⢽⢿⣞⣛⣮⡟⣯⣷⣻⣾⡁⠀⠀⠀⠠⢨⣀⣾⣟⡾⣽⡿⣼⣹⢧⡻⢏⠀\n"
                + "⠄⢼⢿⣽⣿⣗⡿⣽⡻⣿⡶⡇⠄⣀⠀⠀⠻⢿⣹⢾⣽⢯⣷⣳⢯⡯⣟⠃⠀\n"
                + "⠀⠈⢺⣽⢟⣽⡳⢯⣳⣽⡷⣃⠄⡩⠐⣀⢂⠘⢻⣞⣿⢾⡽⣻⡺⡗⠉⠀⠀\n"
                + "⠀⠀⠀⣙⠻⣟⣯⣿⣽⡛⢹⠛⠛⠛⠛⠒⠙⠀⠚⡻⢽⣻⣯⣿⡟⠁⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠉⠉⠈⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠁⠀⠀⠀⠀⠀";

    private Marshmallow marshmallow;

    @Override
    public void marshmallowRespond(String response) {
        System.out.println("    ________________________________________________________");
        System.out.print("     ");
        System.out.println(response.replace("\n", "\n     "));
        System.out.println("    ________________________________________________________\n");
    }

    private void greet() {
        System.out.println(logo);
        marshmallowRespond("Hello! I'm Marshmallow\nWhat can I do for you?");
    }

    private void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                Command command = marshmallow.issue(scanner);
                if (command != null && command.isExit()) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Cli cli = new Cli();
        cli.marshmallow = new Marshmallow("data/tasks.txt");
        cli.marshmallow.setDelegate(cli);
        cli.greet();
        cli.run();
    }
}
