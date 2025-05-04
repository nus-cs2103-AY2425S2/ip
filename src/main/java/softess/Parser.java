package softess;

import java.util.Objects;

public class Parser {

    public UserInterface ui;
    public TaskList tasks;
    public Parser(UserInterface ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    public Command parseCommand(String fullUserInput) throws SoftessException {
        assert fullUserInput != null && !fullUserInput.trim().isEmpty() : "User input cannot be null or empty";

        String[] userInput = fullUserInput.split(" ");
        assert userInput.length > 0 : "User input parsing failed";

        if (Objects.equals(userInput[0], "bye")) {
            return new ExitCommand(ui);
        } else if (Objects.equals(userInput[0], "remind")) {
            return new RemindCommand(ui, tasks);
        } else if (Objects.equals(userInput[0], "list")) {
            return new ListCommand(ui, tasks);
        } else if (Objects.equals(userInput[0], "mark")) {
            assert userInput.length > 1 : "Mark command requires an index";
            assert userInput[1].matches("\\d+") : "Mark index must be a valid number";
            int num = Integer.valueOf(userInput[1]);
            return new MarkCommand(ui, tasks, num, true);
        } else if (Objects.equals(userInput[0], "unmark")) {
            assert userInput.length > 1 : "Unmark command requires an index";
            assert userInput[1].matches("\\d+") : "Unmark index must be a valid number";
            int num = Integer.valueOf(userInput[1]);
            return new MarkCommand(ui, tasks, num, false);
        } else if (Objects.equals(userInput[0], "deadline")) {
            assert fullUserInput.contains("/by") : "Deadline command must contain '/by'";
            userInput = fullUserInput.split("/by");
            String description = userInput[0].split("deadline")[1].trim();
            return new DeadlineCommand(ui, tasks, description, userInput[1].trim());
        } else if (Objects.equals(userInput[0], "event")) {
            assert fullUserInput.contains("/from") && fullUserInput.contains("/to") : "Event command must contain '/from' and '/to'";
            userInput = fullUserInput.split("/from");
            String description = userInput[0].split("event")[1].trim();
            String[] time = userInput[1].split("/to");
            return new EventCommand(ui, tasks, description, time[0].trim(), time[1].trim());
        } else if (Objects.equals(userInput[0], "todo")) {
            String[] split = fullUserInput.split("todo");
            boolean toDoDescriptionIsEmpty = !split[1].trim().isEmpty();
            assert split.length > 1 && toDoDescriptionIsEmpty: "Todo description cannot be empty";
            return new ToDoCommand(ui, tasks, split[1].trim());
        } else if (Objects.equals(userInput[0], "delete")) {
            assert userInput.length > 1 : "Delete command requires an index";
            assert userInput[1].matches("\\d+") : "Delete index must be a valid number";
            int num = Integer.valueOf(userInput[1]);
            return new DeleteCommand(ui, tasks, num);
        } else if (Objects.equals(userInput[0], "find")) {
            String[] split = fullUserInput.split("find");
            assert split.length > 1 && !split[1].trim().isEmpty() : "Find command requires a search keyword";
            return new FindCommand(ui, tasks, split[1].trim());
        } else {
            throw new SoftessException.InvalidCommandException();
        }
    }

}
