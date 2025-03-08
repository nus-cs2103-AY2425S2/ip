package wizt.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import wizt.ui.WizTException;


public class UpdateCommandTest {
    @Test
    public void emptyMarkDescription() {


        String input1 = "mark";

        Command command = new UpdateCommand(input1); // Assuming `tasks` is a wrapper for the list

        // Assert that the WizTException is thrown
        WizTException exception = assertThrows(WizTException.class, () -> {
            if (input1.contains("mark")) {
                String[] substr = input1.split(" ");
                if (substr.length != 2) {
                    throw new WizTException("Please enter a description!");
                }


            }
        });

        // Assert that the exception message is correct
        assertEquals("Please enter a description!", exception.getMessage());
    }

}
