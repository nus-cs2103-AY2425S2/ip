package marshmallow.storage;

import java.util.List;

/**
 * Storage driver for saving and loading data.
 */
public interface Storage<T> {
    /**
     * Loads data from storage.
     *
     * @return List of items loaded from storage.
     */
    public List<T> load();

    /**
     * Saves data to storage.
     *
     * @param items List of items to save.
     */
    public void save(List<T> items);
}
