package aquadem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

/**
 * A class that handles the bulk of the logic of the chatbot.
 */
public class Execution {
    public Execution() {

    }

    /**
     * Converts a string into an Object of type LocalDateTime.
     * @param date
     * @return LocalDateTime corresponding to the date.
     */
    public static LocalDateTime dateConvert(String date) {

        try {
            String dateProcessed = date;
            while(Character.isWhitespace(dateProcessed.charAt(0))) {
                dateProcessed = dateProcessed.substring(1);
            }
            assert !Character.isWhitespace(dateProcessed.charAt(0));
            DateTimeFormatter acceptedFormat =
                    new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toFormatter();
            return LocalDateTime.parse(dateProcessed, acceptedFormat);
        } catch(DateTimeParseException e) {
            System.out.println("Date entered is not a valid format " +
                    "setting default date (1 week from" +
                    " this moment)");
            return LocalDateTime.now().plusWeeks(1);
        }
    }


    /**
     * Executes command based on parsed user input.
     * @param encodedCommand Parsed input
     * @param tasks Tasklist
     * @param storage Storage object to store tasklist
     */
    public static void execute(Pair encodedCommand, TaskList tasks, Storage storage){
        int command = encodedCommand.getHead();
        assert -2 < command && command < 11;
        String[] parsedDetail = encodedCommand.getContents();
        switch (command) {
        case 0:
            listExecution(tasks);
            break;
        case 1:
            deadlineExecution(tasks, storage, parsedDetail);
            break;
        case 2:
            eventExecution(tasks, storage, parsedDetail);
            break;
        case 3:
            todoExecution(tasks, storage, parsedDetail);
            break;
        case 4:
            markExecution(tasks, storage, parsedDetail);
            break;
        case 5:
            unmarkExecution(tasks, storage, parsedDetail);
            break;
        case 6:;
            deleteExecution(tasks, storage, parsedDetail);
            break;
        case 7:
            dateExecution(tasks, storage, parsedDetail);
            break;
        case 8:
            byeExecution();
            break;
        case 9:
            findExecution(tasks, parsedDetail);
            break;
        case 10:
            doafterExecution(tasks, storage, parsedDetail);
            break;
        default:
            Ui.printError();
            break;

        }
    }

    /**
     * Executes list printing.
     * @param tasks
     */

    private static void listExecution(TaskList tasks) {
        Ui.printList(tasks);
    }

    /**
     * Executes doafters.
     * @param tasks
     * @param storage
     * @param parsedDetail
     */
    private static void doafterExecution(TaskList tasks, Storage storage, String[] parsedDetail) {
        Task a1 = new Doafter(parsedDetail[0], parsedDetail[1]);
        tasks.add(a1);
        Ui.printAdded(a1);
        Ui.printRemaining(tasks);
        storage.saveTasks(tasks);
    }

    /**
     * Executes finding.
     * @param tasks
     * @param parsedDetail
     */
    private static void findExecution(TaskList tasks, String[] parsedDetail) {
        TaskList found = tasks.findTask(parsedDetail[0]);
        Ui.printFound();
        listExecution(found);
    }

    /**
     * Executes exit sequence.
     */
    private static void byeExecution() {
        Ui.printBye();
    }

    /**
     * Executes date printing.
     * @param tasks
     * @param storage
     * @param parsedDetail
     */
    private static void dateExecution(TaskList tasks, Storage storage, String[] parsedDetail) {
        int a = Integer.parseInt(parsedDetail[0])-1;
        Task d = tasks.get(a);
        Ui.printDate(d);
        storage.saveTasks(tasks);
    }

    /**
     * Executes delete command.
     * @param tasks
     * @param storage
     * @param parsedDetail
     */
    private static void deleteExecution(TaskList tasks, Storage storage, String[] parsedDetail) {
        int i = Integer.parseInt(parsedDetail[0])-1;
        Task de1 = tasks.get(i);
        tasks.remove(i);
        Ui.printDeleted(de1);
        Ui.printRemaining(tasks);
        storage.saveTasks(tasks);
    }

    /**
     * Executes unmarking.
     * @param tasks
     * @param storage
     * @param parsedDetail
     */
    private static void unmarkExecution(TaskList tasks, Storage storage, String[] parsedDetail) {
        Task u1 = tasks.get(Integer.parseInt(parsedDetail[0])-1);
        u1.markAsUndone();
        Ui.printUnmarked(u1);
        storage.saveTasks(tasks);
    }

    /**
     * Executres marking.
     * @param tasks
     * @param storage
     * @param parsedDetail
     */
    private static void markExecution(TaskList tasks, Storage storage, String[] parsedDetail) {
        Task m1 = tasks.get(Integer.parseInt(parsedDetail[0])-1);
        m1.markAsDone();
        Ui.printMarked(m1);
        storage.saveTasks(tasks);
    }

    /**
     * Exectues todos.
     * @param tasks
     * @param storage
     * @param parsedDetail
     */
    private static void todoExecution(TaskList tasks, Storage storage, String[] parsedDetail) {
        Task t1 = new Todo(parsedDetail[0]);
        tasks.add(t1);
        Ui.printAdded(t1);
        Ui.printRemaining(tasks);
        storage.saveTasks(tasks);
    }

    /**
     * Executes events.
     * @param tasks
     * @param storage
     * @param parsedDetail
     */
    private static void eventExecution(TaskList tasks, Storage storage, String[] parsedDetail) {
        Task e1 = new Event(parsedDetail[0], parsedDetail[1], parsedDetail[2]);
        tasks.add(e1);
        Ui.printAdded(e1);
        Ui.printRemaining(tasks);
        storage.saveTasks(tasks);
    }

    /**
     * Executes deadlines.
     * @param tasks
     * @param storage
     * @param parsedDetail
     */
    private static void deadlineExecution(TaskList tasks, Storage storage, String[] parsedDetail) {
        LocalDateTime d1Date = dateConvert(parsedDetail[1]);
        Task d1 = new Deadline(parsedDetail[0], parsedDetail[1]);
        d1.setDate(d1Date);
        tasks.add(d1);
        Ui.printAdded(d1);
        Ui.printRemaining(tasks);
        storage.saveTasks(tasks);
    }
}
