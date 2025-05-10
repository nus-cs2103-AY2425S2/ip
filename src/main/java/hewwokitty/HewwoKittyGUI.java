package hewwokitty;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import hewwokitty.parser.Parser;
import hewwokitty.storage.Storage;
import hewwokitty.task.TaskList;
import hewwokitty.ui.Ui;

/**
 * JavaFX GUI for HewwoKitty chatbot.
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class HewwoKittyGUI extends Application {
    private TaskList taskList;
    private Parser parser;
    private VBox chatArea;
    private TextField userInput;
    private ByteArrayOutputStream outputStream;

    /**
     * Function for the initial creation of the GUI
     * @param primaryStage A Stage
     */
    @Override
    public void start(Stage primaryStage) {
        taskList = Storage.readFromFile();
        parser = new Parser();

        primaryStage.setTitle("HewwoKitty Chatbot");

        chatArea = new VBox(5);
        chatArea.setPadding(new Insets(10));
        chatArea.setStyle("-fx-background-color: #ffd1dc;");

        ScrollPane scrollPane = new ScrollPane(chatArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefHeight(javafx.stage.Screen.getPrimary().getBounds().getHeight()); // Fits screen height

        userInput = new TextField();
        userInput.setPromptText("Type your message...");

        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> handleUserInput());

        VBox layout = new VBox(10, scrollPane, userInput, sendButton);
        layout.setPadding(new Insets(10));
        layout.setPrefHeight(Double.MAX_VALUE);
        VBox.setVgrow(scrollPane, javafx.scene.layout.Priority.ALWAYS);

        primaryStage.setScene(new Scene(layout, 500, 700));
        primaryStage.setMaximized(true);
        primaryStage.show();

        captureSystemOutput();
        Ui.printStart();
        appendCapturedOutput();
    }

    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (!input.isEmpty()) {
            appendUserMessage(input);

            boolean isRunning = parser.processUserCommands(input, taskList);
            appendCapturedOutput();
            userInput.clear();

            if (!isRunning) {
                Storage.writeToFile(taskList);
                appendBotMessage("hewwo kitty is cwosing uwu~");
                System.exit(0);
            }
        }
    }

    private void appendUserMessage(String message) {
        Text text = new Text(message);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: #d1e7ff; -fx-padding: 8px; -fx-background-radius: 10px;");
        textFlow.setMaxWidth(300);
        VBox wrapper = new VBox(textFlow);
        wrapper.setAlignment(Pos.CENTER_RIGHT);
        chatArea.getChildren().add(wrapper);
    }

    private void appendBotMessage(String message) {
        Text text = new Text(message);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color: #ffffff; -fx-padding: 8px; -fx-background-radius: 10px;");
        textFlow.setMaxWidth(600);
        VBox wrapper = new VBox(textFlow);
        wrapper.setAlignment(Pos.CENTER_LEFT);
        chatArea.getChildren().add(wrapper);
    }

    private void captureSystemOutput() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    private void appendCapturedOutput() {
        String output = outputStream.toString().trim();
        if (!output.isEmpty()) {
            appendBotMessage(output);
            outputStream.reset();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
