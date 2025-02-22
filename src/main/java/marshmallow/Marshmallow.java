package marshmallow;

import java.util.Scanner;

import marshmallow.commands.Command;
import marshmallow.parser.Parser;
import marshmallow.storage.textfilestorage.TextFileStorage;
import marshmallow.tasks.Task;
import marshmallow.tasks.TaskFactory;
import marshmallow.tasks.TaskList;

/**
 * Represents the main class of the Marshmallow chatbot.
 */
public class Marshmallow {
    private TextFileStorage<Task, TaskFactory> storage;
    private TaskList tasks;
    private Parser parser;
    private MarshmallowDelegate delegate;

    /**
     * Constructs Marshmallow.
     *
     * @param path The path to the file to store tasks.
     */
    public Marshmallow(String path) {
        this.storage = new TextFileStorage<>(path, new TaskFactory());
        this.tasks = new TaskList(storage);
        this.parser = new Parser();
    }

    /**
     * Sets the delegate to receive Marshmallow events.
     *
     * @param delegate The delegate.
     */
    public void setDelegate(MarshmallowDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * Issues a command to Marshmallow.
     *
     * @param s The scanner to read the command from.
     * @return The command issued.
     */
    public Command issue(Scanner s) {
        try {
            Command command = parser.parse(s);

            if (command == null) {
                delegate.marshmallowRespond("I'm sorry, but I don't know what that means :(");
                return null;
            }

            String response = command.handle(tasks);

            if (delegate != null) {
                delegate.marshmallowRespond(response);
            }

            return command;
        } catch (MarshmallowException e) {
            delegate.marshmallowRespond(e.getMessage());
        }

        return null;
    }

    /**
     * Issues a command to Marshmallow.
     *
     * @param s The command string.
     * @return The command issued.
     */
    public Command issue(String s) {
        return issue(new Scanner(s));
    }
}
