package mars.command;

import mars.storage.Storage;
import mars.task.Task;
import mars.task.TaskList;
import mars.ui.UI;

import java.util.ArrayList;

public class findCommand extends Command{
    private String keyword;

    /**
     * Constructs findCommand object with the specified
     * keyword
     *
     * @param keyword The word that is used to search for matches
     * among task descriptions
     */

    public findCommand(String keyword){
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching all tasks
     * that contain the keyword
     *
     * @param tasklist
     * @param ui
     * @param storage
     *
     * */

    @Override
    public void execute(TaskList tasklist, UI ui, Storage storage){
        ArrayList<Task> matches = new ArrayList<>();
        String description;

        for(int i=0; i<tasklist.size(); i++){
            Task task = tasklist.get(i);
            description = task.getDescription().toLowerCase();
            if(description.contains(keyword.toLowerCase())){
                matches.add(task);
            }
        }
        if(matches.isEmpty()){
            ui.setResponse("There are no matching tasks in your list:\n");
        } else{
            StringBuilder message = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i=0; i<matches.size(); i++){
                message.append(matches.get(i).toString()).append("\n");
            }
            ui.setResponse(message.toString());
        }
    }


}