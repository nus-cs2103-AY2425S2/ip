package aquadem;

/**
 * A custom exception class to be used for the chatbot.
 */
public class CustomException extends Exception{
    private String msg;

    /**
     * Constructs a custom exception with message msg.
     * @param msg of type String.
     */
    public CustomException(String msg){
        super(msg);
        this.msg = msg;
    }

    /**
     * Overrides the exception class's <code>toString</code> method.
     * @return a <code>String</code>.
     */
    @Override
    public String getMessage() {

        return "Ohno somethings not right! : " + msg + "\n";
    }
}
