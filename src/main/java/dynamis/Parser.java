package dynamis;

/**
 * Handles the processing of user input as well as
 * the stored tasks data.
 */
public class Parser {

    private static final int DEADLINE_PARTS = 2;
    private static final int EVENT_PARTS = 3;

    /**
     * Converts a Task Object to a presentable String. Used to output List of Tasks.
     *
     * @param task The task to convert to string.
     * @return The presentable string format for the task.
     */
    public String serialiseTask(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.getIsDone() ? "1" : "0") + " | " + task.name;
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            String dueDate = (deadline.dueBy != null) ? deadline.dueBy.toString() : deadline.dueByString;
            return "D | " + (task.getIsDone() ? "1" : "0") + " | " + task.name + " | " + dueDate;
        } else if (task instanceof Event) {
            Event event = (Event) task;
            String start = (event.start != null) ? event.start.toString() : event.startString;
            String end = (event.end != null) ? event.end.toString() : event.endString;
            return "E | " + (task.getIsDone() ? "1" : "0") + " | " + task.name
                    + " | " + start + " | " + end;
        } else {
            return "";
        }
    }

    /**
     * Converts the presentable string format of a task back to a Task object.
     *
     * @param line The string formatted version of a task.
     * @return The task converted from the given string.
     */
    public Task deserializeTask(String line) {
        String[] parts = line.split(" \\| ");
        Task task = null;
        switch (parts[0]) {
        case "T":
            task = new Todo(parts[2]);
            break;
        case "D":
            task = new Deadline(parts[2], parts[3]);
            break;
        case "E":
            task = new Event(parts[2], parts[3], parts[4]);
            break;
        }
        if (task != null && parts[1].equals("1")) {
            task.editIsDone(true);
        }
        return task;
    }

    /**
     * Processes the input given by the user and performs
     * respective actions and returns the respective output.
     * @param input The user's input command.
     * @param tasks The TaskList object to add or delete tasks from.
     * @param ui The ui object, used to display respective responses.
     * @return The response for the chatbot.
     */
    public String processInput(String input, TaskList tasks, Ui ui) {
        if (input.equals("bye")) {
            return "Bye";
        } else if (input.equals("help")) {
            return ui.printHelpMessage();
        } else if (input.equals("list")) {
            return ui.printTaskList(tasks);
        } else if (input.matches("mark \\d+")) { //used regex.com to check regex used.
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            return tasks.markItem(taskNumber);
        } else if (input.startsWith("todo ")) {
            if (!input.substring(5).equals("")) {
                return tasks.addItem(new Todo(input.substring(5)));
            }
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ");
            if (parts.length == DEADLINE_PARTS) {
                return tasks.addItem(new Deadline(parts[0], parts[1]));
            }
        } else if (input.startsWith("event ")) {
            String [] parts = input.substring(6).split(" /from | /to ");
            if (parts.length == EVENT_PARTS) {
                return tasks.addItem(new Event(parts[0], parts[1], parts[2]));
            }
        } else if (input.matches("delete \\d+")) {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            return tasks.deleteItem(taskNumber);
        } else if (input.startsWith("find ")) {
            assert (input.length() > 5);
            return tasks.findTasks(input.substring(5));
        }
        return ui.printIncorrectUsageError();
    }
}