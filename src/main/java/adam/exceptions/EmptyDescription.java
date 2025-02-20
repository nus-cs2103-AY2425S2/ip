package adam.exceptions;

/**
 * Represents an exception where the description is empty.
 */
public class EmptyDescription extends AdamException {
    public EmptyDescription() {
        super("Description is empty!");
    }
}
