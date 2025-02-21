package grass;// runs main application

public class Grass {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Grass(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFromTxt());
        } catch (GrassException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
        
        ui = new Ui(tasks);
        ui.startup();
    }

    public String getResponse(String input) {
        String output = "";
        if (input.equals("list")) {
            output += ui.printList();
        }
        else if (input.startsWith("mark")) {
            output += ui.markTask(input);
        }
        else if (input.startsWith("unmark")) {
            output += ui.unmarkTask(input);
        }
        else if (input.startsWith("delete")) {
            output += ui.deleteTask(input);
        }
        else if (input.startsWith("todo")) {
            output += ui.todoTask(input);
        }
        else if (input.startsWith("deadline")) {
            output += ui.deadlineTask(input);
        }
        else if (input.startsWith("event")) {
            output += ui.eventTask(input);
        }
        else if (input.startsWith("find")) {
            output += ui.findTask(input);
        }
        else if (input.equals("bye")) {
            return ui.shutdown();
        }
        else {
            output += ui.invalidCommand();
        }
        try {
            storage.writeToTxt(tasks.getTasks());
        } catch (GrassException e) {
            ui.errorMessage(e.getMessage());
        }
        return output;
    }

//    public static void main(String[] args) {
//        new Grass("./data/grass.txt").run();
//    }
}
