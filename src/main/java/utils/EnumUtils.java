package utils;

import java.util.Random;

public class EnumUtils {
    private EnumUtils() {
    }

    /**
     * Returns random enum of {@param enumClass} enum class
     *
     * @param enumClass the enum class
     * @return random enum of {@param enumClass}
     */
    public static <T extends Enum<T>> T getRandomValue(Class<T> enumClass){
        T[] constants = enumClass.getEnumConstants();
        int x = new Random().nextInt(constants.length);
        return constants[x];
    }
}
