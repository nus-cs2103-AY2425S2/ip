package teddy;

public class Teddy {

    private final Ui ui;
    private final TaskList tasks;
    private static final String FILE_PATH = "./data/teddy.txt";

    public Teddy() {
        this.ui = new Ui();
        Storage storage = new Storage(FILE_PATH);
        this.tasks = new TaskList(storage);
    }

    // Runs Teddy (for CLI)
    public void run() {
        ui.welcome();

        while (true) {
            try {
                String input = ui.readCommand();
                System.out.println(getResponse(input)); // Calls the response method
            } catch (TeddyException e) {
                ui.error("\n" + e.getMessage() + "\n");
            } catch (Exception e) {
                ui.error("\nSomething went wrong. Please try again.\n");
            }
        }
    }

    // Processes input and returns response for GUI
    public String getResponse(String input) throws TeddyException {
        try {
            String[] parts = Parser.splitInput(input);
            Command command = Parser.parseCommand(parts);

            switch (command) {
                case BYE:
                    return "Bye! Hope to see you again soon!";
                case LIST:
                    return tasks.printTasks();
                case MARK:
                    return tasks.markTask(parts);
                case UNMARK:
                    return tasks.unmarkTask(parts);
                case TODO:
                    return tasks.addTodo(parts);
                case DEADLINE:
                    return tasks.addDeadline(parts);
                case EVENT:
                    return tasks.addEvent(parts[1]);
                case DELETE:
                    return tasks.deleteTask(parts);
                case FIND:
                    return tasks.find(parts[1]);
                default:
                    throw new TeddyException("I don't understand the command: " + parts[0]);
            }
        } catch (TeddyException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Something went wrong. Please try again.";
        }
    }

    public static void main(String[] args) {
        new Teddy().run();
    }
}
