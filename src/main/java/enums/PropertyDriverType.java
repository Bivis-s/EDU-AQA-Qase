package enums;

public enum PropertyDriverType {
    CHROME("chrome"),
    FIREFOX("firefox");

    private final String value;

    PropertyDriverType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
