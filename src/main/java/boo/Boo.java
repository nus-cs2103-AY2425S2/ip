package boo;

import boo.misc.BooException;
import boo.misc.Parser;
import boo.misc.Storage;
import boo.misc.Ui;
import boo.task.TaskList;

/**
 * Represents the main class of the program.
 */
public class Boo {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    /**
     * Constructor for the chatbot
     *
     * @throws BooException If there is any problem reading the input.
     */
    public Boo() throws BooException {
        ui = new Ui();
        storage = new Storage("./data/Boo.txt");
        taskList = new TaskList(storage, ui);
        parser = new Parser(taskList, ui);
    }

    /**
     * Returns Boo's response to user input.
     *
     * @param input The user input.
     * @return Boo's response.
     */
    public String getResponse(String input) {
        ui.printGreeting();
        try {
            if (input.equalsIgnoreCase("bye")) {
                return handleExit();
            }
            if (input.equalsIgnoreCase("list")) {
                return ui.printTaskHistory(taskList.getTaskMap());
            } else if (input.toLowerCase().startsWith("mark")) {
                return taskList.markAsDone(input);
            } else if (input.toLowerCase().startsWith("unmark")) {
                return taskList.markAsNotDone(input);
            } else if (input.toLowerCase().startsWith("delete")) {
                return taskList.deleteTask(input);
            } else if (input.toLowerCase().startsWith("find")) {
                return taskList.findTask(input);
            } else {
                return taskList.addTask(Parser.parseTask(input));
            }
        } catch (BooException e) {
            return e.getMessage();
        }
    }

    private String handleExit() {
        String goodbyeMessage = ui.printGoodbyeMessage();

        // Delay exit to display message
        new Thread(() -> {
            try {
                Thread.sleep(1000); // 1-second delay before exiting
                System.exit(0);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        return goodbyeMessage;
    }

    public Ui getUi() {
        return ui;
    }

}
