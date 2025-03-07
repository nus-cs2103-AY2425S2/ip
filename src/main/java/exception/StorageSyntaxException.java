package exception;

/**
 * Exception for handling invalid storage format
 */
public class StorageSyntaxException extends InvalidInputException{
    public StorageSyntaxException() {
        super("Apologies Commander, there appear to be an issue with the storage file");
    }
}
