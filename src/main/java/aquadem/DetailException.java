package aquadem;

/**
 * An exception which is a subclass of CustomException
 * It is thrown when parsing through the user input.
 */
public class DetailException extends CustomException {

    /**
     * Constrcuts a DetailException
     * @param msg
     */
    public DetailException(String msg) {
        super(msg);
    }

}

