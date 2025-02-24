package utils;

import java.time.LocalDateTime;
import java.util.Random;

public class RandomUtils {

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    /**
     * Returns a random integer between 0 (inclusive) and the specified bound
     * (exclusive).
     *
     * @param bound the upper bound (exclusive)
     * @return a random integer between 0 (inclusive) and the specified bound
     * (exclusive)
     */
    public static int getRandomInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    /**
     * Returns a random integer between the specified min (inclusive) and max
     * (exclusive).
     *
     * @param min the lower bound (inclusive)
     * @param max the upper bound (exclusive)
     * @return a random integer between the specified min (inclusive) and max
     * (exclusive)
     */
    public static int getRandomInt(int min, int max) {
        return RANDOM.nextInt(max - min) + min;
    }

    /**
     * Returns a random double between 0.0 and 1.0.
     *
     * @return a random double between 0.0 and 1.0
     */
    public static double getRandomDouble() {
        return RANDOM.nextDouble();
    }

    /**
     * Returns a random double between the specified min (inclusive) and max
     * (exclusive).
     *
     * @param min the lower bound (inclusive)
     * @param max the upper bound (exclusive)
     * @return a random double between the specified min (inclusive) and max
     * (exclusive)
     */
    public static double getRandomDouble(double min, double max) {
        return RANDOM.nextDouble() * (max - min) + min;
    }

    /**
     * Returns a random boolean value.
     *
     * @return a random boolean value
     */
    public static boolean getRandomBoolean() {
        return RANDOM.nextBoolean();
    }

    /**
     * Returns a random string of the specified length.
     *
     * @param length the length of the random string
     * @return a random string of the specified length
     */
    public static String getRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(RANDOM.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * Returns a random LocalDateTime between the years 2000 and 2022.
     *
     * @return a random LocalDateTime between the years 2000 and 2022
     */
    public static LocalDateTime getRandomDateTime() {
        int year = getRandomInt(2000, 2022);
        int month = getRandomInt(1, 13);
        int day = getRandomInt(1, 29);
        int hour = getRandomInt(0, 24);
        int minute = getRandomInt(0, 60);
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    /**
     * Returns a formatted string representation of a random LocalDateTime.
     *
     * @return a formatted string representation of a random LocalDateTime
     */
    public static String getRandomDateTimeString() {
        LocalDateTime dateTime = getRandomDateTime();
        return DateTimeUtils.formatDateTime(dateTime);
    }

}
