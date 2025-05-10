package steve.commands;

import steve.tasks.Contact;
import steve.tasks.TaskManager;

/**
 * Command to handle adding a new contact task to the task manager.
 * This class implements the Command interface and performs the
 * task creation and management when executed.
 */
public class ContactCommand implements Command {
    private TaskManager taskManager;
    private String userInput;

    /**
     * Constructs a ContactCommand instance with the specified TaskManager and user input.
     *
     * @param taskManager The task manager that will handle the task.
     * @param userInput The user input which contains the description for the contact task.
     */
    public ContactCommand(TaskManager taskManager, String userInput) {
        this.taskManager = taskManager;
        this.userInput = userInput;
    }

    /**
     * Executes the contact command by extracting the description from the user input,
     * creating a new Contact task, and adding it to the task manager.
     *
     * @return A message string representing the newly added contact task.
     */
    @Override
    public String execute() {
        String description = userInput.substring("contact".length()).trim();
        Contact contact = new Contact(description);
        taskManager.addContact(contact);
        return contact.messageString();
    }
}
