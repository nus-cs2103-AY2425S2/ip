import java.util.Scanner;

public class Tasker {
    private static void respond(String output) {
        String separator = "____________________________________________________________\n";
        output = separator
                + output + '\n'
                + separator;

        for (String line : output.split("\n")) {
            System.out.println("    " + line);
        }

        System.out.println();
    }

    public static void main(String[] args) {
        respond("Hello! I'm Tasker\n"
                + "What can I do for you?");
        respond("Bye. Hope to see you again soon!");
    }
}
