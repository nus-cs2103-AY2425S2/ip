package softess;

public class SoftessException extends Exception {
    public SoftessException(String message) {
        super(message);
    }

    public static class InvalidCommandException extends SoftessException {
        public InvalidCommandException() {
            super("Oh No! I do not recognise that command, please use a valid command instead!");
        }
    }

    public static class InvalidTodoException extends SoftessException {
        public InvalidTodoException() {
            super("The softess.ToDo command is missing required arguments. Please give a valid description");
        }
    }

}