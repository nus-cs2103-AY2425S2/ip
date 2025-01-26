package tasker;

import static tasker.Parser.parseCommand;

import java.util.Scanner;

import tasker.command.ByeCommand;
import tasker.command.Command;
import tasker.exception.TaskerException;
import tasker.task.TaskList;

/**
 * Main class for project.
 */
public class Tasker {
    /** List of the user's tasks */
    private static TaskList tasks;

    /**
     * Formats and prints an output.
     *
     * @param content The content of this response.
     */
    private static void respond(String content) {
        String padding = "    ";
        String separator = padding + "____________________________________________________________\n";
        String contentPadding = padding + " ";
        StringBuilder response = new StringBuilder(separator);

        for (String line : content.split("\n")) {
            String linePadding = contentPadding + line.replaceFirst("\\S.*", "");
            StringBuilder wrappedLine = new StringBuilder(linePadding);

            for (String word : line.split(" ")) {
                int currLineLength = wrappedLine.length();
                boolean isFirstWord = currLineLength == linePadding.length();

                if ((currLineLength + 1 + word.length() <= separator.length() - 1) || isFirstWord) {
                    if (!isFirstWord) {
                        wrappedLine.append(" ");
                    }

                    wrappedLine.append(word);
                } else {
                    response.append(wrappedLine.append("\n"));
                    wrappedLine = new StringBuilder(linePadding).append(word);
                }
            }

            response.append(wrappedLine.append("\n"));
        }

        response.append(separator);
        System.out.println(response);
    }

    public static void main(String[] args) {
        Storage storage;
        try {
            storage = new Storage("./data/tasker.txt");
            tasks = new TaskList(storage.getTasks());
        } catch (TaskerException e) {
            respond(e.getMessage());
            return;
        }

        respond("""
                Hello! I'm Tasker
                What can I do for you?""");
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();

        while (true) {
            Command parsedCmd = null;

            try {
                parsedCmd = parseCommand(cmd);
                respond(parsedCmd.execute(tasks, storage));
            } catch (TaskerException e) {
                respond(e.getMessage());
            }

            if (parsedCmd instanceof ByeCommand) {
                break;
            }

            cmd = sc.nextLine();
        }

        sc.close();
    }
}
