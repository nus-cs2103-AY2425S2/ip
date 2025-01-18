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
     * @param content The content of this response.
     */
    private static void respond(String content) {
        String padding = "    ";
        String separator = padding + "____________________________________________________________\n";
        StringBuilder response = new StringBuilder(separator);

        for (String line : content.split("\n")) {
            response.append(" ").append(padding).append(line).append("\n");
        }

        response.append(separator);
        System.out.println(response);
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
