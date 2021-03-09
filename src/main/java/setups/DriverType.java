package setups;

public enum DriverType {
    CHROME("chrome"),
    FIREFOX("firefox");

    private final String value;

    DriverType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}