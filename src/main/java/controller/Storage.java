package controller;

import datastructure.TaskList;
import taskObjects.*;

import java.io.*;
import java.util.List;
import java.util.Objects;

import exception.InvalidInputException;
import exception.StorageSyntaxException;

/**
 * {@code Storage} class responsible for storage of persistent data
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a {@code Storage} class with the relevant file path
     * to store the persistent data
     *
     * @param filePath The String file path of where the data will be
     *                 stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads from data the list of task that were saved
     *
     * @return a {@code TaskList} class with all saved task loaded in
     * @throws InvalidInputException if input is invalid or in wrong format
     */
    public TaskList load() throws InvalidInputException {
        TaskList taskList = new TaskList();
        File file = new File(this.filePath);
        if (!file.isFile()) {
            assert (Objects.equals(taskList.getTaskList(), "Commander, currently you have no outstanding task"));
            return taskList;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String readerPointer = reader.readLine();
            while (readerPointer != null) {
                AbstractTask abstractTask = parser(readerPointer);
                if (abstractTask != null) {
                    taskList.load(abstractTask);
                }
                readerPointer = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Sorry Commander, there appears to be an error fetching previous task: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Saves task from current instance into persistent data
     *
     * @param taskList {@code TaskList} class that was used in the current session
     */
    public void save(List<? extends AbstractTask> taskList) {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();

        try {
            if (!file.isFile()) {
                file.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (AbstractTask abstractTask : taskList) {
                    writer.write(abstractTask.toFileFormat());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Sorry Commander, there is an error with saving tasks: " + e.getMessage());
        }
    }

    /**
     * Util function to parse plain text data into {@code AbstractTask} class
     *
     * @param input raw String input from the data
     * @return {@code AbstractTask} class respective to the String input
     * @throws InvalidInputException if input is invalid or in wrong format
     */
    private AbstractTask parser(String input) throws InvalidInputException {
        String[] split = input.split(" \\| ");
        String type = split[0].trim();
        boolean isCompleted = split[1].trim().equals("1");
        String description = split[2].trim();
        AbstractTask returnAbstractTask;
        switch (type) {
        case "T":
            returnAbstractTask = new Todo(description, isCompleted);
            break;
        case "D":
            returnAbstractTask = new Deadline(description, isCompleted, split[3].trim());
            break;
        case "E":
            returnAbstractTask = new Event(description, isCompleted, split[3].trim(), split[4].trim());
            break;
        default:
            throw new StorageSyntaxException();
        }
        return returnAbstractTask;
    }

}
