package marshmallow.storage.textfilestorage;

/**
 * Interface for items that can be saved to a text file.
 */
public interface TextFileStorageSaveable {
    /**
     * Converts the item to a string that can be saved to a text file.
     *
     * Must not include newlines.
     *
     * @return String representation of the item for saving to a text file.
     */
    public String toSaveString();
}
