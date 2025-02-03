import java.util.Scanner;

public class Brownie{
    private static final String horizontalLine = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tasks tasks = new Tasks();

        String greeting = "\nHello! I'm BROWNIE\nWhat can I do for you?\n";
        String farewell = "Bye. Hope to see you again soon!\n";

        System.out.println(horizontalLine + greeting + horizontalLine);

        while(true) {
            try {
                String taskDescription = scanner.nextLine();
                int scriptLength = taskDescription.length();
                if (taskDescription.equalsIgnoreCase("bye")) {
                    System.out.println(farewell + horizontalLine);
                    break;
                }

                // check for list command. Automatically skip anything that is less than 4 characters
                // skips to next iteration after listing tasks
                if (taskDescription.equalsIgnoreCase("list")) {
                    System.out.println(horizontalLine);
                    tasks.list();
                    System.out.println(horizontalLine);
                    continue;
                }

                // mark task in list
                // parses task number as int, parses true if task is to be marked
                if (taskDescription.startsWith("mark")) {
                    if (scriptLength < 6) {
                        throw new DukeException("Which task would you like me to mark done? (please give me a number)");
                    }
                    tasks.updateTask(Integer.parseInt(taskDescription.substring(5)), true);
                    System.out.println(horizontalLine);
                    continue;
                }

                // unmark task in list
                // parses task number as int, parses false if task is to be unmarked
                if (taskDescription.startsWith("unmark")) {
                    if (scriptLength < 8) {
                        throw new DukeException("Which task would you like me to unmark? (please give me a number)");
                    }
                    tasks.updateTask(Integer.parseInt(taskDescription.substring(7)), false);
                    System.out.println(horizontalLine);
                    continue;
                }

                // delete task from list
                if (taskDescription.startsWith("delete")) {
                    if (scriptLength < 8) {
                        throw new DukeException("Which task would you like me to delete? (please give me a number)");
                    }

                    tasks.deleteTask(Integer.parseInt(taskDescription.substring(7)));
                    System.out.println(horizontalLine);
                    continue;
                }

                // automatically add any task that is listed
                // checks for todos, events and deadlines, initialise to appropriate task type
                System.out.println(horizontalLine);
                if (taskDescription.startsWith("todo")) {
                    if (scriptLength < 6) {
                        throw new DukeException("Hello! Can I confirm what task you would like to note down?\n" +
                                "ToDo tasks must look like: todo xxxxxxxx");
                    }
                    tasks.add(taskDescription.substring(5), 'T');
                    System.out.println(horizontalLine);
                } else if (taskDescription.startsWith("event")) {
                    if (scriptLength < 7) {
                        throw new DukeException("Hello! Can I confirm what task you would like to note down?\n" +
                                "Event tasks must look like: event xxxxxxxx /from xxx /to xxx");
                    }
                    tasks.add(taskDescription.substring(6), 'E');
                    System.out.println(horizontalLine);
                } else if (taskDescription.startsWith("deadline")) {
                    if (scriptLength < 10) {
                        throw new DukeException("Hello! Can I confirm what task you would like to note down?\n" +
                                "Deadline tasks must look like: event xxxxxxxx /by xxx");
                    }
                    tasks.add(taskDescription.substring(9), 'D');
                    System.out.println(horizontalLine);
                } else {
                    throw new DukeException("I'm sorry... requests must start with 'todo', 'event' or 'deadline'");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Unfortunately I cannot process your request. Would you like to try again?");
            }
        }

        scanner.close();
    }
}
