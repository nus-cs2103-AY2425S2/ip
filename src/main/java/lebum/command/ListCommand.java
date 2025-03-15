package lebum.command;
import lebum.main.Storage;
import lebum.main.TaskList;
import lebum.main.Ui;
import lebum.task.Task;
public class ListCommand extends Command{
    private String response = "Your current list:\n";
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        for (int i = 0; i < tasks.getNum_of_tasks(); i++) {
            Task t = tasks.array().get(i);
            int index = i + 1;
            System.out.println(index + ". " + t.print());
            response += index + ". " + t.print() + "\n";
        }
    }
    @Override
    public String getResponse() {
        return this.response;
    }

}
