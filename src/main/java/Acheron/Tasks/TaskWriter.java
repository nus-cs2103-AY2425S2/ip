package Acheron.Tasks;

import java.time.LocalDate;

import Acheron.Exceptions.BadDateExceptions;
import Acheron.Exceptions.CorruptedFileException;
import Acheron.Exceptions.GenericExceptions;

/**
 * A class that does work to extract the required information out of any input
 * pertaining to the creatioon of any task
 */
public class TaskWriter {

    /**
     * Creates a task
     * @param input The text input by the user
     * @param taskList The task list object
     * @throws GenericExceptions Throws any errors that occur in some of the usbmethods
     */
    public static void createTask(String input, TaskList taskList) throws GenericExceptions {
        Task newTask;
        input = input.strip();
        if (input.contains("todo")) {
            String taskName = input.substring(input.indexOf(" ") + 1, input.length());
            newTask = new ToDos(taskName, false);
        } else if (input.contains("deadline")) {
            String taskWithDate = input.substring(input.indexOf(' ') + 1, input.length());
            String taskName = taskWithDate.substring(0, taskWithDate.indexOf("/by") - 1);
            String deadline = taskWithDate.substring(taskWithDate.indexOf("by") + 3, taskWithDate.length());
            checkalidDate(deadline);
            assert deadline.length() == 10;
            newTask = new Deadline(taskName, false, deadline);
        } else if (input.contains("event")) {
            String taskWithDate = input.substring(input.indexOf(' ') + 1, input.length());
            String taskName = taskWithDate.substring(0, taskWithDate.indexOf("/from") - 1);
            String startDate = taskWithDate.substring(taskWithDate.indexOf("from") + 5,
                    taskWithDate.indexOf("/to") - 1);
            String endDate = taskWithDate.substring(taskWithDate.indexOf("to") + 3, taskWithDate.length());
            checkalidDate(startDate);
            checkalidDate(endDate);
            checkValidStartAndEndDate(startDate, endDate);
            assert startDate.length() == 10;
            assert endDate.length() == 10;
            newTask = new Events(taskName, false, startDate, endDate);
        } else {
            throw new GenericExceptions();
        }
        taskList.addTask(newTask);
    }

    /**
     * Checks if the date in the input is correctly formatted
     * @param date to be checked
     * @throws BadDateExceptions if date in valid
     */
    private static void checkalidDate(String date) throws BadDateExceptions {
        if (!date.contains("-")) {
            throw new BadDateExceptions();
        }
        String[] split = date.split("-");
        if (split.length != 3) {
            throw new BadDateExceptions();
        }
        if (split[0].length() != 4 || split[1].length() != 2 || split[2].length() != 2) {
            throw new BadDateExceptions();
        }
    }

    /**
     * Checks if the start date is less than end date
     * @param startDate is the start of the event
     * @param endDate isthe end of the event
     * @throws BadDateExceptions if start date is larger than end date
     */
    private static void checkValidStartAndEndDate(String startDate, String endDate) throws BadDateExceptions {
        LocalDate startDateObject = LocalDate.parse(startDate);
        LocalDate endDateObject = LocalDate.parse(endDate);
        if (startDateObject.getYear() < endDateObject.getYear()) {
            return;
        } else if (startDateObject.getYear() == endDateObject.getYear()) {
            if (startDateObject.getMonth().getValue() < endDateObject.getMonth().getValue()) {
                return;
            } else if (startDateObject.getMonth().getValue() == endDateObject.getMonth().getValue()) {
                if (startDateObject.getDayOfMonth() <= endDateObject.getDayOfMonth()) {
                    return;
                }
            }
        }
        throw new BadDateExceptions();
    }

    /**
     * Creates a task from the saved file
     * @param input A text from the saved file
     * @param taskList A task list object
     * @throws CorruptedFileException Throws a exception if something went wrong
     */
    public static void createSavedTask(String input, TaskList taskList) throws CorruptedFileException {
        try {
            String[] split = input.split("\\|");
            boolean isDone = split[1].equals("X");
            String taskName = split[2];
            if (split[0].equals("T")) {
                taskList.addTaskFromStorage(new ToDos(taskName, isDone));
            } else if (split[0].equals("D")) {
                String endDate = split[3];
                taskList.addTaskFromStorage(new Deadline(taskName, isDone, endDate));
            } else if (split[0].equals("E")) {
                String startDate = split[3];
                String endDate = split[4];
                taskList.addTaskFromStorage(new Events(taskName, isDone, startDate, endDate));
            } else {
                throw new CorruptedFileException();
            }
        } catch (Exception e) {
            throw new CorruptedFileException();
        }
    }
}
