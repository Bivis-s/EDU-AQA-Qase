package utils.readers;

public class TimeoutsPropertyReader extends PropertyReader {
    private static final String TIMEOUTS_PROPERTIES_FILE_PATH = "src/main/resources/timeouts.properties";
    private static final String GLOBAL_WEBDRIVER_WAIT_PROPERTY_NAME = "global_webdriver_wait_timeout";

    public TimeoutsPropertyReader() {
        super(TIMEOUTS_PROPERTIES_FILE_PATH);
    }

    public int getGlobalWebdriverWaitTimeout() {
        return getInt(GLOBAL_WEBDRIVER_WAIT_PROPERTY_NAME);
    }
}
