package carolyn;

//reference to: https://www.geeksforgeeks.org/user-defined-custom-exception-in-java/
public class CarolynException extends Exception {
    public CarolynException(String s) {
        super(s);
    }
}