package helix.stub;

import java.util.ArrayList;
import java.util.List;

import helix.storage.Storage;
import helix.task.Task;

/**
 * Implements a stub version of the {@code Storage} class for testing.
 *
 * <p>
 * This class overrides methods in {@link Storage} to provide a simplified, in-memory
 * implementation without file I/O. It ensures that tests can run without dependencies
 * on the file system, thereby reducing external dependencies and increasing test reliability.
 * </p>
 */
public class StubStorage extends Storage {

    /**
     * Constructs a {@code StubStorage} instance.
     * <p>
     * The superclass constructor is invoked with an empty file path since this stub
     * does not interact with the file system.
     * </p>
     */
    public StubStorage() {
        super(""); // No file path required
    }

    /**
     * Returns an empty list of tasks to simulate loading from storage.
     * <p>
     * This method is overridden to ensure that no tasks are loaded from any external source,
     * allowing for controlled test conditions.
     * </p>
     *
     * @return an empty {@link List} of {@link Task} objects
     */
    @Override
    public List<Task> load() {
        return new ArrayList<>(); // Return an empty task list
    }

    /**
     * Simulates saving tasks without performing any actual operations.
     * <p>
     * This method is overridden to prevent file I/O operations during tests. It does nothing
     * with the provided list of tasks, ensuring test isolation.
     * </p>
     *
     * @param tasks the {@link List} of {@link Task} objects to "save" (ignored in this stub implementation)
     */
    @Override
    public void save(List<Task> tasks) {
        // Do nothing, as this is a stub
    }
}
