package steve.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main JavaFX application class.
 * This class sets up and displays a simple JavaFX window with a chatbot interface.
 */
public class Main extends Application {
    private Steve steve = new Steve();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image steveImage = new Image(this.getClass().getResourceAsStream("/images/Steve.png"));
    private Image backgroundImage = new Image(this.getClass().getResourceAsStream("/images/background.png"));

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String steveText = steve.getUi().getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getSteveDialog(steveText, steveImage)
        );
        userInput.clear();
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        // Handling user input
        sendButton.setOnMouseClicked(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());

        AnchorPane mainLayout = new AnchorPane();

        // Set background image
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        BackgroundSize.AUTO,
                        BackgroundSize.AUTO,
                        false,
                        false,
                        true,
                        true
                )
        );
        mainLayout.setBackground(new Background(background));

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout, 400, 600);

        // Display greeting message at the start
        String greeting = Messages.greetingToString();
        dialogContainer.getChildren().add(DialogBox.getSteveDialog(greeting, steveImage));

        // Allow resizing
        stage.setTitle("Steve");
        stage.setResizable(true);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        // Set component properties
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // Make scrollPane background transparent to show main background
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        dialogContainer.setStyle("-fx-background-color: transparent;");

        // Bind UI elements to window size
        scrollPane.prefHeightProperty().bind(mainLayout.heightProperty().subtract(50));
        scrollPane.prefWidthProperty().bind(mainLayout.widthProperty());

        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty().subtract(20));

        userInput.prefWidthProperty().bind(mainLayout.widthProperty()
                .subtract(sendButton.widthProperty()).subtract(10));
        sendButton.prefWidthProperty().bind(mainLayout.widthProperty().multiply(0.15));

        // Set layout constraints
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setLeftAnchor(scrollPane, 1.0);
        AnchorPane.setRightAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(scrollPane, 50.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);

        // Auto-scroll when new messages appear
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Styling
        userInput.setStyle("-fx-font-size: 14px;");
        sendButton.setStyle("-fx-font-size: 14px;");

        dialogContainer.setPadding(new Insets(10, 10, 10, 10));
        dialogContainer.setSpacing(10);

        stage.setScene(scene);
        stage.show();
    }
}
