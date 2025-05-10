package helix.enums;

/**
 * Defines file and folder paths used in the Helix application.
 */
public enum FilePath {
    STORAGE_FILE("data/helix_tasklist.txt");

    private final String path;

    FilePath(String path) {
        this.path = path;
    }

    /**
     * Retrieves the file or folder path.
     *
     * @return the file or folder path as a String
     */
    public String getPath() {
        return path;
    }
}
