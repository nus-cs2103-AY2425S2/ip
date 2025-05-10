package bun.ui;

public class Parser {
    /**
     * Returns the Command interpreted from the input instruction word and details (if any)
     *
     * @param fullCommand String array with at most 2 elements,
     *                    where the first element is the command/instruction word,
     *                    and the second element is the detail of the instruction (if any).
     * @return Command requested in the input.
     * @throws InvalidCommandException If the command word is missing or not one of the listed CommandWord.
     * @throws MissingFieldException   If any field for details is missing.
     * @throws DateFormatException     If the input date is not in YYYY-MM-DD format.
     */
    public static Command parse(String[] fullCommand) throws InvalidCommandException,
            MissingFieldException, DateFormatException {
        if (fullCommand == null || fullCommand.length == 0) {
            throw new InvalidCommandException("");
        }
        String instruction = fullCommand[0];
        CommandWord command;
        try {
            command = CommandWord.valueOf(instruction.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException(instruction);
        }
        switch (command) {
        case BYE:
            return new ExitCommand();
        case LIST: {
            return new ListCommand();
        }
        case MARK: {
            int index = Integer.parseInt(fullCommand[1]) - 1;
            assert index >= 0 : "Index must not be negative";
            return new EditCommand(true, index);
        }
        case UNMARK: {
            int index = Integer.parseInt(fullCommand[1]) - 1;
            assert index >= 0 : "Index must not be negative";
            return new EditCommand(false, index);
        }
        case REMOVE: {
            int index = Integer.parseInt(fullCommand[1]) - 1;
            assert index >= 0 : "Index must not be negative";
            return new DeletionCommand(index);
        }
        case TODO: {
            String description = fullCommand[1];
            if (description.isEmpty()) {
                throw new MissingFieldException("description after the \"todo\" command");
            }
            return new AddCommand(new ToDo(description));
        }
        case DEADLINE: {
            String[] content = fullCommand[1].split(" /by ");
            if (content.length == 0 || content[0].trim().isEmpty()) {
                throw new MissingFieldException("description after the \"deadline\" command");
            } else if (content.length == 1) {
                throw new MissingFieldException("due date after the \"/by\" keyword");
            }
            String description = content[0].trim();
            String date = content[1].trim();
            return new AddCommand(new Deadline(description, date));
        }
        case EVENT: {
            String[] descriptionDates = fullCommand[1].split("/from ");

            if (descriptionDates.length == 0 || descriptionDates[0].trim().isEmpty()) {
                throw new MissingFieldException("description (after the \"event\" command "
                        + "and before specifying start and end dates");
            } else if (descriptionDates.length == 1 || descriptionDates[1].trim().isEmpty()) {
                throw new MissingFieldException("start and end dates "
                        + "after the \"/from\" and \"/to\" keywords respectively");
            }

            String[] startEnd = descriptionDates[1].split("/to ");

            if (startEnd.length == 0 || startEnd[0].trim().isEmpty()) {
                throw new MissingFieldException("start date after the \"/from\" keyword");
            } else if (startEnd.length == 1 || startEnd[1].trim().isEmpty()) {
                throw new MissingFieldException("end date after the \"/to\" keyword");
            }

            String description = descriptionDates[0].trim();
            String start = startEnd[0].trim();
            String end = startEnd[1].trim();

            return new AddCommand(new Event(description, start, end));
        }
        case FIND: {
            if (fullCommand.length < 2 || fullCommand[1].trim().isEmpty()) {
                throw new MissingFieldException("keyword");
            }
            return new FindCommand(fullCommand[1].trim());
        }
        case DOAFTER: {
            String[] content = fullCommand[1].split(" /after ");
            if (content.length == 0 || content[0].trim().isEmpty()) {
                throw new MissingFieldException("description after the \"doafter\" command");
            } else if (content.length == 1) {
                throw new MissingFieldException("start date after the \"/after\" keyword");
            }
            String description = content[0].trim();
            String date = content[1].trim();
            return new AddCommand(new DoAfter(description, date));
        }
        default: {
            throw new InvalidCommandException(instruction);
        }
        }
    }

    /**
     * Commands that are valid.
     */
    private enum CommandWord {
        BYE, LIST, MARK, UNMARK, REMOVE, TODO, DEADLINE, EVENT, FIND, DOAFTER
    }
}
