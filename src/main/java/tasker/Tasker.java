package tasker;

import java.util.Scanner;
import tasker.command.Parser;
import tasker.exception.TaskerException;
import tasker.task.TaskList;

/**
 * Main class for project.
 */
public class Tasker {
    /** List of the user's tasks */
    private static TaskList tasks = new TaskList();

    /**
     * Formats and prints an output.
     *
     * @param output The content of the output.
     */
    private static void respond(String output) {
        String separator = "____________________________________________________________\n";
        output = String.format("%s%s\n%s", separator, output, separator);
        String[] lines = output.split("\n");
        int lineCount = lines.length;

        for (int i = 0; i < lineCount; i++) {
            System.out.println(String.format("%s%s%s",
                    i == 0 || i == lineCount - 1 ? "" : " ",
                    "    ", lines[i]));
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
            try {
                respond(Parser.parse(cmd).execute(tasks));
            } catch (TaskerException e) {
                respond(e.getMessage());
            }
            cmd = sc.nextLine();
        }

        sc.close();
        respond("Bye. Hope to see you again soon!");
    }
}
