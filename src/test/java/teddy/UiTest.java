package teddy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class UiTest {

    private Ui ui;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream)); // Redirect System.out
    }

    @Test
    void welcome_displaysCorrectMessage() {
        ui = new Ui();
        ui.welcome();

        String expectedOutput = "_".repeat(60) + "\nHello! I'm Teddy\nWhat can I do for you?\n" + "_".repeat(60) + "\n";
        assertEquals(expectedOutput, outputStream.toString(), "Welcome message should match.");
    }

    @Test
    void goodbye_displaysCorrectMessage() {
        ui = new Ui();
        ui.goodbye();

        String expectedOutput = "_".repeat(60) + "\nBye! Hope to see you again soon!\n" + "_".repeat(60) + "\n";
        assertEquals(expectedOutput, outputStream.toString(), "Goodbye message should match.");
    }

    @Test
    void readCommand_returnsUserInput() {
        String input = "todo read book\n";
        System.setIn(new ByteArrayInputStream(input.getBytes())); // Simulate user input

        ui = new Ui();
        String result = ui.readCommand();

        assertEquals("todo read book", result, "readCommand() should return correct user input.");
    }

    @Test
    void error_displaysCorrectErrorMessage() {
        ui = new Ui();
        ui.error("Invalid command");

        String expectedOutput = "_".repeat(60) + "\nInvalid command\n" + "_".repeat(60) + "\n";
        assertEquals(expectedOutput, outputStream.toString(), "Error message should match.");
    }

    @Test
    void setSEPERATOR_displaysCorrectSeparator() {
        ui = new Ui();
        ui.setSEPERATOR();

        String expectedOutput = "_".repeat(60) + "\n";
        assertEquals(expectedOutput, outputStream.toString(), "Separator should match.");
    }
}
