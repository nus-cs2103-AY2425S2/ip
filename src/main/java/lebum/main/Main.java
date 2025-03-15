package lebum.main;
import lebum.command.Command;
import lebum.exception.DukeException;
import javafx.animation.PauseTransition;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX main class that user interacts with
 */
public class Main extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage;
    private Image lebumImage;
    private Parser parser;
    private Lebum lebum;
    private Ui ui;

    /**
     * Distinct colors for user and bot messages
     */
    private static final String MAIN_BACKGROUND_COLOR = "#F0F2F5";
    private static final String USER_BUBBLE_COLOR = "#DCF8C6";
    private static final String BOT_BUBBLE_COLOR = "#FFFFFF";
    private static final String BUTTON_COLOR = "#0084FF";

    @Override
    public void start(Stage stage) {
        lebum = new Lebum("data/tasks.txt");
        parser = new Parser();
        ui = new Ui();

        try {
            userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
            lebumImage = new Image(this.getClass().getResourceAsStream("/images/lebum.jpeg"));
        } catch (Exception e) {
            System.out.println("Error uploading image");
        }


        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.setPadding(new Insets(15));
        dialogContainer.setSpacing(15);
        dialogContainer.setStyle("-fx-background-color: " + MAIN_BACKGROUND_COLOR + ";");

        scrollPane.setContent(dialogContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background: " + MAIN_BACKGROUND_COLOR + ";"
                + "-fx-background-color: " + MAIN_BACKGROUND_COLOR + ";"
                + "-fx-border-color: transparent;");

        userInput = new TextField();
        userInput.setStyle("-fx-background-radius: 20;"
                + "-fx-border-radius: 20;"
                + "-fx-padding: 8 15;"
                + "-fx-background-color: white;"
                + "-fx-border-color: #E4E4E4;");

        sendButton = new Button("Send");
        sendButton.setStyle("-fx-background-color: " + BUTTON_COLOR + ";"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 20;"
                + "-fx-border-radius: 20;"
                + "-fx-padding: 8 15;"
                + "-fx-cursor: hand;");

        sendButton.setOnMouseEntered(e ->
                sendButton.setStyle("-fx-background-color: #006ACC;"
                        + "-fx-text-fill: white;"
                        + "-fx-background-radius: 20;"
                        + "-fx-border-radius: 20;"
                        + "-fx-padding: 8 15;"
                        + "-fx-cursor: hand;"));

        sendButton.setOnMouseExited(e ->
                sendButton.setStyle("-fx-background-color: " + BUTTON_COLOR + ";" + "-fx-text-fill: white;"
                        + "-fx-background-radius: 20;"
                        + "-fx-border-radius: 20;"
                        + "-fx-padding: 8 15;"
                        + "-fx-cursor: hand;"));

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setStyle("-fx-background-color: " + MAIN_BACKGROUND_COLOR + ";");
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // Layout constraints
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 60.0);

        AnchorPane.setLeftAnchor(userInput, 15.0);
        AnchorPane.setRightAnchor(userInput, 85.0);
        AnchorPane.setBottomAnchor(userInput, 15.0);

        AnchorPane.setRightAnchor(sendButton, 15.0);
        AnchorPane.setBottomAnchor(sendButton, 15.0);

        scene = new Scene(mainLayout, 400, 600);
        stage.setTitle("Lebum Chatbot");
        stage.setScene(scene);

        // Event handlers
        sendButton.setOnAction((event) -> handleInput());
        userInput.setOnAction((event) -> handleInput());

        showMessage(ui.welcome(), false);
        stage.show();
    }

    private void handleInput() {
        String input = userInput.getText().trim();
        if (!input.isEmpty()) {
            showMessage("You: \n" + input, true);
        }
        try {
            String response = lebum.executeCommand(input);
            if (!response.equals("Oops")) {
                showMessage("Lebum: \n" + response, false);
                // Check if the command is an exit command using pattern
                Command cmd = parser.parse(input);
                if (cmd.isExit()) {
                    // Add a short delay to show goodbye message
                    PauseTransition delay = new PauseTransition(Duration.seconds(1));
                    delay.setOnFinished(e -> Platform.exit());
                    delay.play();
                }
            }
            else {
                showMessage("Error, please fix your command!", false);
            }
        } catch (DukeException e) {
            showMessage("Something went wrong, fix your command!\n" + e.getMessage(), false);
        }
        userInput.clear();
    }

    private void showMessage(String message, boolean isUser) {
        HBox messageBox = new HBox(10);
        messageBox.setPadding(new Insets(5));

        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(250);
        messageLabel.setPadding(new Insets(10));

        // Better styling for chat interface
        String bubbleStyle = String.format(
                "-fx-background-color: %s;"
                        + "-fx-background-radius: 15;"
                        + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 1, 1, 0, 1);",
                isUser ? USER_BUBBLE_COLOR : BOT_BUBBLE_COLOR
        );
        messageLabel.setStyle(bubbleStyle);

        ImageView imageView = new ImageView(isUser ? userImage : lebumImage);
        // icon size
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        imageView.setPreserveRatio(true);

        // clip size
        javafx.scene.shape.Circle clip = new javafx.scene.shape.Circle(20);
        clip.setCenterX(20);
        clip.setCenterY(20);
        imageView.setClip(clip);

        if (isUser) {
            messageBox.setAlignment(Pos.CENTER_RIGHT);
            messageBox.getChildren().addAll(messageLabel, imageView);
        } else {
            messageBox.setAlignment(Pos.CENTER_LEFT);
            messageBox.getChildren().addAll(imageView, messageLabel);
        }

        dialogContainer.getChildren().add(messageBox);
        scrollPane.setVvalue(1.0);
    }
}