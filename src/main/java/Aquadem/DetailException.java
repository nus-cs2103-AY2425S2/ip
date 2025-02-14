package Aquadem;

/**
 * An exception which is a subclass of CustomException
 * It is thrown when parsing through the user input.
 */
public class DetailException extends Aquadem.CustomException {

    /**
     * Constrcuts a DetailException
     * @param msg
     */
    public DetailException(String msg) {
        super(msg);
    }

}

