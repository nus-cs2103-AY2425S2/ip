package nyanko.command;

import nyanko.storage.Storage;
import nyanko.task.TaskList;
import nyanko.task.ToDo;
import nyanko.ui.Ui;

import java.io.IOException;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String argument) {
        this.description = argument;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ToDo todo = new ToDo(description);
        tasks.addTask(todo);
        String toDoString = "WOW you're so hardworking\n"
                + "ok fine... your task has been added!\nadded: "
                + todo.toString()
                + "\nOh my! You have "
                + tasks.size()
                + " tasks!";
        System.out.println(toDoString);
        storage.save(tasks.getTasks());
    }
}