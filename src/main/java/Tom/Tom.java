package Tom;

import Tom.commands.Command;
import Tom.commands.Parser;
import Tom.tasks.TaskList;
//commit 1
//commit 2
//commit 3
//commit 4
//commit 5

public class Tom {
    private TaskList tasks;
    private Storage storage;
    private String test;

    public Tom() {
        storage = new Storage();
        tasks = new TaskList(storage.loadTasks());
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks);
        } catch (TomException e) {
            return e.getMessage();
        }
    }

    public static String getWelcomeMessage() {
        return "Hello! My name is Tom\n"
                + "Type \"help\" for a list of commands.";
    }
}