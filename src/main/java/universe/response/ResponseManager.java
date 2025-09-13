package universe.response;

import java.time.LocalDateTime;

import javafx.application.Platform;
import universe.Checklist;
import universe.CommandsList;
import universe.Ui;
import universe.chores.Deadline;
import universe.chores.Event;
import universe.chores.ToDo;
import universe.exceptions.InvalidResponseException;

/**
 * Responsible for calling the corresponding functions based on user input.
 *
 * @author yuqing-tham
 */
public class ResponseManager {
    private final Checklist chores;
    private final String response;

    /**
     * Constructs a new ResponseManager to execute commands based on user input.
     *
     * @param chores A Checklist maintained by the Universe bot.
     * @param response A String representing the response by the user.
     */
    public ResponseManager(Checklist chores, String response) {
        this.chores = chores;
        this.response = response;
    }

    /**
     * Asks a UserInputParser to help split the response to meaningful parts,
     * and calls different functions to handle different commands.
     *
     * @throws InvalidResponseException If the user input is not recognised under the list of approved commands.
     */
    public void execute() throws InvalidResponseException {
        UserInputParser userInputParser = new UserInputParser(response);
        String command = userInputParser.getCommand();

        switch (command) {
        case "hi" -> handleHi();
        case "help" -> handleHelp();
        case "list" -> handleList();
        case "check" -> handleCheck(userInputParser);
        case "uncheck" -> handleUncheck(userInputParser);
        case "todo" -> handleToDo(userInputParser);
        case "deadline" -> handleDeadline(userInputParser);
        case "event" -> handleEvent(userInputParser);
        case "remove" -> handleRemove(userInputParser);
        case "filter" -> handleFilter(userInputParser);
        case "find" -> handleFind(userInputParser);
        case "clear" -> handleClear();
        case "bye" -> handleBye();
        default -> throw new InvalidResponseException();
        }
    }

    /**
     * Greets the user and gives further directions.
     */
    public void handleHi() {
        System.out.println("Greetings Universe traveller! Type 'help' if you need the commands list.");
    }

    /**
     * Calls the relevant functions to show the list of approved commands.
     */
    public void handleHelp() {
        CommandsList commands = new CommandsList();
        commands.printCommands();
    }

    /**
     * Calls the relevant functions to show the chores checklist.
     */
    public void handleList() {
        System.out.println("Cosmic Chore Checklist:");
        chores.printChecklist();
    }

    /**
     * Calls the relevant functions to mark the chore as completed.
     */
    public void handleCheck(UserInputParser userInputParser) {
        int choreNumberCheck = userInputParser.getChoreNumber();
        chores.checkAsDone(choreNumberCheck);
    }

    /**
     * Calls the relevant functions to mark the chore as not completed.
     */
    public void handleUncheck(UserInputParser userInputParser) {
        int choreNumberUncheck = userInputParser.getChoreNumber();
        chores.uncheckAsDone(choreNumberUncheck);
    }

    /**
     * Calls the relevant functions to create a ToDo chore.
     */
    public void handleToDo(UserInputParser userInputParser) {
        String todoDescription = userInputParser.getDescription();
        ToDo todo = new ToDo(todoDescription);
        chores.addChore(todo);
    }

    /**
     * Calls the relevant functions to create a Deadline chore.
     */
    public void handleDeadline(UserInputParser userInputParser) {
        String deadlineDescription = userInputParser.getDeadlineDescription();
        LocalDateTime deadlineDateTime = userInputParser.getDeadlineDate();
        Deadline deadline = new Deadline(deadlineDescription, deadlineDateTime);
        chores.addChore(deadline);
    }

    /**
     * Calls the relevant functions to create an Event chore.
     */
    public void handleEvent(UserInputParser userInputParser) {
        String eventDescription = userInputParser.getEventDescription();
        LocalDateTime startDateTime = userInputParser.getStartTime();
        LocalDateTime endDateTime = userInputParser.getEndTime();
        try {
            Event event = new Event(eventDescription, startDateTime, endDateTime);
            chores.addChore(event);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    /**
     * Calls the relevant functions to remove a chore from the checklist.
     */
    public void handleRemove(UserInputParser userInputParser) {
        int choreNumber = userInputParser.getChoreNumber();
        chores.removeChore(choreNumber);
    }

    /**
     * Calls the relevant functions to filter the chores by date.
     */
    public void handleFilter(UserInputParser userInputParser) {
        String dateString = userInputParser.getFilterDateString();
        LocalDateTime filterDate = userInputParser.getFilterDate();
        chores.filterByDate(dateString, filterDate);
    }

    /**
     * Calls the relevant functions to find the chores with matching keywords in the descriptions.
     */
    public void handleFind(UserInputParser userInputParser) {
        String keyword = userInputParser.getFindKeyword();
        chores.findByKeyword(keyword);
    }

    /**
     * Calls the relevant functions to clear all chores from the checklist.
     */
    public void handleClear() {
        chores.clearAllChores();
    }

    /**
     * Terminates the session and closes the GUI.
     */
    public void handleBye() {
        Ui ui = new Ui();
        ui.bye();
        Platform.exit();
    }
}
