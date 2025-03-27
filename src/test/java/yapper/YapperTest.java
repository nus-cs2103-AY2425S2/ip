package yapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import yapper.ui.UI;


class YapperTest {
    private Yapper yapper;

    @BeforeEach
    void setUp() {
        try (FileWriter writer = new FileWriter("../testData/YapperTasks.txt", false)) {
            // Writing nothing to the file clears it
            System.out.println("File reset successfully.");
        } catch (IOException e) {
            System.err.println("Error resetting file: " + e.getMessage());
        }
        yapper = new Yapper("../testData/YapperTasks.txt");
    }

    @Test
    void testGetIntroduction() {
        UI ui = new UI();
        String expectedIntroduction = ui.getIntroduction();
        assertEquals(expectedIntroduction, yapper.getIntroduction(), "Introduction message should match UI class output.");
    }

    @Test
    void testGetResponse_listCommand() {
        String response = yapper.getResponse("list");
        String expectedResponse = "\tHere are the tasks in your list:";
        assertEquals(expectedResponse, response, "List command should return a response with no items .");
    }

    @Test
    void testGetResponse_addToDo() {
        String response = yapper.getResponse("todo Read a book");
        String expectedResponse = "Got it. I've added this task:\n\t[T][ ] Read a book"
                + "\nNow you have 1 tasks in the list.";
        assertEquals(expectedResponse, response, "ToDo task should be added correctly.");
    }

    @Test
    void testGetResponse_markTask() {
        yapper.getResponse("todo Read a book"); // Add task
        String response = yapper.getResponse("mark 1");
        String expectedResponse = "Nice! I've marked this task as done:\n\t[T][X] Read a book";
        assertEquals(expectedResponse, response, "Task should be marked as completed.");
    }

    @Test
    void testGetResponse_unmarkTask() {
        yapper.getResponse("todo Read a book"); // Add task
        yapper.getResponse("mark 1"); // Mark task
        String response = yapper.getResponse("unmark 1");
        String expectedResponse = "OK, I've marked this task as not done yet:\n\t[T][ ] Read a book";
        assertEquals(expectedResponse, response, "Task should be unmarked correctly.");
    }

    @Test
    void testGetResponse_deleteTask() {
        yapper.getResponse("todo Read a book"); // Add task
        String response = yapper.getResponse("delete 1");
        String expectedResponse = "Noted. I've removed this task:\n\t[T][ ] Read a book"
                + "\nNow you have 0 tasks in the list.";
        assertEquals(expectedResponse, response, "Task should be deleted correctly.");
    }

    @Test
    void testGetResponse_invalidCommand() {
        String response = yapper.getResponse("invalidCommand");
        String expectedResponse = "Come on we've been through this. I only understand these 3 commands: 'todo', "
                + "'deadline', 'event'.\nPlease give it in this format {Command taskname}";
        assertEquals(expectedResponse, response, "Invalid command should return the expected error message.");
    }

    @Test
    void testGetResponse_byeCommand() {
        String response = yapper.getResponse("bye");
        UI ui = new UI();
        assertEquals(ui.getExit(), response, "Exit message should match UI class output.");
    }
}
