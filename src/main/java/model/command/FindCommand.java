package model.command;

import java.util.List;
import java.util.stream.Collectors;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.BoundExtractedResult;
import model.exception.AliceException;
import model.response.Response;
import model.storage.Storage;
import model.task.Task;
import model.task.TaskList;

/**
 * Represents a command to find tasks that match a given keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, searching for tasks that match the keyword.
     * Credit to xdrop for fuzzy search algorithm
     *
     * @param tasks The list of tasks to search within.
     * @param storage The storage to use for any required data operations.
     * @return A Response containing the tasks that match the keyword.
     * @throws AliceException If an error occurs during execution.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) throws AliceException {
        List<Task> taskList = tasks.asList();
        List<BoundExtractedResult<Task>> match = FuzzySearch.extractTop(keyword, taskList, Task::toString, 10);
        List<Task> matchTaskList = match.stream().map(BoundExtractedResult::getReferent).collect(Collectors.toList());
        TaskList matchTasks = new TaskList(matchTaskList);
        return new Response(Response.RESPONSE_TYPE.TASKS_FOUND, matchTasks.toString());
    }
}
