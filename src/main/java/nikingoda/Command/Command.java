package nikingoda.Command;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Storage.Storage;
import nikingoda.TaskList.TaskList;
import nikingoda.Ui.Ui;

public abstract class Command {
    private String response;

    public static Command findCommand(String command) throws NikingodaException {
        command = command.trim();
        command = command.toLowerCase();
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        }
        String[] commandSplit = command.split(" ");
        if (commandSplit.length == 0) {
            throw new NikingodaException("Please enter message");
        }
        String firstWord = commandSplit[0];
        switch (firstWord) {
        case "mark" -> {
            try {
                int id = Integer.parseInt(command.substring(5));
                return new MarkCommand(id);
            } catch (NumberFormatException e) {
                throw new NikingodaException(" Id must be in form of number");
            }
        }
        case "unmark" -> {
            try {
                int id = Integer.parseInt(command.substring(7));
                return new UnmarkCommand(id);
            } catch (NumberFormatException e) {
                throw new NikingodaException(" Id must be in form of number");
            }
        }
        case "todo" -> {
            return new AddTodoCommand(command);
        }
        case "event" -> {
            return new AddEventCommand(command);
        }
        case "deadline" -> {
            return new AddDeadlineCommand(command);
        }
        case "delete" -> {
            try {
                int id = Integer.parseInt(commandSplit[1]);
                return new DeleteCommand(id);
            } catch (NumberFormatException e) {
                throw new NikingodaException("Task id must be integer");
            }
        }
        case "find" -> {
            try {
                StringBuilder keyWord = new StringBuilder(commandSplit[1]);
                for (int i = 2; i < commandSplit.length; i++) {
                    keyWord.append(" ").append(commandSplit[i]);
                }
                return new FindCommand(keyWord.toString());
            } catch (IndexOutOfBoundsException e) {
                throw new NikingodaException("Please add keyword");
            } catch (Exception e) {
                throw new NikingodaException(e.getMessage());
            }
        }
        case "update" -> {
            try {
                String[] updateCommandSplit = command.split(" /description ");
                if (updateCommandSplit.length > 1) {
                    try {
                        int id = Integer.parseInt(commandSplit[1].trim());
                        String newDescription = updateCommandSplit[1];
                        return new UpdateDescriptionCommand(id, newDescription);
                    } catch (Exception e) {
                        throw new NikingodaException("To update description, use: update <task_id> /description <new_description>");
                    }
                }
                updateCommandSplit = command.split(" /by ");
                if (updateCommandSplit.length > 1) {
                    try {
                        int id = Integer.parseInt(commandSplit[1].trim());
                        String newDeadline = updateCommandSplit[1];
                        return new UpdateDeadlineCommand(id, newDeadline);
                    } catch (Exception e) {
                        throw new NikingodaException("To update deadline, use: update <task_id> /by <new_deadline>");
                    }
                }
                updateCommandSplit = command.split(" /from ");
                if (updateCommandSplit.length > 1) {
                    try {
                        int id = Integer.parseInt(commandSplit[1].trim());
                        String newBegin = updateCommandSplit[1];
                        return new UpdateBeginCommand(id, newBegin);
                    } catch (Exception e) {
                        throw new NikingodaException("To update begin_time, use: update <task_id> /from <new_begin_time>");
                    }
                }
                updateCommandSplit = command.split(" /to ");
                if (updateCommandSplit.length > 1) {
                    try {
                        int id = Integer.parseInt(commandSplit[1].trim());
                        String newEnd = updateCommandSplit[1];
                        return new UpdateEndCommand(id, newEnd);
                    } catch (Exception e) {
                        throw new NikingodaException("To update end_time, use: update <task_id> /to <new_end_time>");
                    }
                }
                throw new NikingodaException("""
                        To update, use:\s
                        'update <task_id> /by <new_deadline>' to update deadline
                        'update <task_id> /description <new_description>' to update description
                        'update <task_id> /from <new_begin_time>' to update begin_time
                        'update <task_id> /to <new_end_time>' to update end_time""");
            } catch (Exception e) {
                throw new NikingodaException("""
                        To update, use:\s
                        'update <task_id> /by <new_deadline>' to update deadline
                        'update <task_id> /description <new_description>' to update description
                        'update <task_id> /from <new_begin_time>' to update begin_time
                        'update <task_id> /to <new_end_time>' to update end_time""");
            }
        }
        default -> throw new NikingodaException("I don't understand you");
        }
    }

    /**
     * @param tasks   TaskList
     * @param ui      Ui
     * @param storage Storage
     * @throws NikingodaException handle Error
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NikingodaException;

    /**
     * except exit command, isExit() return false as we continue use chatbot
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    public String getString() throws NikingodaException {
        if (this.response == null) {
            throw new NikingodaException("Command had not been executed");
        }
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
