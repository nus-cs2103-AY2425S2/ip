package miki;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import miki.command.Command;
import miki.exception.MikiException;
import miki.parse.Parser;
import miki.storage.Storage;
import miki.task.TaskList;
import miki.ui.Ui;

/**
 * Represents the main class of the Miki chatbot.
 */
public class Miki {
    private static TaskList tasks;
    private Storage storage;
    private Ui ui;

    private boolean isExit = false;

    /**
     * Constructor for Miki.
     */
    public Miki() {
        ui = new Ui();
        try {
            storage = new Storage("./data/tasks.txt");
            tasks = new TaskList((storage.load()));
        } catch (MikiException e) {
            System.out.println("Error loading tasks from file. Starting with an empty task list. " + e.getMessage());
            tasks = new TaskList();
            storage = new Storage("./data/tasks.txt");
        }
    }

    /**
     * Returns the response from a given user input.
     *
     * @param input The user input.
     *
     * @return The response from Miki.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.handleUserCommand(input);
            String response = command.execute(tasks, ui, storage);

            if (command.checkExit()) {
                isExit = true;
            }

            return response;
        } catch (MikiException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the greeting message from Miki.
     *
     * @return The greeting message from Miki.
     */
    public String getGreeting() {
        return ui.showGreeting();
    }

    /**
     * Checks if the user has requested to exit the chatbot.
     *
     * @return True if the user has requested to exit the chatbot, false otherwise.
     */
    public boolean checkExit() {
        return isExit;
    }


    /**
     * Schedules the application to exit after the specified delay.
     *
     * @param delayInSeconds The delay in seconds before the application exits.
     */
    public void scheduleExit(int delayInSeconds) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            System.out.println("Exiting application...");
            System.exit(0);
        }, delayInSeconds, TimeUnit.SECONDS);
    }

    /**
     * Runs the Miki chatbot. (This method is only used for the CLI version of Miki)
     */
    public void run() {
        ui.showIntro();

        while (true) {
            ui.showLine();
            String userCommand = ui.readCommand();
            try {
                Command command = Parser.handleUserCommand(userCommand);
                command.execute(tasks, ui, storage);
                if (command.checkExit()) {
                    ui.showExit();
                    scheduleExit(5);
                    break;
                }
            } catch (MikiException e) {
                ui.showErrorMessage(e.getMessage());
            }

            ui.showLine();
        }
    }
}
