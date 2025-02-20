package nana.logic;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks and provides methods to manipulate and retrieve tasks.
 */
public class TaskList {


    private static ArrayList<Task> tasks;
    private static int taskCount;

    public TaskList() {
        tasks = new ArrayList<>();
        taskCount = 0;
        assert tasks != null : "Task list should be initialized";
    }

    public TaskList(ArrayList<ArrayList<String>> input_tasks) {
        this.tasks = new ArrayList<>();
        assert tasks != null : "Input tasks should not be null";

        for (ArrayList<String> task : input_tasks) {
            boolean isDone = Boolean.parseBoolean(task.get(0));
            task.remove(0);
            task.remove(0);
            try {
                this.process(task, isDone);
            } catch (NanaException e) {
                Ui.printNanaException(e);
            }
        }

        taskCount = tasks.size();
    }

    /**
     * Processes a list of strings representing a command and its arguments.
     *
     * @param info the list of strings containing the command and arguments
     * @return the result of processing the command
     * @throws NanaException if an error occurs during processing
     */
    public String process(ArrayList<String> info) throws NanaException {
        String input = info.get(0);
        String s = "";
        if (info.get(0).equals("blah")) {
            throw new NanaException("It seems no meaning");
        }
        if (input.equals("list")) {
            s = listTasks();
        } else if (input.equals("mark")) {
            s = markTask(Parser.parseMarkTask(info));
        } else if (input.equals("unmark")) {
            s = unmarkTask(Parser.parseUnmarkTask(info));
        } else if (input.equals("todo")) {
            s = addTodo(Parser.parseAddTodo(info));
        } else if (input.equals("deadline")) {
            s = addDeadline(Parser.parseAddDeadline(info));
        } else if (input.equals("event")) {
            s = addEvent(Parser.parseAddEvent(info));
        } else if (input.equals("delete")) {
            s = deleteTask(Parser.parseDeleteTask(info));
        } else if (input.equals("find")) {
            s = findTask(Parser.parseFindTask(info));
        }
        else {
            throw new NanaException("You need to specific the type of task");
        }
        return s;
    }

    /**
     * Processes a list of strings representing a command and its arguments, with a specified completion status.
     *
     * @param info the list of strings containing the command and arguments
     * @param isDone the completion status of the task
     * @return the result of processing the command
     * @throws NanaException if an error occurs during processing
     */
    public String process(ArrayList<String> info, boolean isDone) throws NanaException {
        String input = info.get(0);

        String s = "";
        assert input.equals("todo") || input.equals("deadline")
                || input.equals("event") : "Task type should be specified";

        if (input.equals("todo")) {
            s = addTodo(Parser.parseAddTodo(info), isDone);
        } else if (input.equals("deadline")) {
            s = addDeadline(Parser.parseAddDeadline(info), isDone);
        } else if (input.equals("event")) {
            s = addEvent(Parser.parseAddEvent(info), isDone);
        }
        return s;
    }

