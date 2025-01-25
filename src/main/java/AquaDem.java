import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
public class AquaDem {
    public class Task {
        protected String description;
        protected boolean isDone;

        protected String statusIcon = " ";
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }


        public void markAsDone() {
            this.isDone = true;
            this.statusIcon = "X";
        }

        public void markAsUndone() {
            this.isDone = false;
            this.statusIcon = " ";
        }
        @Override
        public String toString() {
            String str = String.format("[%s] %s", this.statusIcon, this.description);
            return str;
        }

        //...
    }

    public AquaDem(){

    }
    private static final String bar = "____________________________________________________________";
    public String intro() {
        String str = bar + "\n" +  "Hello! I'm AquaDem \n" +
                "What can I do for you?\n" + bar + "\n";
        return str;
    }

    public void Printiterlist(List<Task> vals) {
        int v = 1;
        for(Task t: vals){
            String s = t.toString();
            System.out.println(v + ": " + s);
            v++;
        }

    }

    public void marker(List<Task> vals) {

    }

    public void running() {
        List<Task> tasks = new ArrayList<>();
        Scanner inputter = new Scanner(System.in);
        String input = inputter.nextLine();
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
                    Printiterlist(tasks);
                    System.out.println(bar);
                    input = inputter.nextLine();
                    break;
                case "mark":
                    Task t1 = tasks.get(Integer.parseInt(detail)-1);
                    t1.markAsDone();
                    System.out.println("Task marked: " + t1);
                    System.out.println(bar);
                    input = inputter.nextLine();
                    break;
                case "unmark":
                    Task t2 = tasks.get(Integer.parseInt(detail)-1);
                    t2.markAsUndone();
                    System.out.println("Task unmarked: " + t2);
                    System.out.println(bar);
                    input = inputter.nextLine();
                    break;
                default:
                    Task t3 = new Task(input);
                    tasks.add(t3);
                    System.out.println("added: " + t3 + "\n");
                    System.out.println(bar);
                    input = inputter.nextLine();
                    break;
            }

//            if(Objects.equals(input, "list")) {
//                Printiterlist(tasks);
//                input = inputter.nextLine();
//            } else {
//                tasks.add(new Task(input));
//                System.out.println("added: " + input + "\n");
//
//                input = inputter.nextLine();
//            }
        }
        System.out.println(bar);
        System.out.println("Cya again soon ;)" + "\n");
        System.out.println(bar);
    }

    public static void main(String[] args) {
        AquaDem chatbot = new AquaDem();
        System.out.print((chatbot.intro()));
        chatbot.running();
    }
}
