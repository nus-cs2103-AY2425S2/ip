package seb.ui;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Parses command-line input into "command" and "args"
     * eg "deadline" + "quiz 3/12-01-2025 1600"
     *
     * @param input String input from command-line
     * @return Command object containing a Command string and Args string
     * @throws SebException if command-line input is empty
     */
    public static Command parse(String input) throws SebException {
        assert input != null : "Input cannot be null.";

        if (input.isEmpty()) {
            throw new SebException("Please enter something!");
        }
        String[] parts = input.split(" ", 2);
        String command = parts[0].trim();

        if (parts.length == 1) {
            return new Command(command, "");
        } else {
            return new Command(command, parts[1].trim());
        }
    }

    /**
     * Returns a String that checks if Todo input is an empty task
     *
     * @param input Args of the Command (description of todo)
     * @return String description of todo
     * @throws SebException if todo description is empty
     */
    public static String parseTodo(String input) throws SebException {
        assert input != null : "Input cannot be null.";

        if (input.isEmpty()) {
            throw new SebException("Description cannot be empty!");
        }
        return input;
    }

    /**
     * Returns a String array containing elements of Deadline object
     * eg [description, deadline] split from Args from Command
     *
     * @param input Args of the Command
     * @return String array
     * @throws SebException if format of deadline is incorrect
     */
    public static String[] parseDeadline(String input) throws SebException {
        assert input != null : "Input cannot be null.";

        String[] parts = input.split("/", 4);
        if (parts.length != 2) {
            throw new SebException("Invalid Deadline format. Please use: deadline [name] /[by when]");
        }
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        parts[1] = checkDateValidity(parseDateTime(parts[1]));
        return parts;
    }

    /**
     * Error handling to check if dates entered are valid
     * ie if they are in the future, and start date is before end date
     *
     * @param date date to be checked
     * @return date in correct format IF it is valid
     * @throws SebException if date is invalid
     */
    public static String checkDateValidity(LocalDateTime date) throws SebException {
        if (date.isBefore(LocalDateTime.now())) {
            throw new SebException("Date entered cannot be in the past!");
        }
        DateTimeFormatter formatOutput = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm");
        String formattedDT = date.format(formatOutput);
        return formattedDT;
    }

    public static String[] checkDateValidity(LocalDateTime date1, LocalDateTime date2) throws SebException {
        if (date2.isBefore(date1)) {
            throw new SebException("End date must be after start date!");
        } else if (date2.isBefore(LocalDateTime.now())) {
            throw new SebException("End date entered cannot be in the past!");
        }
        DateTimeFormatter formatOutput = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm");
        String[] parts = {date1.format(formatOutput), date2.format(formatOutput)};
        return parts;
    }

    /**
     * Returns a String array containing elements of Event object
     * eg [description, start, end] split from Args from Command
     *
     * @param input Args of Command object
     * @return String array
     * @throws SebException if format of event is incorrect
     */
    public static String[] parseEvent(String input) throws SebException {
        assert input != null : "Input cannot be null.";

        String[] parts = input.split("/", 4);
        if (parts.length != 3) {
            throw new SebException("Invalid Event format. Please use: event [name] /[from] /[to]");
        }
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        String[] dates = checkDateValidity(parseDateTime(parts[1]), parseDateTime(parts[2]));
        parts[1] = dates[0];
        parts[2] = dates[1];
        return parts;
    }

    /**
     * Returns an integer from command line String input eg "mark 3"
     * will return (int) 3
     *
     * @param input Args of Command object
     * @return integer value of String number
     */
    public static int parseNum(String input) {
        return Integer.parseInt(input);
    }

    /**
     * Return String of date time converted to readable format
     * eg from input 24-01-2025 1600 -> Fri, 24 Jan 2025 16:00
     *
     * @param input String of date time from Command args
     * @return String of date time in reader friendly format
     * @throws SebException for incorrect format of input
     */
    public static LocalDateTime parseDateTime(String input) throws SebException {
        assert input != null : "Input cannot be null.";

        try {
            DateTimeFormatter formatInput = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(input, formatInput);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new SebException("Invalid date-time format! Please use dd-mm-yyyy HHmm");
        }
    }

    /**
     * Returns String of date formatted from input
     * eg 24-01-2025 -> Fri, 24 Jan 2025
     * Used for finding tasks by date in showDates() function in TaskList
     *
     * @param input String from command-line Command args
     * @return String of formatted date
     * @throws SebException for incorrect format of date
     */
    public static String parseShowDate(String input) throws SebException {
        assert input != null : "Input cannot be null.";

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dateTime = LocalDate.parse(input, formatter);
            DateTimeFormatter formatOutput = DateTimeFormatter.ofPattern("E, dd MMM yyyy");
            String ftd = dateTime.format(formatOutput);
            return ftd;
        } catch (DateTimeParseException e) {
            throw new SebException("Invalid date-time format! Please use dd-mm-yyyy");
        }
    }

    /**
     * Parses input following "update" command into String array
     * of index of task in tasklist, and attribute to be edited
     *
     * @param input String input containing index and new value
     * @return String array of [index, detail, new value]
     * @throws SebException for invalid format to update
     */
    public static String[] parseUpdate(String input) throws SebException {
        if (input.isEmpty()) {
            throw new SebException("Please enter the task number and detail to be updated! " +
                    "Eg 'update 3 description Quiz 4'");
        }

        String[] parts = input.split(" ", 3);
        if (parts.length != 3) {
            throw new SebException("Invalid update format. Please use: " +
                    "update [task no.] [task detail] [new detail]");
        }
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return parts;
    }
}
