package tasker;

import java.util.Scanner;

class Ui {
    /** Scanner for input */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Formats and prints an output.
     *
     * @param content The content of this response.
     */
    static void respond(String content) {
        String padding = "    ";
        String separator = padding + "____________________________________________________________\n";
        String contentPadding = padding + " ";
        StringBuilder response = new StringBuilder(separator);

        for (String line : content.split("\n")) {
            String linePadding = contentPadding + line.replaceFirst("\\S.*", "");
            StringBuilder wrappedLine = new StringBuilder(linePadding);

            for (String word : line.split(" ")) {
                int currLineLength = wrappedLine.length();
                boolean isFirstWord = currLineLength == linePadding.length();

                if ((currLineLength + 1 + word.length() <= separator.length() - 1) || isFirstWord) {
                    if (!isFirstWord) {
                        wrappedLine.append(" ");
                    }

                    wrappedLine.append(word);
                } else {
                    response.append(wrappedLine.append("\n"));
                    wrappedLine = new StringBuilder(linePadding).append(word);
                }
            }

            response.append(wrappedLine.append("\n"));
        }

        response.append(separator);
        System.out.println(response);
    }

    /**
     * Takes an input from the user.
     *
     * @return The input of the user.
     */
    static String input() {
        String input = Ui.scanner.nextLine();
        return input;
    }

    /**
     * Closes the scanner.
     */
    static void close() {
        Ui.scanner.close();
    }
}
