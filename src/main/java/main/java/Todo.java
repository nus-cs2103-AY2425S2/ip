package main.java;

import Darartole.exception.EmptyBotException;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileForm() {
        return "T" + " | " + super.toFileForm();
    }

    /**
     * Converts the text in the txt to the object Todo
     *
     * @param isDone whether the todo has been finished.
     * @param description the content of the todo.
     * @return Todo object having the same information.
     */
    public static Todo fromFileForm(boolean isDone, String description) {
        Todo td = new Todo(description);
        if (isDone) {
            td.setDone();
        }
        return td;
    }

    /**
     * Adds the Todo task in the tasklist after getting the input
     *
     * @param input input string by the user.
     * @param tasks tasklist in the chatbot.
     * @param fileStored the storage saved by the chatbot.
     * @return String message returned to the user.
     */
    public static String addTodo(String input, Tasklist tasks, Storage fileStored) {
        try {
            String following = input.substring(4).trim();
            if (following.isEmpty()) {
                throw new EmptyBotException("Cannot be empty task.");
            }
            Todo todo = new Todo(following);
            tasks.addTask(todo);
            StringBuilder res = new StringBuilder();
            res.append("Add one todo").append("\n").append(todo.toString())
                    .append("\n").append("Now you have " + tasks.size() + " tasks in the list.");
            fileStored.save(tasks);
            return res.toString();
        } catch (EmptyBotException e) {
            return "ILLEGAL INPUT!" + "\n" + e.getMessage();
        }
    }
}
