package Aquadem;

/**
 * A custom exception class to be used for the chatbot
 */
public class CustomException extends Exception{
    private String msg;

    /**
     * Constructor which takes in a String to be used in the class
     * @param msg of type <code>String</code>
     */
    public CustomException(String msg){
        super(msg);
        this.msg = msg;
    }

    /**
     * Overrides the exception class's <code>toString</code> method
     * @return a <code>String</code>
     */
    @Override
    public String getMessage() {

        return "Ohno somethings not right! : " + msg + "\n"+ Ui.getBar();
    }
}
