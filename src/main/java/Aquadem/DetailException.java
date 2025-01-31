package Aquadem;

/**
 * An exception which is a subclass of CustomException
 * It is thrown when parsing through the user input
 */
public class DetailException extends Aquadem.CustomException {

    public DetailException(String msg) {
        super(msg);
    }

}

