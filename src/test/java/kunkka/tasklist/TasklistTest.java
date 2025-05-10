package kunkka.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import kunkka.components.Task;
import kunkka.components.Todo;;

public class TasklistTest {
    
    @Test
    public void testAddTask() {
        Tasklist tasklist = new Tasklist();
        tasklist.addTask(new Task("read book", "T",false));
        tasklist.addTask(new Task("hw", "T",false));
        assertEquals("[[T][ ] read book, [T][ ] hw]", tasklist.getTasks().toString());
    }

    @Test
    public void testDeleteTask() {
        Tasklist tasklist = new Tasklist();
        tasklist.addTask(new Todo("read book", false));
        tasklist.addTask(new Todo("hw", false));
        tasklist.deleteTask(1);
        assertEquals("[[T][ ] hw]",tasklist.getTasks().toString());
    } 

    @Test
    public void testDeleteInvalidIndex() {
        Tasklist tasklist = new Tasklist();
        tasklist.addTask(new Todo("read book", false));
        tasklist.addTask(new Todo("hw", false));
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        tasklist.deleteTask(3);
        assertTrue(outContent.toString().contains("Error: Invalid task number (Out of range)"));
    }

    @Test
    public void testDeleteNegativeIndex() {
        Tasklist tasklist = new Tasklist();
        tasklist.addTask(new Todo("read book", false));
        tasklist.addTask(new Todo("hw", false));
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        tasklist.deleteTask(-1);
        assertTrue(outContent.toString().contains("Error: Invalid task number (Zero or Negative)"));
    }

    @Test
    public void testMarkTaskAsDone() {
        Tasklist tasklist = new Tasklist();
        tasklist.addTask(new Todo("read book", false));
        tasklist.addTask(new Todo("hw", false));
        tasklist.markTaskAsDone(1);
        assertEquals("[[T][X] read book, [T][ ] hw]",tasklist.getTasks().toString());
    }

    @Test
    public void testMarkInvalidIndex() {
        Tasklist tasklist = new Tasklist();
        tasklist.addTask(new Todo("read book", false));
        tasklist.addTask(new Todo("hw", false));
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        tasklist.markTaskAsDone(3);
        assertTrue(outContent.toString().contains("Error: Invalid task number (Out of range)"));
    }

    @Test
    public void testMarkNegativeIndex() {
        Tasklist tasklist = new Tasklist();
        tasklist.addTask(new Todo("read book", false));
        tasklist.addTask(new Todo("hw", false));
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        tasklist.markTaskAsDone(-1);
        assertTrue(outContent.toString().contains("Error: Invalid task number (Zero or negative)"));
    }

}
