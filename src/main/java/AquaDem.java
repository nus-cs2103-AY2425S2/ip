import java.io.File;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class AquaDem implements Serializable{
    private static final String FILE_PATH = "./src/main/data/Aquadem.ser";
    private ArrayList<Task> tasks;

    public void saveTasks(ArrayList<Task> tasks){
        try {
            File dataList = new File(FILE_PATH);
            if(dataList.createNewFile()) {
                //
            } else {
                //
            }
            FileOutputStream fileSerialized = new FileOutputStream(FILE_PATH);
            ObjectOutputStream taskSerialized = new ObjectOutputStream(fileSerialized);
            taskSerialized.writeObject(tasks);
        } catch (IOException e) {

            
        }
    }

    public void loadTasks(){
        try {
            FileInputStream fileDeSerialized = new FileInputStream(FILE_PATH);
            ObjectInputStream taskDeSerialized = new ObjectInputStream(fileDeSerialized);
            this.tasks = (ArrayList<Task>) taskDeSerialized.readObject();

        } catch (IOException | ClassNotFoundException e) {

            this.tasks = new ArrayList<Task>();
        }
    }


    public class Deadline extends Task implements Serializable {

        protected String by;
        protected LocalDateTime dueDate;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by:" + by + ")";
        }
    }
    public class Todo extends Task implements Serializable{

        protected String by;

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public class Event extends Task implements Serializable {

        protected String t1;

        protected String t2;
        public Event(String description, String t1, String t2) {
            super(description);
            this.t1 = t1;
            this.t2 = t2;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from:" + t1 + "to:" + t2 +  ")";
        }
    }

    public class customException extends Exception{
        private String msg;
        public customException(String msg){
            super(msg);
            this.msg = msg;
        }

        @Override
        public String getMessage() {

            return "Ohno somethings not right! : " + msg + "\n"+ bar;
        }
    }

    public class detailException extends customException{

        public detailException(String msg) {
            super(msg);
        }
    }

    public class deadlineException extends detailException{

        public deadlineException(String msg) {
            super(msg);
        }
    }

    public class eventException extends detailException{

        public eventException(String msg) {
            super(msg);
        }
    }

    public class todoException extends detailException{

        public todoException(String msg) {
            super(msg);
        }
    }

    public class markAndUnmarkException extends detailException{

        public markAndUnmarkException(String msg) {
            super(msg);
        }
    }


