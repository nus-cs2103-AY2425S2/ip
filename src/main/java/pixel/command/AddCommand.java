package pixel.command;

import pixel.task.Deadline;
import pixel.task.Event;
import pixel.task.TaskList;
import pixel.task.TaskType;
import pixel.task.ToDo;
import pixel.util.Parser;
import pixel.util.PixelException;
import pixel.util.Storage;
import pixel.util.Ui;

/**
 * Represents an addition Command consisting of a TaskType and arguments.
 * On execution, adds a task of that TaskType with details from the arguments to the TaskList,
 * and triggers a Ui response to be displayed.
 * TaskList contents are saved to storage on execution.
 */
public class AddCommand extends Command {
    private final TaskType taskType;
    private final String[] args;

    public AddCommand(TaskType taskType, String[] args) {
        this.taskType = taskType;
        this.args = args;
    }

    /**
     * Parses the task details from the arguments using Parser, creates a Task of the provided TaskType,
     * then adds the Task to the TaskList.
     * Ui response is triggered and TaskList contents are saved to storage after successful task addition.
     *
     * @param taskList TaskList storing the tasks
     * @param storage  Storage object handling disk storage for this instance of Pixel
     * @throws PixelException If taskType is unrecognized, or task details corresponding to the taskType are missing.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws PixelException {
        String[] components;
        String response;
        switch (this.taskType) {
        case TODO:
            components = Parser.parseToDo(this.args);
            response = Ui.addResponse(taskList.addTask(new ToDo(components[0])), taskList.getListSize());
            break;
        case DEADLINE:
            components = Parser.parseDeadline(this.args);
            response = Ui.addResponse(taskList.addTask(new Deadline(components[0],
                            Parser.parseDateTime(components[1]))),
                    taskList.getListSize());
            break;
        case EVENT:
            components = Parser.parseEvent(this.args);
            response = Ui.addResponse(taskList.addTask(new Event(components[0], Parser.parseDateTime(components[1]),
                    Parser.parseDateTime(components[2]))), taskList.getListSize());
            break;
        default:
            throw new PixelException("Please enter a valid task type!");
        }
        storage.save(taskList);
        return response;
    }
}
