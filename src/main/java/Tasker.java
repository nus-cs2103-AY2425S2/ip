public class Tasker {
    public static void main(String[] args) {
        String separator = "____________________________________________________________";
        String newLineSeparator = separator + "\n";
        String greeting = newLineSeparator
                + "Hello! I'm Tasker\n"
                + "What can I do for you?\n"
                + newLineSeparator
                + "Bye. Hope to see you again soon!\n"
                + separator;
        System.out.println(greeting);
    }
}
