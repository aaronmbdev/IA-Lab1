package Lab1;

import java.util.Random;

public class Utils {

    private static Random rand = new Random();

    public static int getRandNumber(final int max) {
        return rand.nextInt(max);
    }
}
