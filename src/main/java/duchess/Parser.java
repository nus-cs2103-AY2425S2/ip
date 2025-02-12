package duchess;

/**
 * The {@code Parser} class is responsible for processing user input
 * and breaking it down into components for further interpretation.
 */
public class Parser {
    public Parser() {
    }

    /**
     * Parses a command string by splitting it into an array of words based on spaces.
     *
     * @param s The command string to be parsed.
     * @return An array of words extracted from the input string.
     * @throws Exception If an unexpected error occurs during parsing.
     */
    public String[] parseCommand(String s) throws Exception {
        try {
            return s.split(" ");
        } catch (Exception e) {
            throw e;
        }
    }
}
