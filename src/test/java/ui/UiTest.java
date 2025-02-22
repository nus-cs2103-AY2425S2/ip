package ui;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class UiTest {
    @Test
    void showWelcomeTest() {
        Ui ui = new Ui();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ui.showWelcome();

        String expectedOutput =
                "____________________________________________________________\n" +
                        "    Hello! I'm Samantha\n" +
                        "    What can I do for you?\n" +
                        "____________________________________________________________\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void showGoodbyeTest() {
        Ui ui = new Ui();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ui.showGoodbye();

        String expectedOutput =
                "____________________________________________________________\n" +
                        "    Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n";

        assertEquals(expectedOutput, outputStream.toString());
    }


}
