package Acheron.Utility;

import Acheron.CommandInfo.ByeCommandInfo;
import Acheron.CommandInfo.DeadlineCommandInfo;
import Acheron.CommandInfo.DeleteCommandInfo;
import Acheron.CommandInfo.EventCommandInfo;
import Acheron.CommandInfo.FindCommandInfo;
import Acheron.CommandInfo.GenericCommandInfo;
import Acheron.CommandInfo.ListCommandInfo;
import Acheron.CommandInfo.MarkCommandInfo;
import Acheron.CommandInfo.ToDoCommandInfo;
import Acheron.CommandInfo.UnmarkCommandInfo;
import Acheron.Storage.StorageManager;
import Acheron.Tasks.TaskList;
import Acheron.Tasks.TaskWriter;
import Acheron.Ui.Ui;

/**
 * A utility class used to parse the user's input correctly
 */
public class CommandParser {
    private static GenericCommandInfo[] ALL_COMMANDS_INFO = {
        new ListCommandInfo(),
        new MarkCommandInfo(),
        new UnmarkCommandInfo(),
        new FindCommandInfo(),
        new ToDoCommandInfo(),
        new DeadlineCommandInfo(),
        new EventCommandInfo(),
        new DeleteCommandInfo(),
        new ByeCommandInfo(),
    };
    private StorageManager storageManager;
    private TaskList taskList;

    /**
     * A constructor of the class
     * @param storageManager A storage manager instance
     * @param taskList A task list instance
     */
    public CommandParser(StorageManager storageManager, TaskList taskList) {
        this.storageManager = storageManager;
        this.taskList = taskList;
    }

    /**
     * A method to parse the input received by the chat bot
     * Returns the appropriate output based on the parsed
     * input
     * @param input The text keyed in by the user
     */
    public void parseInput(String input) {
        try {
            //Marking and umarking
            if (input.contains("mark")) {
                String[] split = input.split(" ");
                int num = Integer.parseInt(split[1]) - 1;
                if (split[0].equals("unmark")) {
                    taskList.unmarkTask(num);
                } else {
                    taskList.markTask(num);
                }
                storageManager.updateSavedFile(taskList);
                return;
            }

            //Finding
            if (input.contains("find")) {
                int space = input.indexOf(" ");
                String keyword = input.substring(space + 1);
                taskList.findAllTaskWithKeyword(keyword);
                return;
            }

            //Bye
            if (input.equals("bye")) {
                Ui.displayText("Bye. Hope to see you again soon!");
                return;
            }

            //list
            if (input.equals("list")) {
                Ui.displayText(taskList.toString());
                return;
            }

            //delete
            if (input.contains("delete")) {
                String[] split = input.split(" ");
                int num = Integer.parseInt(split[1]) - 1;
                taskList.removeTask(num);
                storageManager.updateSavedFile(taskList);
                return;
            }

            //help
            if (input.contains("help")) {
                StringBuilder output = new StringBuilder();
                output.append("Here are all the commands supported:\n");
                for (GenericCommandInfo commandInfo : ALL_COMMANDS_INFO) {
                    output.append(commandInfo);
                }
                Ui.displayText(output.toString());
                return;
            }

            //add task
            TaskWriter.createTask(input, taskList);
            storageManager.updateSavedFile(taskList);
        } catch (Exception e) {
            Ui.displayText(e.toString());
        }
    }
}
