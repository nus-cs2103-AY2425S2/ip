package bob.util;

import java.util.List;

import bob.task.Task;

/**
 * This class contains static methods to format strings,
 * for both CLI and GUI.
 */
public class Formatter {

    /**
     * Formats the given strings into a single multi-line string.
     *
     * @param inputs variable number of strings to be formatted.
     * @return single multi-line string.
     */
    public static String format(String... inputs) {
        StringBuilder output = new StringBuilder();
        for (String input: inputs) {
            if (input == null) {
                continue;
            }
            output.append(input).append("\n");
        }
        output.setLength(output.length() - 1);
        return output.toString();
    }

    /**
     * Formats the given list of tasks into a single multi-line string.
     *
     * @param tasks list of tasks to be formatted.
     * @return single multi-line string.
     */
    public static String formatTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No tasks yet!";
        }

        int n = tasks.size();
        String[] taskStrings = new String[n];
        for (int i = 0; i < n; i++) {
            taskStrings[i] = (i + 1) + ". " + tasks.get(i).toString();
        }

        return format("Here are your tasks:", String.join("\n", taskStrings));
    }
}
