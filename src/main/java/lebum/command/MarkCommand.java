package lebum.command;
import lebum.exception.DukeException;
import lebum.main.Storage;
import lebum.main.TaskList;
import lebum.main.Ui;
import lebum.task.Task;

/**
 * Mark tasks as done command.
 */
public class MarkCommand extends Command {
    private String[] parts;
    private String response = "";
    public MarkCommand(String[] parts) {
        this.parts = parts;
    }
    public String getResponse() {
        return this.response;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        try {
            if (parts.length < 2) {
                throw new DukeException("OOPS!!! Please provide a task number to mark.");
            }
            if (isMassCommand(parts)) {
                response += "You have marked the following tasks as done:\n";
                massMark(tasks, storage);
            }
            else {
                int index = Integer.parseInt(parts[1]) - 1;
                Task curTask = tasks.array().get(index);
                response = "You have marked " + curTask.getTitle() + " as done.";
                curTask.markDone();
            }
            storage.saveToFile(tasks);
        }
        catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The task number must be a number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Please provide a task number to mark.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The task number is invalid.");
        }
    }

    public void massMark(TaskList tasks, Storage storage) {
        String content = parts[1];
        String[] indexArray = content.split(",");
        for (int i = 0; i < indexArray.length; i++) {
            int index = Integer.parseInt(indexArray[i]);
            Task t = tasks.array().get(index - 1);
            t.markDone();
            response += t.print() + "\n";
        }
        storage.saveToFile(tasks);
    }
}
