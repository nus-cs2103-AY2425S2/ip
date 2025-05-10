package tom.parser;

/**
 * The WordMatch class provides methods match strings.
 *
 */
public class WordMatch {

    /**
     * Calculates the Levenshtein distance between two strings.
     * The Levenshtein distance is a measure of the similarity between two strings,
     * which we will refer to as the source string (s1) and the target string (s2).
     * The distance is the number of deletions, insertions, or substitutions required
     * to transform the source string into the target string.
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return the Levenshtein distance between the two strings
     */
    public static int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j; // Insertions
                } else if (j == 0) {
                    dp[i][j] = i; // Deletions
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // No operation needed
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j], // Deletion
                        Math.min(
                            dp[i][j - 1], // Insertion
                            dp[i - 1][j - 1] // Substitution
                        )
                    );
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
