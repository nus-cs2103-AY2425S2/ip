package tyler.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tyler.command.Command;
import tyler.parser.Parser;
import tyler.storage.Storage;
import tyler.task.Deadline;
import tyler.task.Event;
import tyler.task.Task;
import tyler.task.list.TaskList;
import tyler.ui.Ui;

public class Tyler {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Tyler(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Tyler() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        Command c = Parser.parse(input);
        c.execute(tasks, ui, storage);
        return ui.getMessage();
    }

    public void saveTasks() {
        List<String> formattedTasks = getFormattedTasks(tasks);
        try {
            storage.save(formattedTasks);
        } catch (IOException e) {
            ui.showMessage("\t !!Unable to save tasks!!");
        }
    }

    private static List<String> getFormattedTasks(ArrayList<Task> tasks) {
        List<String> formattedTasks = new ArrayList<>();
        for (Task t: tasks) {
            int completed = t.getStatusIcon().equals("X") ? 1 : 0;
            String formattedTask = t.getCategory() + "|" + completed + "|" + t.getDescription();
            if (t.getCategory().equals("D")) {
                Deadline d = (Deadline) t;
                formattedTask = formattedTask + "|" + d.getBy();
            } else if (t.getCategory().equals("E")) {
                Event e = (Event) t;
                formattedTask = formattedTask + "|" + e.getFrom() + "|" + e.getTo();
            }
            formattedTasks.add(formattedTask);
        }
        return formattedTasks;
    }
}