;

    public AquaDem(){
        this.tasks = new ArrayList<Task>();
    }
    private static final String bar = "____________________________________________________________";
    public String intro() {
        String str = bar + "\n" +  "Hello! I'm AquaDem\n" +
                "What can I do for you?\n" +
                "fyi: yyyy-MM-dd HH:mm is the valid date format for a deadline\n" + bar + "\n";
        return str;
    }

    public void printIterlist(List<Task> vals) {
        int v = 1;
        for(Task t: vals){
            String s = t.toString();
            System.out.println(v + ": " + s);
            v++;
        }

    }

    public void marker(List<Task> vals) {

    }

    public void detailCheck(String detail) throws detailException {
        if (detail.trim().isEmpty()) {
            throw new detailException("Sorry i need something more in my knowledge space...");
        }
    }

    public void numCheck(String detail, int limit) throws markAndUnmarkException{
        try {
            int i = Integer.parseInt(detail)-1;
            if (i >= limit || i < 0) {
                throw new markAndUnmarkException("that task does not exist... maybe you finished it?");
            }

        }
        catch (NumberFormatException e) {
            throw new markAndUnmarkException("you need a number here...");
        }
    }

    public void deadlineCheck(String detail) throws deadlineException {
        if (detail == ""){
            throw new deadlineException("if you have no work then why you setting a deadline???");
        } else if (!detail.contains("/by")) {
            throw new deadlineException("Without a deadline to submit by, your work gonna be a dead line, get it....");
        }
    }

    public void eventCheck(String detail) throws eventException{
        if (detail.equals("")){
            throw new eventException("event for what? , for who? when ?????");
        } else if (!detail.contains("/from") && !detail.contains("/to")){
            throw new eventException("From when to when?????");
        } else if ((detail.contains("/from") && !detail.contains("/to")) ||(!detail.contains("/from") && detail.contains("/to"))) {
            throw new eventException("From and To please");
        }
    }

    public void todoCheck(String detail) throws todoException{
        if (detail == ""){
            throw new todoException("You need to-do something, todo something, get it...");
        }
    }

    public LocalDateTime dateConvert(String date) {

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

    public String runninginputs(Scanner inputter) {
        String input;
        while(true)
            try {
                input = inputter.nextLine();
                detailCheck(input);
                break;
            } catch(detailException e) {
                System.out.println(e.getMessage());
            }
        return input;
    }

    public void running() {
        Scanner inputter = new Scanner(System.in);
        String input = runninginputs(inputter);
        while (!Objects.equals(input, "bye")) {
            System.out.println(bar);
            String arr[] = input.split(" ",2);

            String command = arr[0];
            String detail = "";
            if (arr.length > 1) {
                detail = arr[1];
            }
            switch(command) {
                case "list":
                    printIterlist(this.tasks);
                    System.out.println(bar);
                    input = inputter.nextLine();
                    break;
                case "deadline":

                    try {
                        deadlineCheck(detail);
                        String deadlineString[] = detail.split("/by",2);
                        LocalDateTime d1Date = dateConvert(deadlineString[1]);
                        Task d1 = new Deadline(deadlineString[0],deadlineString[1]);
                        d1.setDate(d1Date);
                        tasks.add(d1);
                        System.out.println("Okay : ), added: " + d1 + "\n");
                        System.out.println("You have " + tasks.size() + " tasks in the list ;)");
                        System.out.println(bar);
                        this.saveTasks(tasks);
                        input = runninginputs(inputter);

                        break;
                    } catch(detailException e) {
                        System.out.println(e.getMessage());
                        input = runninginputs(inputter);
                        break;
                    }


                case "event":
                    try {
                        eventCheck(detail);
                        String eventString1[] = detail.split("/from",2);
                        String eventString2[] = eventString1[1].split("/to",2);
                        Task e1 = new Event(eventString1[0],eventString2[0],eventString2[1]);
                        tasks.add(e1);
                        System.out.println("Okay : ), added: " + e1 + "\n");
                        System.out.println("You have " + tasks.size() + " tasks in the list ;)");
                        System.out.println(bar);
                        this.saveTasks(tasks);
                        input = runninginputs(inputter);
                        break;
                    } catch(detailException e) {
                        System.out.println(e.getMessage());
                        input = runninginputs(inputter);
                        break;
                    }

                case "todo":

                    try {
                        todoCheck(detail);
                        Task to1 = new Todo(detail);
                        tasks.add(to1);
                        System.out.println("Okay : ), added: " + to1 + "\n");
                        System.out.println("You have " + tasks.size() + " tasks in the list ;)");
                        System.out.println(bar);
                        this.saveTasks(tasks);
                        input = runninginputs(inputter);

                        break;
                    } catch(detailException e) {
                        System.out.println(e.getMessage());
                        input = runninginputs(inputter);
                        break;
                    }
                case "mark":
                    try {
                        numCheck(detail, tasks.size());
                        Task t1 = tasks.get(Integer.parseInt(detail)-1);
                        t1.markAsDone();
                        System.out.println("Task marked: " + t1);
                        System.out.println(bar);
                        this.saveTasks(tasks);
                        input = runninginputs(inputter);

                        break;
                    } catch(detailException e) {
                        System.out.println(e.getMessage());
                        input = runninginputs(inputter);
                        break;
                    }


                case "unmark":
                    try {
                        numCheck(detail, tasks.size());
                        Task t2 = tasks.get(Integer.parseInt(detail)-1);
                        t2.markAsUndone();
                        System.out.println("Task unmarked: " + t2);
                        System.out.println(bar);
                        this.saveTasks(tasks);
                        input = runninginputs(inputter);

                        break;
                    } catch(detailException e) {
                        System.out.println(e.getMessage());
                        input = runninginputs(inputter);
                        break;
                    }
                case "delete":
                    try {
                        numCheck(detail, tasks.size());
                        int i = Integer.parseInt(detail)-1;
                        Task t2 = tasks.get(i);
                        tasks.remove(i);
                        System.out.println("Task deleted: " + t2);
                        System.out.println("you have " + tasks.size() +" tasks left");
                        System.out.println(bar);
                        this.saveTasks(tasks);
                        input = runninginputs(inputter);

                        break;
                    } catch(detailException e) {
                        System.out.println(e.getMessage());
                        input = runninginputs(inputter);
                        break;
                    }
                case "getdate":
                    try {
                        numCheck(detail, tasks.size());
                        int i = Integer.parseInt(detail)-1;
                        Task t = tasks.get(i);
                        if (t instanceof Deadline) {
                            System.out.println("Your deadline date is: ");
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
                            String niceDate = t.getDate().format(format);
                            System.out.println(niceDate);
                            System.out.println(bar);
                            break;
                        } else {
                            System.out.println("This task has no deadline date");
                            System.out.println(bar);
                            break;

                        }

                    } catch(detailException e) {
                        System.out.println(e.getMessage());
                        input = runninginputs(inputter);
                        break;

                    }

                default:
                    System.out.println("I dont know what that is sorry : (");
                    System.out.println(bar);
                    input = runninginputs(inputter);
                    break;
            }

        }
        System.out.println(bar);
        System.out.println("Cya again soon ;)" + "\n");
        System.out.println(bar);
    }

    public static void main(String[] args) {
        AquaDem chatbot = new AquaDem();
        System.out.print((chatbot.intro()));
        chatbot.loadTasks();
        chatbot.running();

    }
}
