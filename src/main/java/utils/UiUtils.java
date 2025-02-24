package utils;

import java.awt.Toolkit;

public class UiUtils {

    /**
     * Converts font size from points to pixels.
     *
     * @param points The font size in points.
     * @return The font size in pixels.
     */
    public static double pointsToPixels(double points) {
        return points * Toolkit.getDefaultToolkit().getScreenResolution() / 72.8;
    }

    /**
     * Converts font size from pixels to points.
     *
     * @param pixels The font size in pixels.
     * @return The font size in points.
     */
    public static double pixelsToPoints(double pixels) {
        return 72.8 * pixels / Toolkit.getDefaultToolkit().getScreenResolution();
    }
}
