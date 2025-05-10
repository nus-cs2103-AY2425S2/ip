package kyrie;
import java.util.ArrayList;

/**
 * Represents a list of tags.
 */
public class TagList {
    private ArrayList<Tag> tags;

    /**
     * Constructor for TagList.
     */
    public TagList() {
        this.tags = new ArrayList<Tag>();
    }

    /**
     * Adds a tag to the tag list.
     *
     * @param tag The tag to be added.
     */
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    /**
     * Deletes a tag from the tag list.
     *
     * @param index The index of the tag to be deleted, 1-indexed.
     * @return The deleted tag.
     */
    public Tag deleteTag(int index) {
        Tag tag = this.tags.get(index - 1);
        this.tags.remove(index - 1);
        return tag;
    }

    /**
     * Retrieves a tag from the tag list by index.
     *
     * @param index The index of the tag to be retrieved, 1-indexed.
     * @return The tag at the specified index.
     */
    public Integer getSize() {
        return this.tags.size();
    }

    /**
     * Retrieves a tag from the tag list by index.
     *
     * @param index The index of the tag to be retrieved, 1-indexed.
     * @return The tag at the specified index.
     */
    @Override
    public String toString() {
        StringBuilder tagsString = new StringBuilder();
        for (int i = 0; i < this.tags.size(); i++) {
            tagsString.append(this.tags.get(i).toString());
            if (i != this.tags.size() - 1) {
                tagsString.append(" ");
            }
        }
        return tagsString.toString();
    }
    
}
