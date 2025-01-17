package tasker;

import java.util.Scanner;
import tasker.command.Parser;
import tasker.task.TaskList;

/**
 * Main class for project
 */
public class Tasker {
    /** List of the user's tasks */
    private static TaskList tasks = new TaskList();

    /**
     * Formats and prints an output
     *
     * @param output The content of the output
     */
    private static void respond(String output) {
        String separator = "____________________________________________________________\n";
        output = String.format("%s%s\n%s", separator, output, separator);

        for (String line : output.split("\n")) {
            System.out.println("    " + line);
        }

        System.out.println();
    }

    public static void main(String[] args) {
        respond("""
                Hello! I'm Tasker
                What can I do for you?""");
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();

        while (!cmd.equals("bye")) {
            respond(Parser.parse(cmd).execute(tasks));
            cmd = sc.nextLine();
        }

        sc.close();
        respond("Bye. Hope to see you again soon!");
    }
}
