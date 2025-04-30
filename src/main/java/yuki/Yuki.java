package yuki;

import yuki.command.Command;
import yuki.task.Task;

/**
 * Yuki is a chatbot that helps users to manage their tasks.
 * It can add, delete, mark as done, find tasks and list all tasks.
 * It can also save and load tasks from a file.
 */
public class Yuki {
    private final Storage storage;
    private TaskList<Task> tasks;
    private final Ui ui;

    /**
     * Constructs a Yuki object.
     *
     * @param filePath The file path to save and load tasks.
     */
    public Yuki(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList<>(storage.load());
        } catch (YukiException e) {
            ui.showLoadingError();
            tasks = new TaskList<>();
        }

    }


    public static void main(String[] args) {
        new Yuki("./data/yuki.txt");
    }

    /**
     * Gets the response from Yuki based on the user input.
     *
     * @param input The user input.
     * @return The response from Yuki.
     * @throws InterruptedException If the thread is interrupted.
     */
    public String getResponse(String input) throws InterruptedException {
        boolean isExit = false;
        String output;
        try {
            Command c = Parser.parse(input);
            output = c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (YukiException e) {
            return e.getMessage();
        }

        if (isExit) {
            storage.save(tasks);
            return ui.showGoodbye();
        }
        return output;
    }
}
