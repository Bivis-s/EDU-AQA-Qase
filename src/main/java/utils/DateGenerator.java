package utils;

import java.util.Date;

public class DateGenerator {
    private DateGenerator() {
    }

    public static String getCurrentFullDate() {
        return new Date().toString();
    }
}
