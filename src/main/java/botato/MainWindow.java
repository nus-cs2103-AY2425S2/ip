package botato;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Botato botato;

    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private final Image botatoImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/botato.png")));

    /**
     * Initializes the scroll pane and displays a welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Display welcome message
        dialogContainer.getChildren().addAll(
                DialogBox.getBotatoDialog(Ui.showWelcome(), botatoImage)
        );
        try (InputStream inputStream = MainWindow.class.getClassLoader().getResourceAsStream("docs/README.md")) {
            if (inputStream == null) {
                System.err.println("File not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /** Injects the Botato instance */
    public void setBotato(Botato b) {
        botato = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Botato's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = botato.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotatoDialog(response, botatoImage)
        );
        userInput.clear();
        // Quit application when 'bye' is typed (i.e. user wants to exit)
        if (Objects.equals(response, "Hope I helped! See you~")) {
            Platform.exit();
        }
        // Opens the user guide after a short delay
        if (Objects.equals(response, "Opening my guide in a new window...")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
            pause.setOnFinished(event -> {openMarkdownViewer();});
            pause.play();
        }
    }

    private void openMarkdownViewer() {
        // Use the class loader to access the resource
        InputStream inputStream = MainWindow.class.getClassLoader()
                .getResourceAsStream("docs/README.md");

        if (inputStream == null) {
            System.err.println("Markdown file not found in resources!");
            return;
        }

        String markdownContent = readMarkdownFile(inputStream);
        String htmlContent = convertMarkdownToHtml(markdownContent);

        Platform.runLater(() -> {
            WebView webView = new WebView();
            webView.getEngine().loadContent(htmlContent);

            Scene scene = new Scene(webView, 600, 600);
            Stage stage = new Stage();
            stage.setX(0);
            stage.setY(0);
            stage.setScene(scene);
            stage.setTitle("Botato Guide");
            stage.show();
        });
    }

    private String readMarkdownFile(InputStream inputStream) {
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            return scanner.useDelimiter("\\A").next();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error loading file.";
        }
    }

    private String convertMarkdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        Node document = parser.parse(markdown);
        return renderer.render(document);
    }
}
