package hamiltonianguy.ui;

import hamiltonianguy.HamiltonianGuy;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


public class Main extends Application {

    private VBox chatContainer;
    private TextField userInput;
    private HamiltonianGuy chatbot;
    private ScrollPane scrollPane;

    @Override
    public void start(Stage stage) {
        chatbot = new HamiltonianGuy();

        chatContainer = new VBox(10);
        chatContainer.setPadding(new Insets(10));
        chatContainer.setStyle("-fx-background-color: #f5f5f5;");

        ScrollPane scrollPane = new ScrollPane(chatContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        userInput = new TextField();
        userInput.setPromptText("Type a message...");
        Button sendButton = new Button("Send");
        sendButton.setOnAction(event -> handleUserInput());

        HBox inputArea = new HBox(10, userInput, sendButton);
        inputArea.setPadding(new Insets(10));
        inputArea.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10, scrollPane, inputArea);
        Scene scene = new Scene(layout, 500, 500);
        stage.setTitle("HamiltonianGuy Chatbot");
        stage.setScene(scene);
        stage.show();

        showWelcomeMessage();
    }

    /**
     * Handles user input and updates the chat display.
     */
    private void handleUserInput() {
        String input = userInput.getText().trim();

        if (!input.isEmpty()) {
            HBox userMessage = addMessage(input, true);  // User message (right-aligned)
            chatContainer.getChildren().add(userMessage);

            String response = chatbot.getResponse(input);
            HBox botMessage = addMessage(response, false); // Bot message (left-aligned)
            chatContainer.getChildren().add(botMessage);

            Platform.runLater(() -> {
                scrollPane.setVvalue(1.0);  // Forces scroll to bottom
            });

            userInput.clear();
        }
    }



    /**
     * Adds a message to the chat display, aligning based on sender.
     *
     * @param text The message text.
     * @param isUser If true, aligns right (user message). Otherwise, aligns left (bot response).
     */
    private HBox addMessage(String text, boolean isUser) {
        Label messageLabel = new Label(text);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(250);
        TextFlow textFlow = new TextFlow(messageLabel);
        textFlow.setPadding(new Insets(10));
        textFlow.setMaxWidth(300);


        if (isUser) {
            textFlow.setStyle("-fx-background-color: #D1E8FF; -fx-background-radius: 10px;");
        } else {
            textFlow.setStyle("-fx-background-color: #E6E6E6; -fx-background-radius: 10px;");
        }

        ImageView avatar = new ImageView(new Image(
                isUser ? "/images/logo2.png" : "/images/logo.jpg"
        ));
        avatar.setFitHeight(50);
        avatar.setFitWidth(50);

        HBox messageContainer = new HBox(10);
        if (isUser) {
            messageContainer.getChildren().addAll(textFlow, avatar); // User on right
            messageContainer.setAlignment(Pos.CENTER_RIGHT);
        } else {
            messageContainer.getChildren().addAll(avatar, textFlow); // Bot on left
            messageContainer.setAlignment(Pos.CENTER_LEFT);
        }

        return messageContainer;
    }


    /**
     * Displays a welcome message with a logo.
     */
    private void showWelcomeMessage() {
        Image logo = new Image(getClass().getResourceAsStream("/images/logo.jpg"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(80);
        logoView.setFitHeight(80);

        Label welcomeLabel = new Label("Hello! I'm HamiltonianGuy.\nHow can I assist you today?");
        welcomeLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        VBox welcomeBox = new VBox(10, logoView, welcomeLabel);
        welcomeBox.setAlignment(Pos.CENTER);
        welcomeBox.setPadding(new Insets(10));

        chatContainer.getChildren().add(welcomeBox);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
