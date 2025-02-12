package duchess;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Duchess class that runs the whole thing.
 */
public class Duchess extends Application {
    private static final String FILE_PATH = "./data/list.txt";

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user_icon.jpg"));
    private Image duchessImage = new Image(this.getClass().getResourceAsStream("/images/duchess_icon.jpg"));

    private TaskList taskList;
    private Parser parser;
    private Storage storage;

    private VBox chatBox;
    private Stage primaryStage;

    /**
     * Constructs a new Duchess application instance.
     * Initializes UI, parser, storage, and loads the task list.
     */
    public Duchess() {
        this.parser = new Parser();
        this.storage = new Storage(FILE_PATH);
        this.taskList = this.storage.loadList();
    }

    /**
     * Adds a Todo task to the task list.
     *
     * @param in The user input containing the task description.
     * @throws DuchessException If the input format is invalid.
     */
    public String addTodo(String in) throws DuchessException {
        String s = "";
        try {
            String[] tokens = in.split(" ");
            String taskName = in.substring(in.indexOf(tokens[1]));
            Todo task = new Todo(taskName);
            this.taskList.add(task);
            s += "added: " + task;
            return s;
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT_TODO);
        }
    }
    /**
     * Adds a Deadline task to the task list.
     *
     * @param in The user input containing the task description and deadline.
     * @throws DuchessException If the input format is invalid.
     */
    public String addDeadline(String in) throws DuchessException {
        String s = "";
        try {
            String byDelimiter = " /by ";

            String[] tokens = in.split(" ");
            String taskName = in.substring(in.indexOf(tokens[1]), in.indexOf(byDelimiter));
            String by = in.substring(in.indexOf(byDelimiter) + byDelimiter.length());
            Deadline task = new Deadline(taskName, by);
            this.taskList.add(task);
            s += "added: " + task;
            return s;
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT_DEADLINE);
        }
    }
    /**
     * Adds an Event task to the task list.
     *
     * @param in The user input containing the task description, start time, and end time.
     * @throws DuchessException If the input format is invalid.
     */
    public String addEvent(String in) throws DuchessException {
        String s = "";
        try {
            String fromDelimiter = " /from ";
            String toDelimiter = " /to ";

            String[] tokens = in.split(" ");
            String taskName = in.substring(in.indexOf(tokens[1]), in.indexOf(fromDelimiter));

            String from = in.substring(in.indexOf(fromDelimiter) + fromDelimiter.length(), in.indexOf(toDelimiter));
            String to = in.substring(in.indexOf(toDelimiter) + toDelimiter.length());
            Event task = new Event(taskName, from, to);
            this.taskList.add(task);
            s += "added: " + task;
            return s;
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT_EVENT);
        }
    }


    /**
     * Marks a task as completed.
     *
     * @param in The user input specifying the task number.
     * @throws DuchessException If the input format is invalid.
     */
    public String mark(String in) throws DuchessException {
        int taskNum;
        String s = "";
        try {
            String taskNumStr = in.split(" ")[1];
            taskNum = Integer.parseInt(taskNumStr);
            this.taskList.get(taskNum - 1).mark();
            s += "Item marked!";
            return s;
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT);
        }
    }

    /**
     * Unmarks a task as not completed.
     *
     * @param in The user input specifying the task number.
     * @throws DuchessException If the input format is invalid.
     */
    public String unmark(String in) throws DuchessException {
        int taskNum;
        String s = "";
        try {
            String taskNumStr = in.split(" ")[1];
            taskNum = Integer.parseInt(taskNumStr);
            this.taskList.get(taskNum - 1).unmark();
            s += "Item marked!";
            return s;
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT);
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param in The user input specifying the task number.
     * @throws DuchessException If the input format is invalid.
     */
    public String deleteTask(String in) throws DuchessException {
        int taskNum = 0;
        String s = "";
        try {
            String taskNumStr = in.split(" ")[1];
            taskNum = Integer.parseInt(taskNumStr);
            if (taskNum < 0 || taskNum > this.taskList.size()) {
                throw new DuchessException(in, ErrorType.INVALID_INDEX);
            }
            Task task = this.taskList.get(taskNum - 1);
            this.taskList.remove(taskNum - 1);
            s += "deleted: " + task;
            return s;
        } catch (DuchessException e) {
            throw e;
        } catch (Exception e) {
            throw new DuchessException(in, ErrorType.INVALID_FORMAT);
        }
    }
    /**
     * Handles unrecognized commands by throwing an exception.
     *
     * @param command The unrecognized command input by the user.
     * @throws DuchessException Always thrown with an invalid command error.
     */
    public void processUnrecognisedCommand(String command) throws DuchessException {
        throw new DuchessException(command, ErrorType.INVALID_COMMAND);
    }

    /**
     * Finds items in the task list that match the keyword and displays it.
     *
     * @param in The command input by the user.
     */
    public String find(String in) {
        String keyword = in.substring(in.indexOf(" ") + 1);
        String s = "";
        ArrayList<Task> matchingTasks = this.taskList.find(keyword);
        s += "Here are the matching tasks in your list:\n";
        for (int i = 0; i < matchingTasks.size(); ++i) {
            s += (i + 1) + ". " + matchingTasks.get(i) + '\n';
        }
        return s;
    }

    /**
     * Prints the list of tasks stored in the TaskList.
     *
     * @param taskList The TaskList containing tasks to be displayed.
     */
    public String formatList(TaskList taskList) {
        String s = "";
        for (int i = 0; i < taskList.size(); ++i) {
            s += (i + 1) + ". " + taskList.get(i) + '\n';
        }
        return s;
    }

    /**
     * Starts the Duchess application, handling user input in a loop.
     */
    public void processInput(String in) {
        if (in.trim().isEmpty()) {
            return;
        }

        String duchessMsg = "";

        DialogBox userDialogBox = new DialogBox(in, userImage, true);
        chatBox.getChildren().addAll(userDialogBox);
        try {
            String[] commandStr = this.parser.parseCommand(in);
            switch(commandStr[0]) {
            case "bye":
                primaryStage.close();
                break;
            case "list":
                duchessMsg = this.formatList(this.taskList);
                break;
            case "mark":
                duchessMsg = this.mark(in);
                break;
            case "unmark":
                duchessMsg = this.unmark(in);
                break;
            case "todo":
                duchessMsg = this.addTodo(in);
                break;
            case "deadline":
                duchessMsg = this.addDeadline(in);
                break;
            case "event":
                duchessMsg = this.addEvent(in);
                break;
            case "delete":
                duchessMsg = this.deleteTask(in);
                break;
            case "find":
                duchessMsg = this.find(in);
                break;
            default:
                this.processUnrecognisedCommand(in);
                break;
            }
            this.storage.saveList(this.taskList);
        } catch (DuchessException e) {
            duchessMsg = e.getMessage();
        } catch (Exception e) {
            duchessMsg = "Exception caught: " + e.getMessage();
        } finally {
            // response message should not be empty
            assert !duchessMsg.equals("")
                    : "Duchess.java: processInput() -"
                    + "Assertion failed: duchessMsg should not be empty here";
            DialogBox duchessDialogBox = new DialogBox(duchessMsg, duchessImage, false);
            chatBox.getChildren().addAll(duchessDialogBox);
        }
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        BorderPane root = new BorderPane();
        chatBox = new VBox(10);
        chatBox.setPadding(new Insets(10));
        chatBox.setStyle("-fx-background-color: #DFFFE1;");

        ScrollPane scrollPane = new ScrollPane(chatBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        chatBox.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        HBox inputBox = new HBox(10);
        TextField inputField = new TextField();
        inputField.setPrefWidth(300);

        Button sendButton = new Button("Send");
        sendButton.setStyle("-fx-background-color: #00C853; -fx-text-fill: white;");

        sendButton.setOnAction(e -> {
            processInput(inputField.getText());
            inputField.clear();
        });

        inputField.setOnAction(e -> {
            processInput(inputField.getText());
            inputField.clear();
        });

        inputBox.getChildren().addAll(inputField, sendButton);
        inputBox.setPadding(new Insets(10));
        inputBox.setAlignment(Pos.CENTER);

        root.setCenter(scrollPane);
        root.setBottom(inputBox);

        Scene scene = new Scene(root, 400, 600);
        primaryStage.setTitle("Duchess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
