import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Julie {
    private static final String BREAK = "______________________________________________\n";
    private static final String INTRO = "Hello! I'm Julie\nWhat can I do for you?\n";
    private static final String EXIT = "Bye. See you later!\n";
    private static final String FILE_PATH = "./data/julie.txt";
    private static ArrayList<Task> taskList;
    private static Storage storage;

    public static void showTask(ArrayList<Task> list) {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < list.size(); i++) {
            int label = i + 1;
            Task item = list.get(i);
            System.out.println(label + ". " + item.toString());
        }
    }

    public static void toggleMarked(int label) {
        int index = label - 1;
        try {
            Task currTask = taskList.get(index);
            currTask.markDone();
            System.out.println(BREAK + "Nice! I've marked this task as done!");
            System.out.println(currTask.toString() + "\n" + BREAK);

            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            System.out.print(BREAK + "Sorry, that task cannot be marked as it isn't in the list!\n" + BREAK);
        }
    }

    public static void toggleUnmarked(int label) {
        int index = label - 1;
        try {
            Task currTask = taskList.get(index);
            currTask.markUndone();
            System.out.println(BREAK + "Okay, I have marked this task as undone!");
            System.out.println(currTask.toString() + "\n" + BREAK);

            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            System.out.print(BREAK + "Sorry, that task cannot be unmarked as it isn't in the list!\n" + BREAK);
        }
    }

    public static String mergeToString(String[] arr, int start, int stop) {
        return String.join(" ", java.util.Arrays.copyOfRange(arr, start, stop));
    }

    public static void ackMessage(Task task) {
        System.out.print(BREAK + "Got it. I've added this task to the list:\n" + task.toString() + "\n" +
                "Now you have " + taskList.size() + " tasks in the list.\n" + BREAK );
    }

    public static void addToDo(String[] input) throws WrongFormatException {
        if (input.length == 1) {
            throw new WrongFormatException("Oops! The correct format for a todo is:\ntodo <description>");
        }
        Task todo = new ToDo(mergeToString(input, 1, input.length));

        taskList.add(todo);
        storage.saveTasks(taskList);
        ackMessage(todo);

    }

    public static void addDeadline(String input) throws WrongFormatException {
        try {
            String desc = (input.length() > 9)
                    ? input.substring(9).split("/by", 2)[0].trim()
                    : "";
            String[] getDeadline = input.split("/by ");
            String dateTime = (getDeadline.length > 1) ? getDeadline[1].trim() : "";

            if (desc.isEmpty() && dateTime.isEmpty()) {
                throw new WrongFormatException("Oops! Missing both the deadline description and the due date/time!\n" +
                        "The correct format is:\n deadline <description> /by <DD-MM-YYYY HHMM>\n" +
                        "e.g. deadline Finish math homework /by 10-02-2025 1800");
            }
            if (desc.isEmpty()) {
                throw new WrongFormatException("Oops! Missing deadline description! The correct format is:\n" +
                        "deadline <description> /by <DD-MM-YYYY HHMM>");
            }
            if (dateTime.isEmpty()) {
                throw new WrongFormatException("Oops! Missing date and time of deadline! The correct format is:\n" +
                        "deadline <description> /by <DD-MM-YYYY HHMM>");
            }

            String[] dateTimeParts = dateTime.split(" ");

            if (dateTimeParts.length < 2) {
                if (dateTimeParts[0].matches("\\d{4}")) {
                    throw new WrongFormatException("Oops! Your deadline is missing the due date! " +
                            "The correct format is:\n deadline <description> /by <DD-MM-YYYY HHMM>\n" +
                            "e.g. deadline Finish math homework /by 10-02-2025 1800");
                } else {
                    throw new WrongFormatException("Oops! Your deadline is missing the due time! " +
                            "The correct format is:\n deadline <description> /by <DD-MM-YYYY HHMM>\n" +
                            "e.g. deadline Finish math homework /by 10-02-2025 1800");
                }
            }

            Task deadline = new Deadline(desc, dateTime);
            taskList.add(deadline);
            storage.saveTasks(taskList);
            ackMessage(deadline);

        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new WrongFormatException("Oops! Wrong format! The correct format for a deadline is:\n" +
                    "deadline <description> /by <DD-MM-YYYY HHMM>");
        }
    }

    public static void addEvent(String input) throws WrongFormatException {
        try {
            String desc = (input.length() > 6)
                    ? input.substring(6).split("/from", 2)[0].trim()
                    : "";
            String[] getEvent = input.split("/from ");
            String dateTimeFrom = (getEvent.length > 1)
                    ? getEvent[1].split("/to", 2)[0].trim()
                    : "";
            String dateTimeTo = (getEvent.length > 1 && getEvent[1].contains("/to"))
                    ? getEvent[1].split("/to", 2)[1].trim()
                    : "";

            if (desc.isEmpty() && dateTimeFrom.isEmpty() && dateTimeTo.isEmpty()) {
                throw new WrongFormatException("Oops! Missing the event description and start/end date/time!\n" +
                        "The correct format is:\nevent <description> /from <DD-MM-YYYY HHMM> /to <DD-MM-YYYY HHMM>\n" +
                        "e.g. event Team meeting /from 10-02-2025 1400 /to 10-02-2025 1600");
            }
            if (desc.trim().isEmpty()) {
                throw new WrongFormatException("Oops! Missing description! The correct format is:\n" +
                        "event <description> /from <DD-MM-YYYY HHMM> /to <DD-MM-YYYY HHMM>");
            }
            if (dateTimeFrom.isEmpty()) {
                throw new WrongFormatException("Oops! Missing the start date and time of the event! " +
                        "The correct format is:\nevent <description> /from <DD-MM-YYYY HHMM> /to <DD-MM-YYYY HHMM>");
            }
            if (dateTimeTo.isEmpty()) {
                throw new WrongFormatException("Oops! Missing the end date and time of the event! " +
                        "The correct format is:\nevent <description> /from <DD-MM-YYYY HHMM> /to <DD-MM-YYYY HHMM>");
            }

            String[] dateTimeFromParts = dateTimeFrom.split(" ");
            String[] dateTimeToParts = dateTimeTo.split(" ");

            if (dateTimeFromParts.length < 2) {
                throw new WrongFormatException("Oops! Your event is missing the full start date and time! " +
                        "The correct format is:\nevent <description> /from <DD-MM-YYYY HHMM> /to <DD-MM-YYYY HHMM>");
            }
            if (dateTimeToParts.length < 2) {
                throw new WrongFormatException("Oops! Your event is missing the full end date and time! " +
                        "The correct format is:\nevent <description> /from <DD-MM-YYYY HHMM> /to <DD-MM-YYYY HHMM>");
            }

            Task event = new Event(desc, dateTimeFrom, dateTimeTo);
            taskList.add(event);
            storage.saveTasks(taskList);
            ackMessage(event);

        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException | DateTimeParseException e) {
            throw new WrongFormatException("Oops! Wrong format! The correct format for an event is:\n" +
                    "event <description> /from <DD-MM-YYYY HHMM> /to <DD-MM-YYYY HHMM>");
        }
    }

    public static void deleteTask(int num) {
        try{
            int index = num - 1;
            int listSize = taskList.size() - 1;
            System.out.print(BREAK + "Noted, the following task has been removed:\n" + taskList.get(index).toString() +
                    "\n" + "Now you have " + listSize + " tasks in the list.\n" + BREAK);

            taskList.remove(index);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            System.out.print(BREAK + "Sorry, that task cannot be deleted as it isn't in the list!\n" + BREAK);
        }
    }

    public static void handleCommand(String input) {
        try {
            String[] parts = input.split(" ");
            if (parts[0].equals("todo")) {
                addToDo(parts);
            } else if (parts[0].equals("deadline")) {
                addDeadline(input);
            } else if (parts[0].equals("event")) {
                addEvent(input);
            } else if (parts.length == 2) {
                try {
                    int label = Integer.parseInt(parts[1]);
                    if (parts[0].equals("mark")) {
                        toggleMarked(label);
                    } else if (parts[0].equals("unmark")) {
                        toggleUnmarked(label);
                    } else if (parts[0].equals("delete")) {
                        deleteTask(label);
                    } else {
                        throw new WrongFormatException("Sorry, I didn't understand what you said!");
                    }
                } catch (NumberFormatException e) {
                    throw new WrongFormatException(
                            "Sorry, that command must be followed by an integer in the format: <command> <integer>!"
                    );
                }
            } else {
                if (parts[0].equals("mark")) {
                    throw new WrongFormatException(
                            "Sorry, that's the wrong format for marking! Here's the correct format:\n" +
                                    "mark <task number>"
                    );
                } else if (parts[0].equals("unmark")) {
                    throw new WrongFormatException(
                            "Sorry, that's the wrong format for unmarking! Here's the correct format:\n" +
                                    "unmark <task number>"
                    );
                } else if (parts[0].equals("delete")) {
                    throw new WrongFormatException(
                            "Sorry, that's the wrong format for deletion! Here's the correct format:\n" +
                                    "delete <task number>"
                    );
                } else {
                    throw new WrongFormatException("Sorry, I didn't understand what you said!");
                }
            }
        } catch (WrongFormatException e) {
            System.out.println(BREAK + e.getMessage() + "\n" + BREAK);
        }
    }

    public static void main(String[] args) {
        System.out.println(BREAK + INTRO + BREAK);

        storage = new Storage(FILE_PATH);
        taskList = new ArrayList<>(storage.loadTasks());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.print(BREAK);
                showTask(taskList);
                System.out.print(BREAK);
            } else {
                handleCommand(input);
            }
        }
        System.out.println(BREAK + EXIT + BREAK);
    }
}