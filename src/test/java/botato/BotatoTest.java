package botato;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BotatoTest {

    private Botato botato;

    @BeforeEach
    void setUp() {
        // Initialize Botato with real dependencies
        botato = new Botato();
        TaskList.initialize(0);
    }

    @Test
    void testGetResponse_AddTodo() {
        // Arrange
        String input = "todo Buy groceries";

        // Act
        String response = botato.getResponse(input);

        // Assert
        assertEquals("Todo added:\n[ ] Todo: Buy groceries", response);
    }

    @Test
    void testGetResponse_InvalidCommand() {
        // Arrange
        String input = "invalid command";

        // Act
        String response = botato.getResponse(input);

        // Assert
        assertEquals("I don't understand that, I'm just a potato...\n"
                        + "Say 'help' to see what I learnt to do so far!"
                , response);
    }

    @Test
    void testGetResponse_ListTasks() {
        // Arrange
        // Add some tasks first
        botato.getResponse("todo potatoes");
        botato.getResponse("deadline buy potatoes /by 2069-06-09 23:59");
        botato.getResponse("event potato festival /from 1969-09-06 00:00 /to 2069-09-06 23:59");
        botato.getResponse("todo Buy groceries");

        String listInput = "list";

        // Act
        String response = botato.getResponse(listInput);

        // Assert
        String expectedOutput = """
                Here are your current tasks:
                1.[ ] Todo: potatoes
                2.[ ] Deadline: buy potatoes (by: 09 Jun 2069 23:59)
                3.[ ] Event: potato festival (from 06 Sep 1969 00:00 to 06 Sep 2069 23:59)
                4.[ ] Todo: Buy groceries""";
        assertEquals(expectedOutput, response);
    }

    @Test
    void testGetResponse_MarkTaskAsDone() {
        // Arrange
        // Add a task first
        botato.getResponse("todo potatoes");

        String markInput = "mark 1";

        // Act
        String response = botato.getResponse(markInput);

        // Assert
        assertEquals("Good job! You finished this:\n[X] Todo: potatoes", response);
    }

    @Test
    void testGetResponse_UnmarkTask() {
        // Arrange
        // Add and mark a task first
        botato.getResponse("todo potatoes");
        botato.getResponse("mark 1");

        String unmarkInput = "unmark 1";

        // Act
        String response = botato.getResponse(unmarkInput);

        // Assert
        assertEquals("Aww.. Guess you didn't do this yet:\n[ ] Todo: potatoes", response);
    }

    @Test
    void testGetResponse_ListTasks_EmptyList() {
        // Arrange
        String listInput = "list";

        // Act
        String response = botato.getResponse(listInput);

        // Assert
        assertEquals("You have no tasks!", response);
    }
}