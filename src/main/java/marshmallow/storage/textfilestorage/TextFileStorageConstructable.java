package marshmallow.storage.textfilestorage;

/**
 * Interface for items that can be constructed from a text file.
 */
public interface TextFileStorageConstructable<T> {
    /**
     * Constructs an item from a save string.
     *
     * @param data Save string to construct the item from.
     * @return Item constructed from save string.
     */
    public T constructFromSaveString(String data);
}
