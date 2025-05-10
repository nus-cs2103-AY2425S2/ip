package tasker.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Todo class.
 */
class TodoTest {
    private String todoType = TaskType.T.toString();
    private String description = "Complete assignment";

    @Nested
    @DisplayName("toStorage Tests")
    class ToStorageTests {
        @Test
        @DisplayName("Correct toStorage unmarked return")
        void toStorage_unmarked_expectedFormat() {
            Todo todoUnmarked = new Todo(description);
            String expectedUnmarkedStorage = todoType + "|false|" + description;
            assertEquals(expectedUnmarkedStorage, todoUnmarked.toStorage());
            Todo todoMarked = new Todo(description, true);
            String expectedMarkedStorage = todoType + "|true|" + description;
            assertEquals(expectedMarkedStorage, todoMarked.toStorage());
        }

        @Test
        @DisplayName("Correct toStorage marked return")
        void toStorage_marked_expectedFormat() {
            Todo todoUnmarked = new Todo(description);
            String expectedUnmarkedStorage = todoType + "|false|" + description;
            assertEquals(expectedUnmarkedStorage, todoUnmarked.toStorage());
            Todo todoMarked = new Todo(description, true);
            String expectedMarkedStorage = todoType + "|true|" + description;
            assertEquals(expectedMarkedStorage, todoMarked.toStorage());
        }
    }

    @Nested
    @DisplayName("toString Tests")
    class ToStringTests {
        @Test
        @DisplayName("Correct toString unmarked return")
        void toString_unmarked_expectedFormat() {
            Todo todo = new Todo(description);
            String expectedString = String.format("[%s][ ] %s", todoType, description);
            assertEquals(expectedString, todo.toString());
        }

        @Test
        @DisplayName("Correct toString marked return")
        void toString_marked_expectedFormat() {
            Todo todo = new Todo(description, true);
            String expectedString = String.format("[%s][X] %s", todoType, description);
            assertEquals(expectedString, todo.toString());
        }
    }
}
