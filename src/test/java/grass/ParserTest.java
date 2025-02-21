package grass;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void listEmptyTest(){
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);
        try {
            parser.parseList();
        } catch (GrassException e) {
            assertEquals("Task list is empty.", e.getMessage());
        }
    }

    @Test
    public void listTest(){
        TaskList tasks = new TaskList();
        tasks.addTask(new Task("test"));
        Parser parser = new Parser(tasks);
        try {
            assertEquals("1. [ ] test\n", parser.parseList());
        } catch (GrassException e) {
            assertEquals("Task list is empty.", e.getMessage());
        }
    }
}