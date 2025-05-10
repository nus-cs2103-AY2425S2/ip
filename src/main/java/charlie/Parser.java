package charlie;

import static java.lang.Integer.parseInt;

/**
 * The Parser class is responsible for interpreting user input commands and performing actions
 * on the TaskList. It handles various actions like marking tasks, adding tasks, listing tasks, etc.
 */
class Parser {
    private String action;
    private String task;

    Parser(String input) {
        try {
            String[] words = input.split(" ", 2);
            this.action = words[0];
            this.task = words[1];
        } catch (Exception e) {
            String[] words = input.split(" ", 2);
            this.action = words[0];
            this.task = "";
        }
    }


    /**
     * Executes the corresponding action based on the parsed input.
     * The action is matched to a specific command and then the corresponding method of the TaskList is called.
     *
     * @param taskList The task list on which the action should be performed.
     */

    public String getResponse(TaskList taskList) {
        String response;
        switch (this.action) {
        case "" -> {
            response = "Please tell me what to do\n" + Ui.printHelp();
        }
        case "list" -> {
            response = taskList.listTasks();
        }
        case "mark" -> {
            CharlieHandler handler = new CharlieHandler();
            if (handler.isValidNumber(task)) {
                response = taskList.markTask(parseInt(task));
            } else {
                response = handler.getErrorMessage();
            }
        }
        case "unmark" -> {
            CharlieHandler handler = new CharlieHandler();
            if (handler.isValidNumber(task)) {
                response = taskList.unmarkTask(parseInt(task));
            } else {
                response = handler.getErrorMessage();
            }
        }
        case "todo" -> {
            CharlieHandler handler = new CharlieHandler();
            if (handler.isValidTodo(task)) {
                response = taskList.addTask(new Todo(task));
            } else {
                response = handler.getErrorMessage();
            }
        }
        case "deadline" -> {
            CharlieHandler handler = new CharlieHandler();
            if (handler.isValidDeadline(task)) {
                response = taskList.addTask(new Deadline(task));
            } else {
                response = handler.getErrorMessage();
            }
        }
        case "event" -> {
            CharlieHandler handler = new CharlieHandler();
            if (handler.isValidEvent(task)) {
                response = taskList.addTask(new Event(task));
            } else {
                response = handler.getErrorMessage();
            }
        }
        case "delete" -> {
            CharlieHandler handler = new CharlieHandler();
            if (handler.isValidNumber(task)) {
                response = taskList.deleteTask(parseInt(task));
            } else {
                response = handler.getErrorMessage();
            }
        }
        case "find" -> {
            response = taskList.findTask(task.trim());
        }
        case "bye" -> {
            response = "Goodbye!";
        }
        default -> response = Ui.printHelp();
        }
        return response;
    }

}



