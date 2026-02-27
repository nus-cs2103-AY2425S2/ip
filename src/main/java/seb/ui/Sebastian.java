package seb.ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Sebastian {

    private static final String FILEPATH = "./data/Sebastian.txt";
    private static Storage storage;
    private Ui ui;
    private static TaskList tasks; // store tasks in the list

    public Sebastian(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = new TaskList(storage.loadTasks());
    }

    /**
     * Prints out data after parsing command-line input and
     * drawing data from tasks stored in TaskList
     */
    public void run() {
        System.out.println("----------------------------------------------");
        ui.welcome();
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("----------------------------------------------");
            String input = s.nextLine();
            System.out.println(getResponse(input));

            if (input.equalsIgnoreCase("bye")) {
                return;
            }
        }
    }

    private String captureOutput(Runnable action) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        PrintStream ps = new PrintStream(outputStream);

        System.setOut(ps);
        action.run();
        System.setOut(originalOut);
        return outputStream.toString().trim();
    }

    public String getResponse(String input) {
        assert input != null : "Input cannot be null.";

        try {
            Command command = Parser.parse(input);

            switch (command.getCommand()) {
            case "BYE":
                return captureOutput(() -> ui.bye());

            case "LIST":
                return captureOutput(() -> ui.list(tasks.getTaskList()));

            case "TODO":
                String desc = Parser.parseTodo(command.getArgs());
                tasks.addTask(new Todo(desc, false));
                storage.saveTasks(tasks.getTaskList());
                return captureOutput(() -> ui.showSuccess("Okay! You have successfully added task: " + desc));

            case "DEADLINE":
                String[] parts = Parser.parseDeadline(command.getArgs());
                tasks.addTask(new Deadline(parts[0], parts[1], false));
                storage.saveTasks(tasks.getTaskList());
                return captureOutput(() -> ui.showSuccess("Yay! You have successfully added deadline: " + parts[0]));

            case "EVENT":
                String[] parts2 = Parser.parseEvent(command.getArgs());
                tasks.addTask(new Event(parts2[0], parts2[1], parts2[2], false));
                storage.saveTasks(tasks.getTaskList());
                return captureOutput(() -> ui.showSuccess("Nice! You have successfully added event: " + parts2[0]));

            case "MARK":
                int index = Parser.parseNum(command.getArgs()) - 1;
                tasks.getTask(index).markDone();
                storage.saveTasks(tasks.getTaskList());
                return captureOutput(() -> ui.showSuccess("Amazing! You have completed: "
                        + tasks.getTask(index).getDescription()));

            case "UNMARK":
                int index2 = Parser.parseNum(command.getArgs()) - 1;
                tasks.getTask(index2).markNotDone();
                storage.saveTasks(tasks.getTaskList());
                return captureOutput(() -> ui.showSuccess("Okay, you have yet to finish: " + tasks.getTask(index2).getDescription()));

            case "DELETE":
                int index3 = Parser.parseNum(command.getArgs()) - 1;
                Task deltask = tasks.getTask(index3);
                tasks.removeTask(index3);
                storage.saveTasks(tasks.getTaskList());
                return captureOutput(() -> ui.showSuccess("Aight! You have deleted: " + deltask.getDescription()));

            case "FIND":
                return captureOutput(() -> ui.find(tasks.getTaskList(), command.getArgs()));

            case "DATE":
                return captureOutput(() -> {
                    try {
                        ui.showDates(tasks.getTaskList(), Parser.parseShowDate(command.getArgs()));
                    } catch (SebException e) {
                        throw new RuntimeException(e);
                    }
                });

            case "UPDATE":
                String[] parts3 = Parser.parseUpdate(command.getArgs());
                Task task = tasks.getTask(Parser.parseNum(parts3[0]) - 1);
                task.update(parts3[1], parts3[2]);
                tasks.updateTask(task, Parser.parseNum(parts3[0]) - 1);
                storage.saveTasks(tasks.getTaskList());
                return captureOutput(() -> ui.showSuccess("Great! You have updated: "
                        + task.getDescription()));

            case "HEY":
                return captureOutput(() -> ui.sebby());

            default:
                return captureOutput(() -> ui.showError("Sorry dear, I didn't understand that."));
            }
        } catch (Exception e) {
            return captureOutput(() -> ui.showError(e.getMessage()));
        }
    }

    public String getWelcomeMessage() {
        return "Hey there my queen! My name is Sebastian~\nHow can I help you? :)";
    }

    public static void main(String[] args) throws SebException {
        new Sebastian(FILEPATH).run();
    }
}
