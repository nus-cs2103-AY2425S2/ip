package kaji;

public class Tag {
    private String tagName;

    public Tag(String tagName) {
        if (tagName.startsWith("#")) {
            this.tagName = tagName;
        } else {
            this.tagName = "#" + tagName;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tag tag = (Tag) o;
        return tagName.equals(tag.tagName);
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    @Override
    public String toString() {
        return tagName;
    }
}