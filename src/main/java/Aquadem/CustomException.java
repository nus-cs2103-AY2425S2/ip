package Aquadem;

public class CustomException extends Exception{
    private String msg;
    public CustomException(String msg){
        super(msg);
        this.msg = msg;
    }

    @Override
    public String getMessage() {

        return "Ohno somethings not right! : " + msg + "\n"+ Ui.getBar();
    }
}
