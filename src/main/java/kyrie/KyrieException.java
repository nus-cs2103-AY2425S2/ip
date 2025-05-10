package kyrie;

/**
 * Represents an exception in Kyrie.
 */
public class KyrieException extends Exception {
    public static String errorMessage = "There seems to be something wrong: \n";
    
    /**
     * Constructor for KyrieException.
     * 
     * @param message The error message.
     */
    public KyrieException(String message) {
        super(errorMessage + message);
    }
}