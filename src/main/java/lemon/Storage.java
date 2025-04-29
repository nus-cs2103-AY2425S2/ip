/**
 * This class deals with operations that involves storage such as
 * loading the tasklist file from memory to the chatbot
 * and overwriting the tasklist file after changes are made through the chatbot
 */

package lemon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;


public class Storage {
    private String filePath;

    /**
     * Construct a file for storage with a specified file path
     * @param filePath The path of the file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Check if the file for storage of a specified path is present
     * If not present, create a new file of that path for storage of the tasklist
     * If present, load the content of the file to the chatbot
     * @return The arraylist that contains all the tasks in the specified storage file
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<lemon.Task>();
        File file = new File(filePath);

        if (!file.exists()) {
            //check if a tasklist file is present, and create one if it is not present
            System.out.println("You don't have a file to store tasks yet. Let me create one for you!");
            try {
                if (file.createNewFile()) {
                    System.out.println("You have created a new tasklist file!");
                } else {
                    System.out.println("Tasklist file could not be created");
                }
            } catch (IOException e) {
                System.out.println("Error creating tasklist file" + e.getMessage());
            }
            return tasks;

        } else {
            //load the content of the tasklist file into the Tasks arraylist
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    loadSingleTask(line, tasks);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Tasklist could not be found");
            }
            return tasks;
        }

    }

    /**
     * Load a single task from the text file into the arraylist of tasks
     * @param line A line of user input describing one task
     * @param tasks The arraylist of tasks to which the task is to be added
     */
    public void loadSingleTask(String line, ArrayList<Task> tasks) {
        String taskDescription = line.substring(6);
        String[] splitByCharacter = line.split("");
        String[] splitByPart = taskDescription.split("/");
        if(splitByPart.length == 1) {
            //to do
            Todo todoTask = new Todo(taskDescription);
            if(splitByCharacter[4].equals("X")) {
                todoTask.isDone = true;
            }
            tasks.add(todoTask);
        } else if(splitByPart.length == 2) {
            //deadline
            //problem: the time representation only apply to deadline tasks and not event tasks, not consistent
            String description = splitByPart[0];
            String dateString = splitByPart[1].toLowerCase();
            String[] parts = dateString.split(" ");
            parts[1] = parts[1].substring(0, 1).toUpperCase() + parts[1].substring(1);
            dateString = String.join(" ", parts);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(dateString, formatter);
            Deadline deadlineTask = new Deadline(description, date);
            if(splitByCharacter[4].equals("X")) {
                deadlineTask.isDone = true;
            }
            tasks.add(deadlineTask);
        } else if(splitByPart.length == 3) {
            //event
            String description = splitByPart[0];
            String start = splitByPart[1];
            String end = splitByPart[2];
            Event eventTask = new Event(description, start, end);
            if(splitByCharacter[4].equals("X")) {
                eventTask.isDone = true;
            }
            tasks.add(eventTask);
        }

    }

    /**
     * Write the tasklist to the specified storage file
     * @param tasks The arraylist that contains all the tasks from user input
     */
    public void storeTasks(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter("tasklist.txt")) {
            for(Task task: tasks) {
                String taskString = task.toString();
                writer.write(taskString + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file" + e.getMessage());
        }
    }

}
