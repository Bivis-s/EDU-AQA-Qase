package enums;

public enum PropertyDriverType implements EnumValue {
    CHROME("chrome"),
    FIREFOX("firefox");

    private final String value;

    PropertyDriverType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
