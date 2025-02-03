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

    public static void addDeadline(String input) throws WrongFormatException{
        try{
            String[] getDeadline = input.split("/by ");
            String desc = getDeadline[0].substring(9, getDeadline[0].length());
            if (desc.trim().isEmpty()) {
                throw new WrongFormatException("Oops! the correct format for a deadline is:\n" +
                        "deadline <description> /by <date>");
            }
            Task deadline = new Deadline(desc, getDeadline[1]);

            taskList.add(deadline);
            storage.saveTasks(taskList);
            ackMessage(deadline);
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new WrongFormatException("Oops! the correct format for a deadline is:\n" +
                    "deadline <description> /by <date>");
        }
    }

    public static void addEvent(String input) throws WrongFormatException {
        try {
            String[] getEvent = input.split("/from ");
            String desc = getEvent[0].substring(6, getEvent[0].length());
            if (desc.trim().isEmpty()) {
                throw new WrongFormatException("Oops! the correct format for an event is:\n" +
                        "event <description> /from <date> /to <date>");
            }
            String[] getTime = getEvent[1].split("/to ");
            Task event = new Event(desc, getTime[0], getTime[1]);

            taskList.add(event);
            storage.saveTasks(taskList);
            ackMessage(event);
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new WrongFormatException("Oops! the correct format for an event is:\n" +
                    "event <description> /from <date> /to <date>");
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