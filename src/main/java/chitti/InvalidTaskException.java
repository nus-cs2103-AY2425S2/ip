package chitti;

public class InvalidTaskException extends Exception{
    public InvalidTaskException(){
        super();
    }
    @Override
    public String toString(){
        return "OOPS!!! You have entered an invalid task :-(";
    }
}
