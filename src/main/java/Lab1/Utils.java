package Lab1;

import java.util.Random;

public class Utils {

    private static Random rand = new Random();

    public static int getRandNumber(final int max) {
        return rand.nextInt(max);
    }

    public static int computeDistance(final int fromX, final int fromY, final int toX, final int toY) {
        return Math.abs(fromX-toX) + Math.abs(fromY-toY);
    }
}
