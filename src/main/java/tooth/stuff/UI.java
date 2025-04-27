package tooth.stuff;

/**
 * UI of the App
 */
public class UI {
    private String response = "";

    public UI() {}

    /**
     * Close program
     */
    public void bye() {
        System.out.println("Ok Bye");
    }
    /**
     * Used to tell user sucess status
     *
     * @param sentence the string to reply user
     */
    public void say(String sentence) {
        response = "TOOTH SAYS:\n" + sentence;
    }
    /**
     * Used to tell user failure status
     *
     * @param sentence the string to reply user
     */
    public void complain(String sentence) {
        response = "TOOTH COMPLAINS:\n" + sentence;
    }
    public void appendLn(String newSentence) {
        response = response + "\n" + newSentence;
    }

    public String getResponse() {
        return this.response;
    }
}
