package Aquadem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class Execution {
    public Execution() {

    }

    public static LocalDateTime dateConvert(String date) {

        try {
            String dateProcessed = date;
            if (Character.isWhitespace(date.charAt(0))) {
                dateProcessed = date.substring(1);
            }
            DateTimeFormatter acceptedFormat = new DateTimeFormatterBuilder()
                    .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toFormatter();

            LocalDateTime loDT = LocalDateTime.parse(dateProcessed, acceptedFormat);
            return loDT;
        } catch(DateTimeParseException e) {
            System.out.println("Date entered is not a valid format " +
                    "setting default date (1 week from" +
                    " this moment)");
            return LocalDateTime.now().plusWeeks(1);
        }
    }






    public static void execute(Pair encodedCommand, TaskList tasks, Storage storage){
        int command = encodedCommand.getHead();
        String[] parsedDetail = encodedCommand.getContents();
        switch (command) {
        case 0:
            Ui.printList(tasks);
            break;
        case 1:
            LocalDateTime d1Date = dateConvert(parsedDetail[1]);
            Task d1 = new Deadline(parsedDetail[0], parsedDetail[1]);
            d1.setDate(d1Date);
            tasks.add(d1);
            Ui.printAdded(d1);
            Ui.printRemaining(tasks);
            storage.saveTasks(tasks);
            break;
        case 2:
            Task e1 = new Event(parsedDetail[0], parsedDetail[1], parsedDetail[2]);
            tasks.add(e1);
            Ui.printAdded(e1);
            Ui.printRemaining(tasks);
            storage.saveTasks(tasks);
            break;

        case 3:
            Task t1 = new Todo(parsedDetail[0]);
            tasks.add(t1);
            Ui.printAdded(t1);
            Ui.printRemaining(tasks);
            storage.saveTasks(tasks);
            break;

        case 4:
            Task m1 = tasks.get(Integer.parseInt(parsedDetail[0])-1);
            m1.markAsDone();
            Ui.printMarked(m1);
            storage.saveTasks(tasks);
            break;
        case 5:
            Task u1 = tasks.get(Integer.parseInt(parsedDetail[0])-1);
            u1.markAsUndone();
            Ui.printUnmarked(u1);
            storage.saveTasks(tasks);
            break;
        case 6:
            int i = Integer.parseInt(parsedDetail[0])-1;
            Task de1 = tasks.get(i);
            tasks.remove(i);
            Ui.printDeleted(de1);
            Ui.printRemaining(tasks);
            storage.saveTasks(tasks);
            break;
        case 7:

            int a = Integer.parseInt(parsedDetail[0])-1;
            Task d = tasks.get(a);
            Ui.printDate(d);
            storage.saveTasks(tasks);
            break;
        case 8:
            Ui.printBye();
            break;

        case 9:
            TaskList found = tasks.findTask(parsedDetail[0]);
            Ui.printFound();
            Ui.printList(found);
            break;

        default:
            Ui.printError();
            break;

        }
    }
}
