package tom;

import java.util.LinkedList;
import java.util.Queue;

import tom.command.Command;
import tom.command.LoadCommand;
import tom.command.PromptCommand;
import tom.command.ReportErrorCommand;
import tom.command.WelcomeCommand;
import tom.exception.TomCommandException;
import tom.exception.TomParseException;
import tom.parser.CommandParser;
import tom.parser.Parser;
import tom.storage.Storage;
import tom.tasklist.TaskList;
import tom.ui.Ui;

/**
 * The main class for the Tom application.
 */
public class Tom {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Queue<Command> commandQueue;

    /**
     * Constructs a Tom instance with the default file path.
     */
    public Tom(Ui ui) {
        this("./data/tom.txt", ui);
    }

    /**
     * Constructs a Tom instance with the specified file path.
     *
     * @param filePath The file path for storing tasks.
     * @param ui The ui Tom will communicate with.
     */
    public Tom(String filePath, Ui ui) {
        this.ui = ui;
        storage = new Storage(filePath);
        tasks = new TaskList();
        commandQueue = new LinkedList<>();

        addCommand(0, new LoadCommand());
        addCommand(0, new WelcomeCommand());
        executeCommands();
    }

    /**
     * Handles user input by parsing and executing the corresponding command.
     * If the command requires additional input, it prompts the user accordingly.
     *
     * @param input The user input.
     */
    public void handleUserInput(String input) {
        String[] inputComponents = input.split(" ", 2);
        String commandType = inputComponents[0];
        String commandArgs = inputComponents.length > 1 ? inputComponents[1] : null;

        CommandParser commandParser = Parser.findParser(commandType, ui);
        handleUserInput(commandArgs, commandParser);
    }

    /**
     * Handles user input by parsing and executing the corresponding command.
     * If the command requires additional input, it prompts the user accordingly.
     *
     * @param input The user input.
     * @param commandParser The parser that will parse given input.
     */
    public void handleUserInput(String input, CommandParser commandParser) {
        Command command;
        try {
            commandParser.parseNext(input);
            command = commandParser.produceCommand();
        } catch (TomParseException e) {
             command = new PromptCommand(e,
            newInput -> handleUserInput(newInput, commandParser));
        }
        
        command.setId(commandParser.getId());
        if (command != null) {
            addCommand(command);
        }
        executeCommands();
    }

    /**
     * Adds a command to the queue.
     *
     * @param command The command to be added.
     */
    public void addCommand(Command command) {
        commandQueue.add(command);
    }

    /**
     * Adds a command to the queue.
     *
     * @param id The ID of the command.
     * @param command The command to be added.
     */
    public void addCommand(int id, Command command) {
        command.setId(id);
        commandQueue.add(command);
    }

    /**
     * Executes all commands in the queue.
     */
    public void executeCommands() {
        while (!commandQueue.isEmpty()) {
            Command command = commandQueue.poll();
            try {
                command.execute(tasks, ui, storage);
            } catch (TomCommandException e) {
                Command errCommand = new ReportErrorCommand(e);
                errCommand.setId(command.getId());
                commandQueue.add(errCommand);
            }
        }
    }
}
