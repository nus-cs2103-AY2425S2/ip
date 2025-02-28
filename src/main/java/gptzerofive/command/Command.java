package gptzerofive.command;

import gptzerofive.exception.GptException;
import gptzerofive.notes.Note;
import gptzerofive.storage.Storage;
import gptzerofive.task.Task;
import gptzerofive.task.TaskList;
import gptzerofive.ui.Ui;

/**
 * Represents a command to be executed.
 */
public abstract class Command {
    public abstract String exec(TaskList taskList, Ui ui, Storage storage) throws GptException;
}

class ListCommand extends Command {
    @Override
    public String exec(TaskList taskList, Ui ui, Storage storage) {
        return ui.showTaskList(taskList.getTaskListString());
    }
}

class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String exec(TaskList taskList, Ui ui, Storage storage) throws GptException {
        if (index < 1 || index > taskList.size()) {
            return ui.formattedPrint("Sorry, this task doesn't exist.");
        }
        Task task = taskList.getTask(index - 1);
        task.markAsDone();
        storage.saveToFile(taskList);
        return ui.formattedPrint("Nice! I've marked this task as done:\n" + task.toString());
    }
}

class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String exec(TaskList taskList, Ui ui, Storage storage) {
        if (task.getDescription().isEmpty()) {
            return ui.formattedPrint("Task description cannot be empty.");
        }
        taskList.addTask(task);

        storage.saveToFile(taskList);
        return ui.formattedPrint("Got it. I've added this task:\n" + task.toString() + "\nNow you have "
                + taskList.size() + " tasks in the list.");
    }
}

class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String exec(TaskList taskList, Ui ui, Storage storage) throws GptException {
        if (index < 1 || index > taskList.size()) {
            return ui.formattedPrint("Sorry, this task doesn't exist.");
        }
        Task task = taskList.removeTask(index - 1);

        storage.saveToFile(taskList);
        return ui.formattedPrint("Noted. I've removed this task:\n" + task.toString() + "\nNow you have "
                + taskList.size() + " tasks in the list.");
    }
}

class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String exec(TaskList taskList, Ui ui, Storage storage) {
        if (keyword.isEmpty()) {
            return ui.formattedPrint("Keyword cannot be empty.");
        }
        TaskList filteredTaskList = taskList.filterTasks(keyword);
        return ui.printFilteredTaskList(filteredTaskList.getTaskListString());
    }
}

class ErrorCommand extends Command {
    private final String err;

    public ErrorCommand(String err) {
        this.err = err;
    }

    @Override
    public String exec(TaskList taskList, Ui ui, Storage storage) {
        return ui.formattedPrint(err);
    }
}

class NewNoteCommand extends Command {
    private final Integer index;
    private final String note;

    public NewNoteCommand(Integer index, String note) {
        this.index = index;
        this.note = note;
    }

    @Override
    public String exec(TaskList taskList, Ui ui, Storage storage) throws GptException {
        if (index == null || note == null) {
            return ui.formattedPrint("Index and note cannot be null.");
        }
        if (index < 1 || index > taskList.size()) {
            return ui.formattedPrint("Sorry, this task doesn't exist.");
        }
        Task task = taskList.getTask(index - 1);
        task.setNote(new Note(note));

        storage.saveToFile(taskList);
        return ui.formattedPrint("Got it. I've added a note to this task:\n" + task.toString());
    }
}

class ShowNoteCommand extends Command {
    private final int index;

    public ShowNoteCommand(int index) {
        this.index = index;
    }

    @Override
    public String exec(TaskList taskList, Ui ui, Storage storage) throws GptException {
        if (index < 1 || index > taskList.size()) {
            return ui.formattedPrint("Sorry, this task doesn't exist.");
        }
        Task task = taskList.getTask(index - 1);
        return ui.formattedPrint(task.getNote() == null ? "This task has no note." : task.getNote());
    }
}

class DeleteNoteCommand extends Command {
    private final int index;

    public DeleteNoteCommand(int index) {
        this.index = index;
    }

    @Override
    public String exec(TaskList taskList, Ui ui, Storage storage) throws GptException {
        if (index < 1 || index > taskList.size()) {
            return ui.formattedPrint("Sorry, this task doesn't exist.");
        }
        Task task = taskList.getTask(index - 1);
        task.setNote(new Note(""));

        storage.saveToFile(taskList);
        return ui.formattedPrint("Got it. I've removed the note from this task:\n" + task.toString());
    }
}

class EditNoteCommand extends Command {
    private final int index;
    private final String note;

    public EditNoteCommand(int index, String note) {
        this.index = index;
        this.note = note;
    }

    @Override
    public String exec(TaskList taskList, Ui ui, Storage storage) throws GptException {
        if (index < 1 || index > taskList.size()) {
            return ui.formattedPrint("Sorry, this task doesn't exist.");
        }
        Task task = taskList.getTask(index - 1);
        task.setNote(new Note(note));

        storage.saveToFile(taskList);
        return ui.formattedPrint("Got it. I've edited the note for this task:\n" + task.toString());
    }
}
