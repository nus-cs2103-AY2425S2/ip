package chitti;

public class UnknownMessageException extends Exception{
    public UnknownMessageException(){
        super();
    }
    @Override
    public String toString(){
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
