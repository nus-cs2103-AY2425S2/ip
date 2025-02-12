import frontend.SirDuke;

/** Welcome to SirDuke!
 *<p>
 *     Using the chatbot:
 *     - To view the list, use the following command:
 *     list
 *
 *      - To add a To Do to the list, use the following format:
 *     deadline/description
 *
 *      - To add a Deadline to the list, use the following format:
 *     deadline/description/time to be completed by
 *
 *      - To add an Event to the list, use the following format:
 *      event/description/start time/end time
 *
 *      - To mark a task as done, use the following format:
 *      mark/task index
 *
 *      - To unmark a task as done, use the following format:
 *      unmark/task index
 *
 *      - To delete a task, use the following format:
 *      delete/task index
 *
 *      - To close the chatbot, use the following command:
 *      bye
 *</p>
 */
public class Main {

    public static void main(String[] args) {
        SirDuke chatBot = new SirDuke();
        chatBot.start();
    }
}
