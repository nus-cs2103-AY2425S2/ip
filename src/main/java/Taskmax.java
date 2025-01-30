import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Taskmax {
    private static final String FILE_PATH = "data/tasks.txt";
    private static Storage storage = new Storage(FILE_PATH);

    public static void main(String[] args) {
        int limit = 100;  //Maximum number of tasks
        Mascot mascot = new Mascot();  //Taskmax's illustration
        ArrayList<Task> tasks; //Task arraylist
        try {
            tasks = new ArrayList<>(storage.loadTasks());
            System.out.println("Loaded previous tasks from file.");
        } catch (IOException e) {
            System.out.println("Error loading tasks. Starting with an empty list.");
            tasks = new ArrayList<>();
        }

        String line = "-".repeat(100) + "\n";
        String tooMany = "Your task list is full, get to work first!";
        String intro = "Greetings! I'm Taskmax, Your personal tasking companion.\n"
                     + "What can I schedule for you today?\n";
        System.out.println(line + intro + line + mascot + line
                           + "\nEnter \"hello!\" to begin\n" + line);

        Scanner scan = new Scanner(System.in);
        String input;

        while (true) {
            input = scan.nextLine();
             if (input.equals("bye")) {   //bye input
                try {
                    storage.saveTasks(tasks); // save tasks before exiting
                    System.out.println("Tasks have been saved to my drive!");
                } catch (IOException e){
                    System.out.println("Error saving tasks to my drive!");
                }

                System.out.println(line
                        + "\nI hope that you are satisfied with your service.\n"
                        + "See you again soon!\n"
                        + line);
                break;
            } else if (input.equals("list")) {  //list input
                System.out.println(line
                        + "\nHere are the tasks in your list:\n"
                        + line);
                for (int i = 0; i < tasks.size(); i++) {
                    int num = i + 1;
                    System.out.println(num + "." + tasks.get(i).toString());
                }
                System.out.println("\n" + line);
            } else if (input.startsWith("mark ")) {
                try {//mark input as done
                int num = Integer.parseInt(input.substring(5)) - 1;
                    if(num < 0 || num >= tasks.size()) {
                        throw new TaskmaxException("Please enter a number of an existing task so I can find it in the list!");
                    }
                        tasks.get(num).markAsDone();
                        System.out.println("Nice! I've marked your task as done.\n"
                                            + "Keep up the good work!\n"
                                            + line);
                    } catch (TaskmaxException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("unmark ")) {   //mark input as undone
                try {
                    int num = Integer.parseInt(input.substring(7)) - 1;
                if(num < 0 || num >= tasks.size()) {
                    throw new TaskmaxException("Please enter a number of an existing task so I can find it in the list!");
                }
                tasks.get(num).markAsNotDone();
                    System.out.println("I've unmarked your task.\n"
                            + "Don't give up on it yet!\n"
                            + line);
                    } catch (TaskmaxException e) {
                System.out.println(e.getMessage());
            }
                }
                else if (input.startsWith("delete ")) {
                    try {
                        int num = Integer.parseInt(input.substring(7)) - 1;
                        if (num < 0 || num >= tasks.size()) {
                            throw new TaskmaxException("Please enter a number of an existing task so I can find it in the list!");
                        }
                        Task toRemove = tasks.remove(num);
                        System.out.println(line + "\nNoted. I've removed this task:\n"
                                + toRemove.toString()
                                + "\nNow you have " + tasks.size() + " tasks in the list.\n"
                                + line);
                } catch (TaskmaxException e) {
                    System.out.println(e.getMessage());
                    }
                } else if (input.startsWith("todo ")) {  //To do tasks
                    if(tasks.size() >= limit) {
                        System.out.println(tooMany);
                        break;
                    }
                    try {
                        String description = input.substring(5);
                        if (description.equals("")) {
                            throw new TaskmaxException("You have to include a task to add!\n"
                                    + "e.g todo Assignmnet1");
                        }
                        tasks.add(new ToDo(description));
                        System.out.println(line + "\nGot it. I've added this task:\n  "
                                + tasks.get(tasks.size()-1).toString()
                                + "\nNow you have " + tasks.size() + " tasks in the list.\n"
                                + line);
                    } catch (TaskmaxException e) {
                        System.out.println(e.getMessage());
                    }
            } else if (input.startsWith("deadline")) {  //Deadline tasks
                if (tasks.size() >= limit) {
                    System.out.println(tooMany);
                    break;
                }
                try {
                    String[] sections = input.substring(9).split("/by");
                if (sections.length != 2) {
                    throw new TaskmaxException("Oops! You have to include a \"/by deadline\" after your task\n"
                            + "e.g. deadline Assignment2 /by Sunday\n"
                            + "Please ry again!");
                }
                tasks.add(new Deadline(sections[0], sections[1]));
                System.out.println(line + "\nGot it. I've added this task:\n  "
                        + tasks.get(tasks.size() - 1).toString()
                        + "\nNow you have " + tasks.size() + " tasks in the list.\n"
                        + line);
            } catch (TaskmaxException e){
                System.out.println(e.getMessage());
            }
            } else if (input.startsWith("event")) {     //Event tasks
                if(tasks.size() >= limit) {
                    System.out.println(tooMany);
                    break;
                }
                try {
                    String[] sections = input.substring(6).split("/from | /to");
                    if (sections.length != 3) {
                        throw new TaskmaxException("Oops! You have to include a \"/from start /to end\" after your task\n"
                                + "e.g. event Concert /from Monday 3am /to Monday 4pm\n"
                                + "Please try again!");
                    }
                    tasks.add(new Event(sections[0], sections[1], sections[2]));
                    System.out.println(line + "\nGot it. I've added this task:\n  "
                            + tasks.get(tasks.size() - 1).toString()
                            + "\nNow you have " + tasks.size() + " tasks in the list.\n"
                            + line);
                } catch (TaskmaxException e) {
                    System.out.println(e.getMessage());
                }
            } else if (tasks.size() >= limit) {
                System.out.println(tooMany);
                break;
            } else {
                  System.out.println("Hey there! There are 7 things I can help you with! \n"
                          + "\n1. List: Enter \"list\" and I will list out all the tasks you have given me!\n"
                          + "2. ToDo: Enter \"todo theTaskName\" to add a task you plan to do!\n"
                          + "3. Deadlines: Enter \"deadline theTaskName /by date\" to add a task with a specific deadline!\n"
                          + "4. Events: Enter \"event theTaskName /from start period /to end period\" to add an event!\n"
                          + "5. Delete: Enter \"delete theTaskName\" to delete a task from the list!\n"
                          + "6. Mark as done: Enter \"mark TaskListNumber\" to mark the task as complete in the list!\n"
                          + "7. Mark as undone: Enter \"unmark TaskListNumber\" to mark the task as incomplete in the list!\n"
                          + "\nIf you need a refresher, just enter any word!"
                          + "\nIf you are satisfied with your service, just enter \"bye\"\n"
                          + "\nDo remember that my input receptors are sensitive so please be careful with your spelling"
                          + "\nand capital letters for commands!\n"
                          + "\nThat is all and happy scheduling! ~Taskmax :D\n" + line);
            }
        }
        scan.close();
    }
}
