package doot;

//class for wrapping the nice lines around text to be printed
public class  Ui {

    //wraps text with underscores then print
    //@param message the message to be printed
    public static void showMessage(String message) {
        System.out.println("________________________________________________________________________________________________________________________\n" +
                message +
                "\n________________________________________________________________________________________________________________________\n");        }
}