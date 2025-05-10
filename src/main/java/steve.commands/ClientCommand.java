package steve.commands;

import java.util.List;

import steve.tasks.Contact;
import steve.tasks.TaskManager;

/**
 * Constructs a ClientCommand with the specified task manager.
 *
 * The task manager that manages the list of clients.
 */
public class ClientCommand implements Command {
    private TaskManager taskManager;

    public ClientCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Executes the client command
     *
     * @return A message string representing the list of contacts
     */
    @Override
    public String execute() {
        List<Contact> clients = taskManager.getClients();
        int i = 1;
        String result = "Client list: \n";
        for (Contact c : clients) {
            result = result + i + ". " + c.contactDetails() + "\n";
            i++;
        }
        return result;
    }
}
