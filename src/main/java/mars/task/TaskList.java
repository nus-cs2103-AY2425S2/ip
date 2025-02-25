package mars.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import mars.marsException;

/**
 * Wrapper class to an ArrayList of tasks
 * provides add, delete, mark, and unmark operations
 */
public class TaskList {
    static final DateTimeFormatter YEAR_MONTH_DAY = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // output: 2025-02-22 1430
    static final DateTimeFormatter MONTH_DAY_YEAR = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"); // output: Feb 22 2025, 2:30 PM
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList instance with an input list of tasks.
     *
     * @param tasks The initial list of tasks in string format.
     */
    public TaskList(ArrayList<String> tasks) {
        this.tasks = new ArrayList<>();
        try{
        for (String line : tasks) {
            String desc;
            boolean isDone = (line.charAt(4) == 'X');
            if (line.charAt(1) == 'T') {
                /* unmarked task should follow format: [T][ ] run with an empty
                space in between second box so that line.substring() can be standardised to index 7 */
                desc = line.substring(7);
                this.add(new Todo(desc, isDone));
            } else if (line.charAt(1) == 'D') {
                String remaining_line = line.substring(7);
                String[] split_line = remaining_line.split(" \\(by: ");
                String name = split_line[0];
                String endDate = split_line[1].split("\\)")[0];
                String formattedEndDate = LocalDateTime.parse(endDate, MONTH_DAY_YEAR).format(YEAR_MONTH_DAY);
                desc= name + " by " + formattedEndDate;
                this.add(new Deadline(desc, isDone));
            }
            else if (line.charAt(1) == 'D' &&  line.charAt(2) == 'W') {
                String remaining_line = line.substring(8);
                String[] split_line = remaining_line.split(" between: ");
                String name = split_line[0];
                String[] dates = split_line[1].split(" and: ");
                String formattedStartDate = LocalDateTime.parse(dates[0], MONTH_DAY_YEAR).format(YEAR_MONTH_DAY);
                String formattedEndDate = LocalDateTime.parse(dates[1], MONTH_DAY_YEAR).format(YEAR_MONTH_DAY);
                desc = name + " between: " + formattedStartDate + " and " + formattedEndDate;
                this.add(new DoWithin(desc, isDone));
            }
            else {
                String remaining_line = line.substring(7);
                String[] split_line = remaining_line.split(" \\(from: ");
                String name = split_line[0];
                String[] dates = split_line[1].split(" to: ");
                String startDate = dates[0];
                String formattedStartDate = LocalDateTime.parse(startDate, MONTH_DAY_YEAR).format(YEAR_MONTH_DAY);
                String endDate = dates[1].split("\\)")[0];
                String formattedEndDate = LocalDateTime.parse(endDate, MONTH_DAY_YEAR).format(YEAR_MONTH_DAY);
                desc = name + " from " + formattedStartDate + " to " + formattedEndDate;
                this.add(new Event(desc, isDone));
            }
        }
        } catch (PatternSyntaxException e) {
           System.out.println("PatternSyntaxException: " + e.getMessage());
        }
    }

    /**
     * @return length of the ArrayList<Task>
     */
    public int size() {
         return tasks.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index array index of the task to be deleted
     * @throws marsException if the index is out of bounds.
     */
    public void delete(int index) throws marsException {
        if (index < 0 || index >= tasks.size()) {
                throw new marsException("invalid task number. Please try deleting again");
        }
        tasks.remove(index);
    }

    /**
     * Marks task as done at the specified index.
     *
     * @param index array index of the task to be marked as done
     * @throws marsException If the index is out of bounds.
     */
        public void mark(int index) throws marsException {
            if (index < 0 || index >= tasks.size()) {
                throw new marsException("invalid task number. Please mark again");
            }
            tasks.get(index).mark();
        }

        /**
         * Unmarks a task at the specified index.
         *
         * @param index array index of the task to be unmarked
         * @throws marsException If the index is out of bounds.
         */
        public void unmark(int index) throws marsException {
            if (index < 0 || index >= tasks.size()) {
                throw new marsException("invalid task number. Please unmark again");
            }
            tasks.get(index).unmark();
        }

        /**
         * @return The list of tasks as an ArrayList<Task>
         */
        public ArrayList<Task> getTasks() {
            return tasks;
        }

    /**
     * @return the task at the specified index
     */


    public Task get(int index) throws marsException {
        if (index < 0 || index >= tasks.size()) {
            throw new marsException("invalid task number. Please try to get the specified task again");
        }
        return tasks.get(index);
    }

    /**
     * Prints the task list to the console.
     */
        public void printList() {
            if (tasks.isEmpty()) {
                System.out.println("list is empty");
            }
            else if(tasks.size() == 1){
                System.out.println("  Now you have 1 task in the list.\n");
            }
            else {
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.\n");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            }
        }
        public Task getLast(){
            int size = this.size();
            return tasks.get(size -1);
        }
}
