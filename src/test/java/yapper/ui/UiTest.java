package yapper.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UiTest {
    private UI ui;

    @BeforeEach
    void setUp() {
        // Create the UI object with a placeholder name
        ui = new UI();
    }

    @Test
    void testGetIntroduction() {
        String actualOutput = ui.getIntroduction();
        String expectedOutput = "Hello! I'm Yapper. What can I do for you?";

        assertEquals(expectedOutput, actualOutput, "Introduction message should match expected output.");
    }

    @Test
    void testGetExit() {
        String actualOutput = ui.getExit();
        String expectedOutput = "Bye. Hope to see you again soon!";

        assertEquals(expectedOutput, actualOutput, "Exit message should match expected output.");
    }
}