    /**
     * Finds tasks that match the given keyword.
     *
     * @param parsed the list of strings containing the keyword to find
     * @return a string representation of the matching tasks
     */
    public String findTask(ArrayList<String> parsed) {
        ArrayList<Task> matchTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(parsed.get(0))) {
                matchTasks.add(task);
            }
        }
        return Ui.printFindTasks(matchTasks);
    }

    public String listTasks() {
        return Ui.printListTasks(tasks, taskCount);
    }

    public static String listTxt() {
        String txt = "";
        for (int i = 0; i < taskCount; i++) {
            txt += "     " + tasks.get(i).toStorage() + "\n";
        }

        return txt;
    }

    /**
     * Adds a new todo task.
     *
     * @param parsed the list of strings containing the task description
     * @return a string representation of the added task
     */
    public String addTodo(ArrayList<String> parsed) {
        tasks.add(new Todo(parsed.get(0)));
        addCount();
        String s = Ui.printAddTodo(tasks.get(taskCount - 1), taskCount);
        Storage.updateTxt();
        return s;
    }

    /**
     * Adds a new todo task with a specified completion status.
     *
     * @param parsed the list of strings containing the task description
     * @param isDone the completion status of the task
     * @return a string representation of the added task
     */
    public String addTodo(ArrayList<String> parsed, boolean isDone) {
        tasks.add(new Todo(parsed.get(0), isDone));
        addCount();
        String s = Ui.printAddTodo(tasks.get(taskCount - 1), taskCount);
        Storage.updateTxt();
        return s;
    }

    /**
     * Adds a new deadline task.
     *
     * @param parsed the list of strings containing the task description and deadline
     * @return a string representation of the added task
     */
    public String addDeadline(ArrayList<String> parsed) {
        tasks.add(new Deadline(parsed.get(0), parsed.get(1)));
        addCount();
        String s = Ui.printAddDeadline(tasks.get(taskCount - 1), taskCount);
        Storage.updateTxt();
        return s;
    }

    /**
     * Adds a new deadline task with a specified completion status.
     *
     * @param parsed the list of strings containing the task description and deadline
     * @param isDone the completion status of the task
     * @return a string representation of the added task
     */
    public String addDeadline(ArrayList<String> parsed, boolean isDone) {
        tasks.add(new Deadline(parsed.get(0), parsed.get(1), isDone));
        addCount();
        String s = Ui.printAddDeadline(tasks.get(taskCount - 1), taskCount);
        Storage.updateTxt();
        return s;
    }

    /**
     * Adds a new event task.
     *
     * @param parsed the list of strings containing the task description, start time, and end time
     * @return a string representation of the added task
     */
    public String addEvent(ArrayList<String> parsed) {
        tasks.add(new Event(parsed.get(0), parsed.get(1), parsed.get(2)));
        addCount();
        String s = Ui.printAddEvent(tasks.get(taskCount - 1), taskCount);
        Storage.updateTxt();
        return s;
    }

    /**
     * Adds a new event task with a specified completion status.
     *
     * @param parsed the list of strings containing the task description, start time, and end time
     * @param isDone the completion status of the task
     * @return a string representation of the added task
     */
    public String addEvent(ArrayList<String> parsed, boolean isDone) {
        tasks.add(new Event(parsed.get(0), parsed.get(1), parsed.get(2), isDone));
        addCount();
        String s = Ui.printAddEvent(tasks.get(taskCount - 1), taskCount);
        Storage.updateTxt();
        return s;
    }

    /**
     * Deletes a task by its index.
     *
     * @param index the index of the task to be deleted
     * @return a string representation of the deleted task
     */
    public String deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        minsCount();
        String s = Ui.printDeleteTask(task, taskCount);
        Storage.updateTxt();
        return s;
    }

    public String markTask(int index) {
        tasks.get(index - 1).markAsDone();
        String s = Ui.printMarkTask(tasks.get(index - 1));
        Storage.updateTxt();
        return s;
    }

    public String unmarkTask(int index) {
        tasks.get(index - 1).markAsUndone();
        String s = Ui.printUnmarkTask(tasks.get(index - 1));
        Storage.updateTxt();
        return s;
    }

    /**
     * Adds a new task.
     *
     * @param parsed the list of strings containing the task description
     * @return a string representation of the added task
     */
    public String addTask(ArrayList<String> parsed) {
        tasks.add(new Task(parsed.get(0)));
        addCount();
        String s = Ui.printAddTask(tasks.get(taskCount - 1));
        Storage.updateTxt();
        return s;
    }

    /**
     * Adds a new task with a specified completion status.
     *
     * @param parsed the list of strings containing the task description
     * @param isDone the completion status of the task
     * @return a string representation of the added task
     */
    public String addTask(ArrayList<String> parsed, boolean isDone) {
        tasks.add(new Task(parsed.get(0), isDone));
        addCount();
        String s = Ui.printAddTask(tasks.get(taskCount - 1));
        Storage.updateTxt();
        return s;
    }

    private void addCount() {
        taskCount++;
    }

    private void minsCount() {
        taskCount--;
    }
}
