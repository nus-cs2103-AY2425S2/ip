package controller;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Alice;
import model.exception.AliceExit;
import model.response.Response;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends VBox {

    @FXML
    private StackPane leftSidebar;
    @FXML
    private ScrollPane chatScrollPane;
    @FXML
    private VBox chatVBox;
    @FXML
    private TextField userInput;
    @FXML
    private StackPane rightSidebar;

    private Alice alice;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final FormSidebar formSidebar = new FormSidebar(this);
    private final AliceSidebar aliceSidebar = new AliceSidebar();

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        chatScrollPane.vvalueProperty().bind(chatVBox.heightProperty());
        chatScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chatVBox.prefWidthProperty().bind(chatScrollPane.widthProperty());

        leftSidebar.getChildren().add(formSidebar);
        StackPane.setAlignment(formSidebar, Pos.TOP_CENTER);
    }

    /**
     * Injects the Alice instance.
     *
     * @param alice the Alice instance
     */
    public void setAlice(Alice alice) {
        this.alice = alice;
        alice.initialize();

        aliceSidebar.setAlice(alice);
        rightSidebar.getChildren().add(aliceSidebar);
        StackPane.setAlignment(aliceSidebar, Pos.TOP_CENTER);

        startListeningToAlice();
    }

    /**
     * Starts listening to Alice's responses.
     */
    private void startListeningToAlice() {
        executorService.submit(() -> {
            try {
                while (true) {
                    Response response = alice.takeResponse();
                    String imageUrl = alice.getImageUrl();
                    javafx.application.Platform.runLater(() -> {
                        AliceDialogBox[] aliceDialogBoxes = AliceDialogBox.fromResponse(response, imageUrl);
                        chatVBox.getChildren().addAll(aliceDialogBoxes);
                    });
                    aliceSidebar.updateAliceImage();
                    aliceSidebar.setAngerLabel();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    /**
     * Controller handler for user input.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        //Currently, this stackpane is needed to align user dialog box to the right.
        StackPane stackPane = new StackPane();
        UserDialogBox userDialogBox = UserDialogBox.of(input);
        stackPane.getChildren().add(userDialogBox);
        stackPane.prefWidthProperty().bind(chatVBox.widthProperty());
        StackPane.setAlignment(userDialogBox, Pos.BOTTOM_RIGHT);
        chatVBox.getChildren().add(stackPane);
        try {
            alice.run(input);
        } catch (AliceExit ex) {
            alice.quit();
            try {
                Thread.sleep(2000);
                executorService.shutdown();
            } catch (InterruptedException e) {
            } finally {
                System.exit(0);
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        userInput.clear();
    }

    /**
     * Handles user input with the given input string.
     *
     * @param input the input string
     */
    protected void handleUserInput(String input) {
        userInput.setText(input);
        handleUserInput();
    }

    /**
     * Handles key press events.
     *
     * @param keyEvent the key event
     */
    @FXML
    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            handleUserInput();
        }
    }

    @FXML
    private void onHelpMenuItemAction() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Main.class.getResource("fxml/CommandGuideModal.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Help");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("images/alice_annoyed.png")));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the Alice instance.
     *
     * @return the Alice instance
     */
    protected Alice getAlice() {
        return this.alice;
    }

}
