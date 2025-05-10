package kyrie;

/**
 * Represents a tag.
 */
public class Tag {
    private String tagName;

    /**
     * Constructor for Tag.
     * 
     * @param tagName The name of the tag.
     */
    public Tag(String tagName) {
        if (tagName.startsWith("#")) {
            tagName = tagName.substring(1);
        }
        this.tagName = tagName;
    }

    /**
     * Returns the name of the tag.
     * 
     * @return The name of the tag.
     */
    public String getTagName() {
        return this.tagName;
    }

    /**
     * Returns the string representation of the tag.
     * 
     * @return The string representation of the tag.
     */
    @Override
    public String toString() {
        return "#" + this.tagName;
    }
}
